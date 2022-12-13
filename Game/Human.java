import javax.swing.*;
import java.awt.*;
import java.util.*;



class Human extends StageObject {
    public Human(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, stage_obj_list);
    }

    // 移動関数群ここから(移動に成功したらtrue, 失敗したらfalseを返す)
    // ※ジャンプと下移動は実装途中
    public boolean move_right() {
        if (this.is_collision()) {
            this.x += 1;
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }
    public boolean move_left() {
        if (this.is_collision()) {
            this.x -= 1;
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }
    public boolean jump() {
        if (this.is_collision()) {
            this.y -= 10;
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }
    public boolean move_bottom() {
        if (this.is_collision()) {
            this.y += 10;
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }
}






class Player extends Human {
    public Player(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, stage_obj_list);
    }

    
}






class Enemy extends Human {
    public Enemy(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, stage_obj_list);
    }
}
