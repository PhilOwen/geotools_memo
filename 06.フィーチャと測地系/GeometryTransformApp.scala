import org.geotools.geometry.jts.JTSFactoryFinder
import org.locationtech.jts.geom._

object GeometryTransformApp extends App {
  val factory = JTSFactoryFinder.getGeometryFactory
  val point   = factory.createPoint(new Coordinate(-2, 30))
  val line    = factory.createLineString(Array(new Coordinate(-1.9, 30), new Coordinate(-1.8, 300)))

  val transformer = new Transformer("EPSG:32736")

  val point1 = transformer(point)
  val line1  = transformer(line)
  println(s"$point -> $point1")
  println(s"$line -> $line1")
}