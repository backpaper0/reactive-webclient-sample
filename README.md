# Reactive WebClient sample

## 起動

`apple`、`pen`、`uh`、`blocking-uh`を起動してください。

## 実行

```sh
# blocking uh
curl localhost:8888/uh
# reactive uh
curl localhost:8080/uh
```

`blocking uh`では`apple`へのHTTP通信が完了してから`pen`へのHTTP通信を行います。
つまりHTTP通信がシリアルに行われます。

それに対して`reactive uh`では`apple`へのHTTP通信と`pen`へのHTTP通信がコンカレントに行われます。

`apple`と`pen`にはそれぞれ3秒間のスリープを入れています。
シリアルな`blocking uh`は全体の処理に約6秒かかりますが、コンカレントな`reactive uh`は約3秒です。

`blocking uh`と`reactive uh`、どちらもレスポンスに処理にかかった時間(ミリ秒)を出力しているので比較してみてください。

