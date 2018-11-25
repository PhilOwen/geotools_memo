GeoTools公式チュートリアルの5番目、
Image Tutorialの最後の方をピックアップ。
地図配信サービスのWMS(Web Map Service)から、
地図をダウンロードして保存してみる。

WMSサーバが必要だが、
今回はローカルで立てたGeoServerを使った。
GeoServerにデフォルトで入っていた
ニューヨークのレイヤをそのまま使用
（レイヤリストの3番目）。  
ダウンロードしたい地図範囲と、画像サイズは
どう指定するのがいいのだろう…

保存は、GeoTools関係なく、
単純にストリームをImageIOで保存した。

## 参考
- [Image Tutorial](http://docs.geotools.org/latest/userguide/tutorial/raster/image.html)
