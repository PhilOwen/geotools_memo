import java.net.URL
import java.io.File
import javax.imageio.ImageIO
import collection.JavaConversions._

import org.geotools.data.ows.Layer
import org.geotools.data.wms.WebMapServer
import org.geotools.data.wms.request.GetMapRequest
import org.geotools.data.wms.response.GetMapResponse

object WMSRequestApp extends App {
  val url = new URL("http://localhost:8080/geoserver/wms?VERSION=1.1.1&Request=GetCapabilities&Service=WMS")
  val wms = new WebMapServer(url)
  val nyLayer  = getLayer(wms, 3)
  val request  = setupRequest(wms, nyLayer)
  val response = wms.issueRequest(request)
  saveResponseImage(response, "./ny.png")

  def getLayer(server: WebMapServer, i: Int): Layer = {
    val capabilities = wms.getCapabilities()
    val layers = capabilities.getLayerList()
    layers(i)
  }

  def setupRequest(server: WebMapServer, layer: Layer): GetMapRequest = {
    val req = server.createGetMapRequest()
    req.addLayer(layer)
    req.setFormat("image/png")
    req.setDimensions("512", "512")
    req.setTransparent(true)
    req.setSRS("EPSG:4326")
    req.setBBox("-74.047185,40.679648,-73.907005,40.882078")
    req
  }

  def saveResponseImage(res: GetMapResponse, filename: String) {
    val image = ImageIO.read(res.getInputStream())
    ImageIO.write(image, "PNG", new File(filename))
  }
}