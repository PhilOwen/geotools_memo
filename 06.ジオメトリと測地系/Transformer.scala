import org.geotools.referencing.CRS
import org.geotools.geometry.jts.JTS
import org.locationtech.jts.geom._

class Transformer(targetCrsCode: String) {
  val targetCrs  = CRS.decode(targetCrsCode)
  val defaultCrs = CRS.decode("EPSG:4326")
  val transform  = CRS.findMathTransform(defaultCrs, targetCrs, true)

  def apply(geom: Geometry): Geometry = JTS.transform(geom, transform)
}