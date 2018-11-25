resolvers += "Java.net repository" at "http://download.java.net/maven/2"
resolvers += "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"

libraryDependencies ++= Seq(
  "org.geotools" % "gt-shapefile" % "20.1",
  "org.geotools" % "gt-swing" % "20.1"
  )