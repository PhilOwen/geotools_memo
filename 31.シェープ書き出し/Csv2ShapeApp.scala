import java.io._
import scala.collection.JavaConversions._

import org.geotools.data._
import org.geotools.data.collection.ListFeatureCollection
import org.geotools.data.shapefile._
import org.geotools.data.simple._
import org.geotools.feature.simple._
import org.geotools.geometry.jts.JTSFactoryFinder
import org.opengis.feature.simple._
import org.locationtech.jts.geom._

object Csv2ShapeApp extends App {
  val (csvfile, shpfile) = ("locations1.csv", "./out/locations1.shp")
  val TYPE = DataUtilities.createType("Location",
    Array("the_geom:Point:srid=4326", "name:String", "number:Integer").mkString(","))

  val records      = readCSV(csvfile)
  val features     = buildFeatures(TYPE, records)
  val dataStore    = createShapefileDataStore(shpfile, TYPE)
  val featureStore = getFeatureStore(dataStore)
  val collection   = new ListFeatureCollection(TYPE, features)
  runTransaction(featureStore, collection)

  def readCSV(filename: String): List[(Coordinate, String, Int)] = {
    val lines = scala.io.Source.fromFile(filename).getLines.toList

    for (line <- lines.tail if ! line.trim.isEmpty) yield {
      val cols = line.split(",").map(_.trim)
      val latitude  = cols(0).toDouble
      val longitude = cols(1).toDouble
      val name      = cols(2)
      val number    = cols(3).toInt

      val coord = new Coordinate(longitude, latitude)
      (coord, name, number)
    }
  }

  def buildFeatures(featureType: SimpleFeatureType, records: List[(Coordinate, String, Int)]):
          List[SimpleFeature] = {
    val builder = new SimpleFeatureBuilder(TYPE)
    val factory = JTSFactoryFinder.getGeometryFactory()
    for ((point, name, number) <- records) yield {
      builder.add(factory.createPoint(point))
      builder.add(name)
      builder.add(number)
      builder.buildFeature(null)
    }
  }

  def createShapefileDataStore(filename: String, featureType: SimpleFeatureType): DataStore = {
    val factory = new ShapefileDataStoreFactory()
    val file    = new File(filename)
    val params  = Map[String, Serializable](
      "url" -> file.toURI.toURL,
      "create spatial index" -> true
    )

    val datastore = factory.createNewDataStore(params)
    datastore.createSchema(featureType)
    datastore
  }

  def getFeatureStore(dataStore: DataStore): SimpleFeatureStore = {
    val typeName      = dataStore.getTypeNames().head
    val featureSource = dataStore.getFeatureSource(typeName)
    featureSource.asInstanceOf[SimpleFeatureStore]
  }

  def runTransaction(featureStore: SimpleFeatureStore, collection: ListFeatureCollection) {
    val transaction = new DefaultTransaction("create")
    featureStore.setTransaction(transaction)
    try {
      featureStore.addFeatures(collection)
      transaction.commit()
    } catch { case e: Exception =>
      e.printStackTrace()
      transaction.rollback()
    } finally {
      transaction.close()
    }
  }
}
