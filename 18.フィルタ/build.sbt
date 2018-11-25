resolvers += "Java.net repository" at "http://download.java.net/maven/2"
resolvers += "Open Source Geospatial Foundation Repository" at "http://download.osgeo.org/webdav/geotools/"
resolvers += "Boundless Maven Repository" at "http://repo.boundlessgeo.com/main"

libraryDependencies ++= Seq(
  "org.geotools" % "gt-shapefile" % "20.1",
  "org.geotools" % "gt-cql" % "20.1"
)