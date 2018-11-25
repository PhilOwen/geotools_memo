GeoTools公式チュートリアルの5番目、
Image Tutorialの最後の方をピックアップ。
地図配信サービスのWMS(Web Map Service)から、地図を選んで表示してみる。

WMSサーバが必要だが、
またローカルで立てたGeoServerを使った。  
GeoServerはかなり便利。
GeoTiffなどもレイヤとして簡単にセットアップできる。
JAXAのDEMとか、無償でダウンロードできるので使うと良い。
今回は山がちな富山県周辺を選んだ。

WMSサーバは、自分ができること(capability)をメタデータとして配信しているので、
そのcapabilityから、見たいレイヤを選ぶ。
複数選択可。  
選択されたレイヤは、MapFrameに追加され表示される。

## 参考
- [Image Tutorial](http://docs.geotools.org/latest/userguide/tutorial/raster/image.html)
- [JAXAのDEM](https://www.eorc.jaxa.jp/ALOS/aw3d30/index_j.htm)
- [JAXAのデータ利用規約](https://www.eorc.jaxa.jp/ALOS/aw3d30/terms.htm)