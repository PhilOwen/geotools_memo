GeoTools公式チュートリアルの4番目、
Query Tutorialから、PostGISからデータを読むところだけをやってみる。  
この節は元々、FilterとかQueryでDBを読むのをやっていたが、
PostGIS自体が、わりと重要なので切り出した。

PostGIS用DataStoreのインスタンスを取得するときは、
GeoToolsの推奨に従って、DataStoreFinderを使った。
getDataStore()にPostGIS用のパラメータを渡せばよく、
PostGIS専用の具体的なクラス名は表に出てこない。

PostGISには、ローカルのDockerを使った。
kartoza/postgisというイメージで、ユーザ名とパスワードはともにdocker、
データベース名はgis。
そこにsetup.sqlを仮で流してみた。

## 参考
- [Query Tutorial](http://docs.geotools.org/latest/userguide/tutorial/filter/query.html)
- [kartoza/postgis](https://hub.docker.com/r/kartoza/postgis/)
