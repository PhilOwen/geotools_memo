GeoToolsを使って、shapefileに書き込んでみる。  
GeoTools公式チュートリアルのFeature Tutorialに沿って、
緯度経度と属性の入っているCSVを入力に使った。

shapefileは、仕様が公開されていて、国土地理院含めよく使われるが、
バイナリファイルで、自力で読み書きするのはかなり面倒。  
GeoToolsのチュートリアルにあるコードを
Scalaでかなり短く書き直したが、それでも長い。
importが多い。


地物(Feature)は、GeoToolsでは一般に
org.opengis.feature.simple.SimpleFeature
で表される。
そこで、まずCSVからSimpleFeatureのリストを作った。  
ここからshapefileへ書き出すには、DataStoreをまず作って、
DataStoreからFeatureStoreを取り出す。
Featureのリストの書き込みトランザクションを、
FeatureStoreに行わせて、ようやく完了。

なお、FeatureStoreと似たようなものに
FeatureSourceというインタフェースもあるが、
Storeの方は読み書きできて、Sourceは読み取り専用。
StoreはSourceを継承している。

覚えにくいので、当分はコピペでいいかな…

## 参考
- [GeoTools Feature Tutorial](http://docs.geotools.org/latest/userguide/tutorial/feature/csv2shp.html)
