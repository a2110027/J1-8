// 仕様
// ---全体概要---
// プレイヤーや敵・ブロックもすべてStageObjectという親クラスを持つものとする。
// StageObjectは当たり判定処理のためにStageObjectListというクラスに登録する
// ---StageObjectList---
// StageObjectListはStageObjectクラスの配列となっている。当たり判定の処理で読み込むとき、読み込み範囲を限定しながら使うことで処理を軽くする目的がある。
// 新しい要素を登録するにはadd_stage_object(StageObject new_obj)を使用する
// 配列を取得するにはget_collision_list(int range_x[], int range_y[])を使用する
// 取得時に渡す引数は[0, 100], [50, 200]のように渡す。こうすると0<=x<=100, 50<=y<=200の範囲と読み込み必須のStageObject配列が返される。
// ---StageObject---
// 座標情報(xとy)と大きさ情報(widthとheight)を持つ
// 当たり判定処理に使うため、StageObjectListインスタンスを渡す
// どんな範囲にあっても必ず読み込みしたいときにはset_collision_load_flag(true)として読み込ませるようにする


import java.util.Observable;

import javax.swing.JLabel;



// プレイヤーや敵または障害物ブロック含むすべてのオブジェクトの親クラス
class StageObject extends Observable {
    // 初期設定ここから
    // 描画用のJLabel
    JLabel object_label;
    // 座標情報
    protected int x, y;
    // サイズに関する情報(このサイズの長方形が当たり判定になる)
    protected int width, height;
    // 物体インスタンスを配列に入れ当たり判定につかう
    protected ObstacleList obstacle_list;
    // 衝突判定の読み込みを必須にするかどうか(読み込み範囲外でも必ず読み込むかどうかのフラグ)
    boolean collision_load_required;
    // コンストラクタ
    public StageObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.obstacle_list = null;
        this.collision_load_required = false;
    }
    // 初期設定ここまで



    // 各辺の始点と終点を取得する関数ここから
    public int[][] get_top_line() {
        return new int[][]{{x, y}, {x+width, y}};
    }
    public int[][] get_right_line() {
        return new int[][]{{x+width, y}, {x+width, y+height}};
    }
    public int[][] get_bottom_line() {
        return new int[][]{{x, y+height}, {x+width, y+height}};
    }
    public int[][] get_left_line() {
        return new int[][]{{x, y}, {x, y+height}};
    }
    // 各辺の始点と終点を取得する関数ここまで



    // 衝突判定の読み込みを必須にするかどうかのフラグ管理ここから
    public void set_collision_load_flag(boolean flag) {
        this.collision_load_required = flag;
        setChanged();
        notifyObservers();
    }
    public boolean is_collision_load_required() {
        return this.collision_load_required;
    }
    // 衝突判定の読み込みを必須にするかどうかのフラグ管理ここまで



    // 障害物リスト関係の処理ここから
    public void set_obstacle_list(ObstacleList obstacle_list) {
        this.obstacle_list = obstacle_list;
    }
    public ObstacleList get_obstacle_list() {
        return this.obstacle_list;
    }
    // 障害物リスト関係の処理ここまで
}
