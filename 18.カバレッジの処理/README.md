GeoTools公式チュートリアルの6番目、
Coverage Processor Tutorialをやってみる。

チュートリアルだとかなり長いが、
要は画像を読み込んで、分割したり、
縮小したりした後、保存しているだけ。  
チュートリアルは、Javaのループで
ゴチャゴチャやっているのが非常に良くない。

ただ、それぞれの画像が地理的な座標を持っている点は、本当の難しさがある。
普通の画像より意味が大きく、いろいろ処理するときも
測地系やら考えながらやらないといけない。
あまり単純ではない。

あと、カバレッジというのは、「何かを覆う画像」のこと。
地理情報の文脈でいうと、地球を覆う地図画像のこと。  
今回はJAXAの標高GeoTiffを使った。

## 参考
- [GeoTools Coverage Processor Tutorial](http://docs.geotools.org/latest/userguide/tutorial/coverage/coverage.html)
- [JAXAのDEM](https://www.eorc.jaxa.jp/ALOS/aw3d30/index_j.htm)
- [JAXAのデータ利用規約](https://www.eorc.jaxa.jp/ALOS/aw3d30/terms.htm)
