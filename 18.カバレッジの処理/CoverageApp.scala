import java.io.File
import collection.JavaConversions._

import org.geotools.coverage.grid.GridCoverage2D
import org.geotools.coverage.grid.io._
import org.geotools.coverage.processing._
import org.geotools.geometry.Envelope2D
import org.geotools.geometry.jts.ReferencedEnvelope
import org.opengis.geometry.Envelope
import org.opengis.referencing.crs.CoordinateReferenceSystem

object CoverageApp extends App {
  val src      = "./N036E137_AVE_DSM.tif"
  val format   = GridFormatFinder.findFormat(src)
  val coverage = readCoverage(src, format)

  val covs = splitCoverage(coverage, 4)
  for ((i, j, c) <- covs) {
    val filename = s"./out/div_${i}_${j}.tif"
    saveCoverage(c, filename, format)
  }

  val cov2 = scaleCoverage(coverage, 0.5)
  saveCoverage(cov2, "./out/half.tif", format)

  def readCoverage(src: String, format: AbstractGridFormat): GridCoverage2D = {
    val reader = format.getReader(src)
    reader.read(null)
  }

  def saveCoverage(coverage: GridCoverage2D, filename: String, format: AbstractGridFormat) {
    val tileFile = new File(filename)
    val writer = format.getWriter(tileFile)
    writer.write(coverage, null)
  }

  def splitCoverage(coverage: GridCoverage2D, nDiv: Int): List[(Int, Int, GridCoverage2D)] = {
    val envelope = coverage.getEnvelope2D
    val bbox     = envelope.getBounds

    val (minX, maxX)  = (bbox.getMinX, bbox.getMaxX)
    val (minY, maxY)  = (bbox.getMinY, bbox.getMaxY)
    val geoTileWidth  = (maxX - minX) / nDiv.toDouble
    val geoTileHeight = (maxY - minY) / nDiv.toDouble

    val targetCRS = coverage.getCoordinateReferenceSystem

    val getEnvelope = (i: Int, j: Int) =>
                    getTileEnvelope(minX, minY, geoTileWidth, geoTileHeight, targetCRS, i, j)
    val rows = for (i <- 0 to (nDiv-1)) yield {
      val row = for (j <- 0 to (nDiv-1)) yield {
        val envelope  = getEnvelope(i, j)
        val coverage1 = cropCoverage(coverage, envelope)
        (i, j, coverage1)
      }
      row.toList
    }
    rows.toList.flatten
  }

  def cropCoverage(gridCoverage: GridCoverage2D, envelope: Envelope): GridCoverage2D = {
    val processor = CoverageProcessor.getInstance()

    val params = processor.getOperation("CoverageCrop").getParameters()
    params.parameter("Source").setValue(gridCoverage)
    params.parameter("Envelope").setValue(envelope)

    processor.doOperation(params).asInstanceOf[GridCoverage2D]
  }

  def scaleCoverage(coverage: GridCoverage2D, k: Double): GridCoverage2D = {
    val ops = new Operations(null)
    ops.scale(coverage, k, k, 0, 0).asInstanceOf[GridCoverage2D]
  }

  def getTileEnvelope(minX: Double, minY: Double, width: Double, height: Double,
        targetCRS: CoordinateReferenceSystem, i: Int, j: Int): Envelope = {
    val startX = (i * width) + minX
    val endX   = startX + width
    val startY = (j * height) + minY
    val endY   = startY + height

    new ReferencedEnvelope(startX, endX, startY, endY, targetCRS)
  }
}