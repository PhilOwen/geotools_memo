import java.io.File
import collection.JavaConversions._

import org.geotools.data._
import org.opengis.feature.simple.SimpleFeature
import org.locationtech.jts.geom._

object ShapeReaderApp extends App {
  val dataStore = connectToDataSource("locations1.shp")
  val features  = readFeatures(dataStore, "locations1")
  features.foreach(writeAttributes)

  def connectToDataSource(shpfile: String): DataStore = {
    val file  = new File(shpfile)
    val param = Map("url" -> file.toURI.toURL)
    DataStoreFinder.getDataStore(param)
  }

  def readFeatures(dataStore: DataStore, typeName: String): List[SimpleFeature] = {
    val source = dataStore.getFeatureSource(typeName)
    source.getFeatures().toArray.map {
      _.asInstanceOf[SimpleFeature]
    }.toList
  }

  def writeAttributes(feature: SimpleFeature) {
    val name = feature.getAttribute("name").asInstanceOf[String]
    val geom = feature.getAttribute("the_geom").asInstanceOf[Geometry]
    println(s"$name, $geom")
  }
}