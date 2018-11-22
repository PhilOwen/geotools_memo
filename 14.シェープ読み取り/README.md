GeoToolsを使ってshapefileを読み出してみる。  
とりあえず、ファイルとテーブル名だけ指定して、全件検索。

DataStore（ここではshapefile）を取得して、
FeatureSource（テーブル）を取得してから
フィーチャを取得する。  
FeatureSourceのgetFeatures()にフィルタを渡せば、
それで絞られた結果が返る。
が、今回は何もしないので全件検索。

使ったshapefileは
[Feature Tutorial](http://docs.geotools.org/latest/userguide/tutorial/feature/csv2shp.html)
のもの。

書き込みに比べると比較的シンプルだが、それでもクラスやメソッドを覚えるのは大変。
適宜コピペしてよいと思う。

## 参考
- [GeoTools Query Tutorial](http://docs.geotools.org/latest/userguide/tutorial/filter/query.html)