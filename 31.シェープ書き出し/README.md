GeoTools公式チュートリアルの2番目、
Feature Tutorialをやってみる。
緯度経度と属性の入っているCSVの中身を
shapefileとして書き出してみる。

なお、チュートリアルでは2番目だったが、
シェープを書くのは実際あまりない。
読むほうが需要が大きい。

shapefileは、仕様が公開されていて、国土地理院含めよく使われるが、
バイナリファイルで、自力で読み書きするのはかなり面倒。  
GeoToolsのチュートリアルにあるコードを
Scalaでかなり短く書き直したが、それでも長い。
importがかなり多い。


地物(Feature)は、GeoToolsでは一般に
org.opengis.feature.simple.SimpleFeature
で表される。
これのインスタンスは、コンストラクタからでなく、
SimpleFeatureBuilderに作ってもらう。

ここでは、まずCSVからSimpleFeatureのリストを作った。  
shapefileへ書き出すには、DataStoreをまず作って、
DataStoreからFeatureStoreを取り出す。
FeatureStoreに、Featureのリストの書き込み
トランザクションを行わせて完了。

なお、FeatureStoreと似たようなものに
FeatureSourceというインタフェースもあるが、
Storeの方は読み書きできて、Sourceは読み取り専用。
StoreはSourceを継承している。

覚えにくいので、当分はコピペでいいかな…

## 参考
- [GeoTools Feature Tutorial](http://docs.geotools.org/latest/userguide/tutorial/feature/csv2shp.html)
