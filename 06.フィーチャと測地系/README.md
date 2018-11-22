GeoToolsを使って、座標を変換してみる。  
東アフリカのとある位置を、普通の緯度経度から、
現地に合ったユニバーサル横メルカトル図法の表現に変換する。

まずは、座標点（Coordinate）から、
点（Point）と線（LineString）を作った。
特に指定しないと、座標点は緯度経度として解釈される
（ディスプレイ座標とは縦横が逆なことに注意）。

変換すると、メートル表記のXY座標が返る。
が、値が大きくて読みにくい上、
そもそもメルカトル図の座標で言われてもピンとこない。
EPSGのサイトで両者を変換できるので、
それと比べることで、このプログラムの変換が正しいと確認できる。


GeoToolsでは、ジオメトリを表すのに
幾何学ライブラリのJTSのクラスを使っている。
Point、LineString、PolygonはGeometryの一種なので、Geometryの子クラスになっている。
それらの集合体の、GeometryCollectionというクラスもある。
**Geometryは座標系を持っていて、ただの無次元の図形ではない**のが紛らわしい。
無次元の座標点はCoordinateのほう。

## 参考
- [EPSGの座標変換サイト](https://epsg.io/transform#s_srs=4326&t_srs=32736&x=30.0000000&y=-2.0000000)
- [JTSのGeometryのJavaDoc](http://tsusiatsoftware.net/jts/javadoc/com/vividsolutions/jts/geom/Geometry.html)
- [GeoTools Geometry CRS Tutorial](http://docs.geotools.org/latest/userguide/tutorial/feature/csv2shp.html)
