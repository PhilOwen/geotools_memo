GeoTools公式チュートリアルの3番目、
Geometry CRS Tutorialをやってみる。

公式では、シェープファイルを読んで、
GUIで表示したり、測地系を変換したりしていた。
ただ、GUIまわりはめんどくさくて
本質が埋もれがちなので、GeoToolsにGUIは期待していない。  
今回は本当に測地系変換だけをやった。
東アフリカのとある位置を、普通の緯度経度表現から、
現地に合ったユニバーサル横メルカトル図法の表現に変換した
（EPSG 32736）。

まずは、点（Point）と線（LineString）を作った。
WKT形式で簡単な指定する場合と、
プログラムで生の座標点（Coordinate）から作る場合の両方をやった。
WKTのほうがラクなので、普通はWKTでやればいいと思う。  
なお、特に指定しないと、座標点は緯度経度として解釈される
（ディスプレイ座標とは縦横が逆なことに注意）。

変換すると、メートル表記のXY座標が返る。
とはいっても、この表現ではピンとこないので、
EPSGのサイトで両者を変換できる。
それと比べることで、このプログラムの変換が正しいと確認できる。


GeoToolsでは、ジオメトリを表すのに
幾何学ライブラリのJTSのクラスを使っている。
Point、LineString、PolygonはGeometryの一種なので、Geometryの子クラスになっている。
それらの集合体の、GeometryCollectionというクラスもある。
**Geometryは座標系を持っていて、無次元のただの図形ではない**のが紛らわしい。
無次元の座標点はCoordinateのほう。  
また、Featureは、属性に加えてGeometryも持つ、という形。

## 参考
- [EPSGの座標変換サイト](https://epsg.io/transform#s_srs=4326&t_srs=32736&x=30.0000000&y=-2.0000000)
- [JTSのGeometryのJavaDoc](http://tsusiatsoftware.net/jts/javadoc/com/vividsolutions/jts/geom/Geometry.html)
- [GeoTools Geometry CRS Tutorial](http://docs.geotools.org/latest/userguide/tutorial/feature/csv2shp.html)
