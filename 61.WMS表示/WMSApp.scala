import collection.JavaConversions._

import org.geotools.data.ows.Layer
import org.geotools.data.wms.WebMapServer
import org.geotools.map._
import org.geotools.swing.JMapFrame
import org.geotools.swing.wms._

object WMSApp extends App {
  val capabilitiesURL = WMSChooser.showChooseWMS(List("http://localhost:8080/geoserver/wms/"))
  val wms     = new WebMapServer(capabilitiesURL)
  val layers  = WMSLayerChooser.showSelectLayer(wms)
  val content = new MapContent()
  for (layer <- layers) {
    val displayLayer = new WMSLayer(wms, layer)
    content.addLayer(displayLayer)
  }
  JMapFrame.showMap(content)
}