# J1-8

## 物理法則の式を書き換えて攻略するアクションゲーム

* 落下速度の式をいじって遠くに行けるとか  
* 攻撃のエネルギー式を変えて攻略する敵  
* キャラとか攻撃とかギミックを制御する式を書き換えて攻略

***

## 環境

* VScodeでコーディング
* コード管理はGithub
* 文字コードは UTF-8

***

## 命名規則

* クラス名  
大文字のやつ  
ex) TestClass  

* メソッド名  
小文字アンダーバーのやつ  
ex) test_method  

* 変数  
小文字アンダーバーのやつ  
ex) test_variable  

* 定数  
全部大文字アンダーバー  
ex) FINAL_STATIC  

***

## 画面仕様

Start画面 → プレイ画面 → GameOver or GameClear

### Start画面

* ゲーム開始ボタン
* ゲーム終了  
* スコアログ

### プレイ画面

* 背景レイヤー, 構造物レイヤー, 当たり判定レイヤーで構成  
* タイムとスコア右上に表示  
* 操作キーの説明を左上に表示  
* PAUSE画面  

### GameClear or GameOver

* スコア表示  
* リスタート,終了選択

***

## クラス仕様

### M 板倉

* Player
* Enemy
* Object

### V 綾部

* Start
* PlayScene
* ValueSetting(式変更画面)
* PauseScene
* GameFinish(clearとGameOverは文字列のみ違う)

### C 山下

* KeyController

***

## スケジュール

    12月
      week1(12/2~12/8)  
          ・環境構築(VScodeとGithub)
          ・要件定義(命名規則・仕様)
      week2(12/9~12/15)  
          ・構造設計の決定(担当を決める)
          ・コーディング開始？
      week3(12/16~12/22)  
          ・以下コーディング(コーディングの優先順位や担当、流れはweek2で決定)
      week4(12/23~12/29)  
    1月  
      week1(12/30~1/5)

***
