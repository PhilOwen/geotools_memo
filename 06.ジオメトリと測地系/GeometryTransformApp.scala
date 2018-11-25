import org.geotools.geometry.jts.JTSFactoryFinder
import org.locationtech.jts.geom._
import org.locationtech.jts.io.WKTReader

object GeometryTransformApp extends App {
  val factory = JTSFactoryFinder.getGeometryFactory()

  val wktReader = new WKTReader(factory)
  val pointA    = wktReader.read("POINT(-2 30)")
  val lineA     = wktReader.read("LINESTRING(-1.9 30, -1.8 30)")

  val pointB = factory.createPoint(new Coordinate(-2, 30))
  val lineB  = factory.createLineString(Array(new Coordinate(-1.9, 30), new Coordinate(-1.8, 30)))

  val transformer = new Transformer("EPSG:32736")

  val p1::l1::p2::l2::_ = List(pointA, lineA, pointB, lineB).map(transformer(_))
  println(s"$pointA -> $p1")
  println(s"$pointB -> $p2")
  println(s"$lineA -> $l1")
  println(s"$lineB -> $l2")
}