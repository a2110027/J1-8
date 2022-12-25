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


import java.util.ArrayList;
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
    protected StageObjectsList stage_obj_list;
    // 衝突判定の読み込み範囲
    int range_x[], range_y[];
    // 衝突判定の読み込みを必須にするかどうか(読み込み範囲外でも必ず読み込むかどうかのフラグ)
    boolean collision_load_required;
    // コンストラクタ
    public StageObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stage_obj_list = null;
        this.collision_load_required = false;
        // 衝突判定の読み込み限界は当たり判定の4倍程
        this.range_x = new int[]{this.x-(width*4), this.x+(width*4)};
        this.range_y = new int[]{this.y-(height*4), this.y+(height*4)};
    }
    // 初期設定ここまで



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
    public void set_stage_object_list(StageObjectsList stage_object_list_instance) {
        this.stage_obj_list = stage_object_list_instance;
    }
    public StageObjectsList get_stage_object_list() {
        return this.stage_obj_list;
    }
    // 障害物リスト関係の処理ここまで
}






class StageObjectsList {
    // 初期設定ここから
    ArrayList<StageObject> stage_obj_list;
    public StageObjectsList() {
        stage_obj_list = new ArrayList<StageObject>();
    }
    // 初期設定ここまで



    // ステージオブジェクトを追加する関数ここから(y座標の昇順に格納。同一y座標ではx座標の昇順に格納)
    public void add_stage_object(StageObject new_obj) {
        int targetIndex = 0;
        // 衝突判定の読み込みが必須なら先頭に追加
        if (!new_obj.is_collision_load_required()) {
            for (StageObject obj : this.stage_obj_list) {
                // 読み込み必須要素は無視する
                if (obj.is_collision_load_required()) {
                    targetIndex++;
                    continue;
                }
                if (new_obj.y < obj.y) {
                    break;
                } else if (new_obj.y == obj.y && new_obj.x < obj.x) {
                    break;
                }
                targetIndex++;
            }
        }

        this.stage_obj_list.add(targetIndex, new_obj);
    }
    // ステージオブジェクトを追加する関数ここまで



    // 衝突オブジェクトの読み込み関数ここから
    // 範囲に収まる衝突オブジェクトの出力
    public void print_stage_object_list(int range_x[], int range_y[]) {
        int i = 0;
        for (StageObject stage_obj : this.stage_obj_list) {
            if (range_y[0] <= stage_obj.y && stage_obj.y <= range_y[1]) {
                if (range_x[0] <= stage_obj.x && stage_obj.x <= range_x[1]) {
                    System.out.println(i+":(x, y) = ("+stage_obj.x+", "+stage_obj.y+")");
                }
            }
            i++;
        }
    }
    // 範囲に収まる衝突オブジェクトのうち始めのインデックスを返す
    public int search_range_first_index(int y) {
        int start = 0;
        for (StageObject stage_obj : this.stage_obj_list) {
            if (stage_obj.is_collision_load_required()) {
                start++;
            } else if (stage_obj.y >= y) {
                break;
            } else {
                start++;
                continue;
            }
        }
        return start;
    }
    public ArrayList<StageObject> get_collision_list(int range_x[], int range_y[]) {
        ArrayList<StageObject> result = new ArrayList<StageObject>();
        for (StageObject stage_obj : this.stage_obj_list) {
            if (stage_obj.is_collision_load_required()) {
                result.add(stage_obj);
            } else {
                break;
            }
        }
        int start_index = search_range_first_index(range_y[0]);
        for (int i=start_index; i<this.stage_obj_list.size(); i++) {
            StageObject obj = this.stage_obj_list.get(i);
            if (range_y[0] <= obj.y && obj.y <= range_y[1]) {
                if (range_x[0] <= obj.y && obj.y <= range_x[1]) {
                    result.add(obj);
                }
            }
        }
        return result;
    }
    // 衝突オブジェクトの読み込み関数ここまで
}


/**
 * 通常のブロックのクラス
 */
class Block extends StageObject{
    public Block(int x, int y, int width, int height){
        super(x, y, width, height);
    }
}

/**
 * 下にくぐれる板のクラス
 */
class Board extends StageObject{
    public Board(int x, int y, int width, int height){
        super(x, y, width, height);
    }
}


/**
 * 上向きの針のクラス。当たったら死亡する。
 */
class Needle extends StageObject{
    public Needle(int x, int y, int width, int height){
        super(x, y, width, height);
    }
}

