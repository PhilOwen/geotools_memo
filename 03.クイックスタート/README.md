Javaで地理空間データを処理するためのGeoToolsを使ってみる。

冗長になりがちなJavaは避けたい。
でも移植に手間をあんまりかけたくなかったので
Scalaでやった（clojureなどでなく）。
`sbt run`で起動できる。

公式サイトのQuick Startにある例を少し変更して、
EsriのShapefile形式のファイルを開いて表示してみる。
国土地理院からゲットした地図を開いて、パンやズームできるようにした。  
しかしまぁ、GeoToolsは日本語情報が恐ろしく少ないな...

使った地図は、国土地理院の地球地図日本というもの。
原則フリーで使える。

起動がちょっと遅い。
また、終了時に警告が出ているが、スルー。

## 参考
- [GeoToolsのQuick Start（Maven版）](http://docs.geotools.org/latest/userguide/tutorial/quickstart/maven.html)
- [地球地図日本のShapefile](http://www.gsi.go.jp/kankyochiri/gm_jpn.html)
- [地球地図日本の利用規約](http://www.gsi.go.jp/kikakuchousei/kikakuchousei40182.html)
