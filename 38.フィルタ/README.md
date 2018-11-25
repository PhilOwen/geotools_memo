GeoTools公式チュートリアルの4番目、
Query Tutorialをやってみる。

この節の元の内容は、ネット上の公開PostGISに接続して、
FilterやQueryを使ってフィーチャを検索するというものだった。
が、簡単のため、ローカルのシェープで検索してみる。
データストアが何だろうが、機能は一応揃っているようなので
（パフォーマンスは違うだろうが）。

CQL.toFilter()で、文字列からFilterオブジェクトを生成できる。
今回は、例としてname属性にCaを含むフィーチャを抽出した。
また、オーストラリアくらいの範囲内に存在するフィーチャも抽出した。  
単純な属性での検索は他の方法でもできるだろうけれど、
空間検索をあっさり実行できるのは結構強い。

一応、文字列から直でFilterを組み立てる以外に、
FilterFactoryの各種メソッドを使ってもFilterは組み立てられる。
が面倒で、CQLを素直に使ったほうが楽だと思う…。

またQueryというオブジェクトがあり、これを使うと
Filterに加え、LIMITやORDER BY的なこともできる。

## 参考
- [Query Tutorial](http://docs.geotools.org/latest/userguide/tutorial/filter/query.html)
- [CQLについて](http://docs.geotools.org/latest/userguide/library/cql/cql.html)
