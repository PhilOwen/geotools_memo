import collection.JavaConversions._
import org.geotools.data.DataStore
import org.geotools.data.DataStoreFinder
import org.geotools.data.simple.SimpleFeatureCollection
import org.geotools.filter.text.cql2.CQL
import org.opengis.feature.simple.SimpleFeature

object QueryApp extends App {
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

  val typeName   = "sample_point"
  val filterText = "name LIKE '%mag%'"
  println("sending query...")
  val features = filterFeatures(typeName, filterText)
  println("iterating results...")
  for (feature <- features.toArray) {
    printAttributes(feature.asInstanceOf[SimpleFeature])
  }

  def connect(params: Map[String, Object]): DataStore = {
    DataStoreFinder.getDataStore(params)
  }

  def filterFeatures(typeName: String, filterText: String): SimpleFeatureCollection = {
    val source = dataStore.getFeatureSource(typeName)
    val filter = CQL.toFilter(filterText)
    source.getFeatures(filter)
  }

  def printAttributes(feature: SimpleFeature) {
    val name    = feature.getAttribute("name")
    // val sth     = feature.getAttribute("sth")
    println(s"- $name")
  }
}