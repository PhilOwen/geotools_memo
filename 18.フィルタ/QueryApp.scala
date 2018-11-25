import java.io.File
import collection.JavaConversions._

import org.geotools.data._
import org.geotools.filter.text.cql2.CQL
import org.opengis.feature.simple.SimpleFeature
import org.locationtech.jts.geom._

object QueryApp extends App {
  val dataStore = connectToDataSource("./shp/locations1.shp")

  println("like search:")
  val features1  = readFeatures(dataStore, "locations1", "name LIKE '%Ca%'")
  features1.foreach(writeAttributes)

  println("spatial search:")
  val features2  = readFeatures(dataStore, "locations1", "BBOX(the_geom, 110, -45, 155, -10)")
  features2.foreach(writeAttributes)

  println("search limit:")
  val features3  = readFeatures(dataStore, "locations1", 5)
  features3.foreach(writeAttributes)

  def connectToDataSource(shpfile: String): DataStore = {
    val file  = new File(shpfile)
    val param = Map("url" -> file.toURI.toURL)
    DataStoreFinder.getDataStore(param)
  }

  def readFeatures(dataStore: DataStore, typeName: String, searchText: String): List[SimpleFeature] = {
    val source = dataStore.getFeatureSource(typeName)
    val filter = CQL.toFilter(searchText)
    source.getFeatures(filter).toArray.map {
      _.asInstanceOf[SimpleFeature]
    }.toList
  }

  def readFeatures(dataStore: DataStore, typeName: String, limit: Int): List[SimpleFeature] = {
    val source = dataStore.getFeatureSource(typeName)
    val query = new Query()
    query.setMaxFeatures(limit)
    source.getFeatures(query).toArray.map {
      _.asInstanceOf[SimpleFeature]
    }.toList
  }

  def writeAttributes(feature: SimpleFeature) {
    val name = feature.getAttribute("name").asInstanceOf[String]
    val geom = feature.getAttribute("the_geom").asInstanceOf[Geometry]
    println(s"- $name, $geom")
  }
}