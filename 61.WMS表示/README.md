地図配信サービスのWMS(Web Map Service)から、地図を選んで表示してみる。

まずはWMSサーバを選択する必要があるが、
今回はローカルで立てたGeoServerを使った。
JAXAのDEM（Digital Elevation Model）を
WMSで配信するようにGeoServerを設定した。
DEMは、JAXAのサイトからGeoTiffとしてダウンロードでき、
今回は富山県周辺を選んだ。

WMSサーバは、自分ができること(capability)をメタデータとして配信しているので、
そのcapabilityから、見たいレイヤを選ぶ。
複数選択可。  
選択されたレイヤは、MapFrameに追加され表示される。

## 参考
- [Image Tutorial](http://docs.geotools.org/latest/userguide/tutorial/raster/image.html)
- [JAXAのDEM](https://www.eorc.jaxa.jp/ALOS/aw3d30/index_j.htm)
- [JAXAのデータ利用規約](https://www.eorc.jaxa.jp/ALOS/aw3d30/terms.htm)