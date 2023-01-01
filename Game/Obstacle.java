import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

// 障害物の親クラスここから
public class Obstacle extends StageObject {
    Image img;

    public Obstacle(int x, int y, int width, int height, Image image) {
        super(x, y, width, height);
        img = image;
    }

    public Image get_image() {
        return this.img;
    }
}
// 障害物の親クラスここまで






// 障害物リストのクラスここから
class ObstacleList {
    // 初期設定ここから
    ArrayList<Obstacle> obstacle_list;

    public ObstacleList() {
        obstacle_list = new ArrayList<Obstacle>();
    }
    // 初期設定ここまで

    // ステージオブジェクトを追加する関数ここから(y座標の昇順に格納。同一y座標ではx座標の昇順に格納)
    public void add_obstacle(Obstacle new_obstacle) {
        int targetIndex = 0;
        // 衝突判定の読み込みが必須なら先頭に追加
        if (!new_obstacle.is_collision_load_required()) {
            for (StageObject obj : this.obstacle_list) {
                // 読み込み必須要素は無視する
                if (obj.is_collision_load_required()) {
                    targetIndex++;
                    continue;
                }
                if (new_obstacle.y < obj.y) {
                    break;
                } else if (new_obstacle.y == obj.y && new_obstacle.x < obj.x) {
                    break;
                }
                targetIndex++;
            }
        }

        this.obstacle_list.add(targetIndex, new_obstacle);
    }

    // obstacle_listに格納する関数
    public void load_from_str_arr(String stage_data[][], int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // ブロックごとにインスタンスを追加
                switch (stage_data[i][j]) {
                    case "0":
                        break;
                    case "1":
                        add_obstacle(new Block(j * 32, i * 32, 32, 32));
                    case "2":
                        add_obstacle(new Board(j * 32, i * 32, 32, 32));
                    case "3":
                        add_obstacle(new Needle(j * 32, i * 32, 32, 32));
                    default:
                        break;
                }
            }
        }
        // print_stage_object_list(new int[]{0, 10000}, new int[]{0, 10000});
        System.out.println("set!");
    }
    // ステージオブジェクトを追加する関数ここまで



    // リストの取得関数ここから
    public ArrayList<Obstacle> get_list() {
        return this.obstacle_list;
    }
    // リストの取得関数ここまで



    // 衝突オブジェクトの読み込み関数ここから
    // 範囲に収まる衝突オブジェクトの出力
    public void print_obstacle_list(int range_x[], int range_y[]) {
        int i = 0;
        for (Obstacle obstacle : this.obstacle_list) {
            if (range_y[0] <= obstacle.y && obstacle.y <= range_y[1]) {
                if (range_x[0] <= obstacle.x && obstacle.x <= range_x[1]) {
                    System.out.println(i + ":(x, y) = (" + obstacle.x + ", " + obstacle.y + ")");
                }
            }
            i++;
        }
    }



    // 範囲に収まる衝突オブジェクトのうち始めのインデックスを返す
    public int search_range_first_index(int y) {
        int start = 0;
        for (Obstacle obstacle : this.obstacle_list) {
            if (obstacle.is_collision_load_required()) {
                start++;
            } else if (obstacle.y >= y) {
                break;
            } else {
                start++;
                continue;
            }
        }
        return start;
    }
    public ArrayList<Obstacle> get_collision_list(int range_x[], int range_y[]) {
        // System.out.println(String.format("x: %d~%d, y: %d~%d", range_x[0],
        // range_x[1], range_y[0], range_y[1]));
        ArrayList<Obstacle> result = new ArrayList<Obstacle>();
        for (Obstacle obstacle : this.obstacle_list) {
            if (obstacle.is_collision_load_required()) {
                result.add(obstacle);
            } else {
                break;
            }
        }
        int start_index = search_range_first_index(range_y[0]);
        for (int i = start_index; i < this.obstacle_list.size(); i++) {
            Obstacle obj = this.obstacle_list.get(i);
            if (range_y[0] <= obj.y && obj.y <= range_y[1]) {
                if (range_x[0] <= obj.x && obj.x <= range_x[1]) {
                    result.add(obj);
                }
            }
        }
        return result;
    }
    // 衝突オブジェクトの読み込み関数ここまで



    // リストの初期化関数ここから
    public void init_list() {
        
    }
    // リストの初期化関数ここまで
}
// 障害物リストのクラスここまで






// 通常ブロックここから
class Block extends Obstacle {
    public Block(int x, int y, int width, int height) {
        super(x, y, width, height, Toolkit.getDefaultToolkit().getImage("./img/object/GroundBlock.png"));
    }
}
// 通常ブロックここまで






// 板ブロックここから
class Board extends Obstacle {
    public Board(int x, int y, int width, int height) {
        super(x, y, width, height, Toolkit.getDefaultToolkit().getImage("./img/object/Board.png"));
    }
}
// 板ブロックここまで






// 棘ブロックここから
class Needle extends Obstacle {
    public Needle(int x, int y, int width, int height) {
        super(x, y, width, height, Toolkit.getDefaultToolkit().getImage("./img/object/Needle.png"));
    }
}
// 棘ブロックここまで
