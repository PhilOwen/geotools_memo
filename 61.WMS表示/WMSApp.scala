import javax.swing.JOptionPane
import org.geotools.data.ows.Layer
import org.geotools.data.wms.WebMapServer
import org.geotools.map._
import org.geotools.swing.JMapFrame
import org.geotools.swing.wms._
import collection.JavaConversions._

object WMSApp extends App {
  val capabilitiesURL = WMSChooser.showChooseWMS(List("http://localhost:8080/geoserver/wms/"))
  if( capabilitiesURL == null ){
    System.exit(0)
  }

  val wms = new WebMapServer(capabilitiesURL)
  val wmsLayers = WMSLayerChooser.showSelectLayer(wms)
  if(wmsLayers == null) {
    JOptionPane.showMessageDialog(null, "Could not connect - check url")
    System.exit(0)
  }

  val mapcontent = new MapContent()
  for (wmsLayer <- wmsLayers) {
    val displayLayer = new WMSLayer(wms, wmsLayer)
    mapcontent.addLayer(displayLayer)
  }
  JMapFrame.showMap(mapcontent)
}