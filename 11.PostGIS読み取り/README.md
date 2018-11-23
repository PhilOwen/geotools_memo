GeoToolsを使って、PostGISからデータを取得してみる。

CQL.toFilter()で、文字列からFilterオブジェクトを生成できる。
今回は、例としてnameカラムにmagを含むものを抽出した。
文字列から直でFilterを組み立てる以外に、
FilterFactoryの各種メソッドを使ってもFilterは組み立てられる。

PostGIS用DataStoreのインスタンスを取得するとき、
GeoToolsの推奨に従って、DataStoreFinderを使った。
getDataStore()にPostGIS用のパラメータを渡せばよく、
PostGIS専用の具体的なクラス名は表に出てこない。


PostGISには、ローカルのDockerを使った。
kartoza/postgisというイメージで、ユーザ名とパスワードはともにdocker、
データベース名はgis。
そこにsetup.sqlを仮で流してみた。

## 参考
- [Query Tutorial](http://docs.geotools.org/latest/userguide/tutorial/filter/query.html)
- [CQLについて](http://docs.geotools.org/latest/userguide/library/cql/cql.html)
- [kartoza/postgis](https://hub.docker.com/r/kartoza/postgis/)
