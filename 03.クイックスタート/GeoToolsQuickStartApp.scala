import java.io.File

import org.geotools.data._
import org.geotools.map._
import org.geotools.styling._
import org.geotools.swing.JMapFrame

object GeoToolsQuickStartApp extends App {
  val file  = new File("./coastl_jpn.shp")
  val store = FileDataStoreFinder.getDataStore(file)

  val featureSource = store.getFeatureSource
  val style = SLD.createSimpleStyle(featureSource.getSchema)
  val layer = new FeatureLayer(featureSource, style)

  val map = new MapContent()
  map.addLayer(layer)
  JMapFrame.showMap(map)
}