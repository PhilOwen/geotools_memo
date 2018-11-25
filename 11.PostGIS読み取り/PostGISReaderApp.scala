import collection.JavaConversions._
import org.geotools.data.DataStore
import org.geotools.data.DataStoreFinder
import org.geotools.data.simple.SimpleFeatureCollection
import org.opengis.feature.simple.SimpleFeature

object PostGISReaderApp extends App {
  val params = Map[String, Object](
    "dbtype"   -> "postgis",
    "host"     -> "localhost",
    "port"     -> (5432:Integer),
    "database" -> "gis",
    "user"     -> "docker",
    "passwd"   -> "docker"
  )
  val dataStore = connect(params)
  println("getting tables...")
  dataStore.getTypeNames.foreach(f => println(s"- $f"))

  println("sending query...")
  val features = filterFeatures("sample_point")
  println("iterating results...")
  for (feature <- features.toArray) {
    printAttributes(feature.asInstanceOf[SimpleFeature])
  }

  def connect(params: Map[String, Object]): DataStore = {
    DataStoreFinder.getDataStore(params)
  }

  def filterFeatures(typeName: String): SimpleFeatureCollection = {
    val source = dataStore.getFeatureSource(typeName)
    source.getFeatures()
  }

  def printAttributes(feature: SimpleFeature) {
    val name    = feature.getAttribute("name")
    // val sth     = feature.getAttribute("sth")
    println(s"- $name")
  }
}