import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Human extends StageObject implements ActionListener {
    protected Speed speed;
    //private javax.swing.Timer timer; 12/23変更点、注意(by Fuki)
    public javax.swing.Timer timer;
    private double TIMER_DERAY;
    private boolean non_move_flag;

    public Human(int x, int y, int width, int height, double init_vx, double init_vy, double max_vx, double max_vy, double default_ax, double default_ay) {
        super(x, y, width, height);
        this.non_move_flag = true;
        this.speed = new Speed(default_ax, default_ay, init_vx, init_vy, max_vx, max_vy);
        this.TIMER_DERAY = 0.03;
        this.timer = new javax.swing.Timer((int)(TIMER_DERAY * 1000), this);
        //this.timer.start(); 12/23変更点、注意(by Fuki)
    }

    // 時間更新関数ここから
    public void actionPerformed(ActionEvent e) {
        if (this.non_move_flag) {
            this.non_move();
        }
        int new_x = (int) (x + speed.get_vx() * TIMER_DERAY + 0.5 * speed.get_ax() * TIMER_DERAY * TIMER_DERAY);
        int new_y = (int) (y + speed.get_vy() * TIMER_DERAY + 0.5 * speed.get_ay() * TIMER_DERAY * TIMER_DERAY);
        double new_v_x = speed.get_vx() + speed.get_ax() * TIMER_DERAY;
        double new_v_y = speed.get_vy() + speed.get_ay() * TIMER_DERAY;
        this.speed.set_v(new_v_x, new_v_y);
        this.set_position(new_x, new_y);
    }
    // 時間更新関数ここまで



    // セット関数ここから
    public void set_position(int x, int y) {
        int prev_x = this.x, prev_y = this.y;
        this.x = x;
        this.y = y;
        // 衝突処理
        String collision_judge = is_collision();
        switch (collision_judge) {
            case "top":
                this.y = prev_y+1;
                speed.set_v(speed.get_vx(), 0);
                speed.set_a(speed.get_ax(), speed.get_ay());
                // break;
            case "right":
                this.x = prev_x-1;
                speed.set_v(0, speed.get_vy());
                speed.set_a(0, speed.get_ay());
                // break;
            case "bottom":
                this.y = prev_y;
                speed.set_v(speed.get_vx(), 0);
                speed.set_a(speed.get_ax(), 0);
                // break;
            case "left":
                this.x = prev_x+1;
                speed.set_v(0, speed.get_vy());
                speed.set_a(0, speed.get_ay());
                // break;
            default:
                if (collision_judge != "not") {
                    System.out.println("collision: "+collision_judge);
                }
            break;
        }
        // if (this.is_collision()) {
        //     System.out.println("HumanClass.java; Human.set_position: セットした座標は衝突判定のある座標です。");
        //     this.x = prev_x;
        //     this.y = prev_y;
        // }
        // System.out.println(String.format("(x, y) = (%d, %d), v = (%.3f, %.3f), a = (%.3f, %.3f)", this.x, this.y, this.speed.get_vx(), this.speed.get_vy(), this.speed.get_ax(), this.speed.get_ay()));
        setChanged();
        notifyObservers();
    }
    public void set_non_move_flag(boolean flag) {
        this.non_move_flag = flag;
    }
    // セット関数ここまで



    // 取得関数群ここから
    public int get_x() {
        return this.x;
    }
    public int get_y() {
        return this.y;
    }
    // 取得関数群ここまで



    /**
     * 衝突判定ここから
     * @return 衝突場所によって"top", "right", "bottom", "left", "not"が返される
     */
    public String is_collision() {
        if (this.get_stage_object_list() == null) {
            System.out.println("HumanClass.java; Human.is_collision: stage_object_listがnullです");
            return "not";
        }
        // 衝突座標の許容範囲の設定(移動するとき座標変化は1ずつではないため許容範囲を設ける必要がある)
        int tolerate_range = 10;
        // Humanの各辺の始点と終点座標の設定([[x, y], [x, y]]という2次元配列で保存)
        int human_top_line[][] = new int[][]{{x, y}, {x+width, y}};
        int human_right_line[][] = new int[][]{{x+width, y}, {x+width, y+height}};
        int human_bottom_line[][] = new int[][]{{x, y+height}, {x+width, y+height}};
        int human_left_line[][] = new int[][]{{x, y}, {x, y+height}};
        for (StageObject collision_obj : stage_obj_list.get_collision_list(this.range_x, this.range_y)) {
            int obj_top_line[][] = collision_obj.get_top_line();
            int obj_right_line[][] = collision_obj.get_right_line();
            int obj_bottom_line[][] = collision_obj.get_bottom_line();
            int obj_left_line[][] = collision_obj.get_left_line();
            // System.out.println(String.format("human:(%d, %d) (%d, %d) (%d, %d) (%d, %d)", human_top_line[0][0], human_top_line[0][1], human_right_line[0][0], human_right_line[0][1], human_bottom_line[1][0], human_bottom_line[1][1], human_left_line[1][0], human_left_line[1][1]));
            // System.out.println(String.format("obj:(%d, %d) (%d, %d) (%d, %d) (%d, %d)", obj_top_line[0][0], obj_top_line[0][1], obj_right_line[0][0], obj_right_line[0][1], obj_bottom_line[1][0], obj_bottom_line[1][1], obj_left_line[1][0], obj_left_line[1][1]));
            // System.out.println(stage_obj_list.get_collision_list(range_x, range_y).size());
            // 上衝突の判定
            if (obj_bottom_line[0][1]-tolerate_range<=human_top_line[0][1] && human_top_line[0][1]<=obj_bottom_line[0][1]) {
                System.out.println(String.format("human-top:(%d, %d)~(%d, %d)", human_top_line[0][0], human_top_line[0][1], human_top_line[1][0], human_top_line[1][1]));
                System.out.println(String.format("obj-bottom:(%d, %d)~(%d, %d)", obj_bottom_line[0][0], obj_bottom_line[0][1], obj_bottom_line[1][0], obj_bottom_line[1][1]));
                if ((obj_bottom_line[0][0]<=human_top_line[0][0] && human_top_line[0][0]<=obj_bottom_line[1][0]) || (obj_bottom_line[0][0]<=human_top_line[1][0] && human_top_line[1][0]<=obj_bottom_line[1][0])) {
                    return "top";
                }
            }
            // 右衝突の判定
            if (obj_left_line[0][0]<=human_right_line[0][0] && human_right_line[0][0]<=obj_left_line[0][0]+tolerate_range) {
                System.out.println(String.format("human-right:(%d, %d)~(%d, %d)", human_right_line[0][0], human_right_line[0][1], human_right_line[1][0], human_right_line[1][1]));
                System.out.println(String.format("obj-left:(%d, %d)~(%d, %d)", obj_left_line[0][0], obj_left_line[0][1], obj_left_line[1][0], obj_left_line[1][1]));
                if ((obj_left_line[0][1]<=human_right_line[0][1] && human_right_line[0][1]<=obj_left_line[1][1]) || (obj_left_line[0][1]<=human_right_line[1][1] && human_right_line[1][1]<=obj_left_line[1][1])) {
                    return "right";
                }
            }
            // 下衝突の判定
            if (obj_top_line[0][1]<=human_bottom_line[0][1] && human_bottom_line[0][1]<=obj_top_line[0][1]+tolerate_range) {
                System.out.println(String.format("human-bottom:(%d, %d)~(%d, %d)", human_bottom_line[0][0], human_bottom_line[0][1], human_bottom_line[1][0], human_bottom_line[1][1]));
                System.out.println(String.format("obj-top:(%d, %d)~(%d, %d)", obj_top_line[0][0], obj_top_line[0][1], obj_top_line[1][0], obj_top_line[1][1]));
                if ((obj_top_line[0][0]<=human_bottom_line[0][0] && human_bottom_line[0][0]<=obj_top_line[1][0]) || (obj_top_line[0][0]<=human_bottom_line[1][0] && human_bottom_line[1][0]<=obj_top_line[1][0])) {
                    return "bottom";
                }
            }
            // 左衝突の判定
            if (obj_right_line[0][0]-tolerate_range<=human_left_line[0][0] && human_left_line[0][0]<=obj_right_line[0][0]) {
                System.out.println(String.format("human-left:(%d, %d)~(%d, %d)", human_left_line[0][0], human_left_line[0][1], human_left_line[1][0], human_left_line[1][1]));
                System.out.println(String.format("obj-right:(%d, %d)~(%d, %d)", obj_right_line[0][0], obj_right_line[0][1], obj_right_line[0][0], obj_right_line[1][1]));
                if ((obj_right_line[0][1]<=human_left_line[0][1] && human_left_line[0][1]<=obj_right_line[1][1]) || (obj_right_line[0][1]<=human_left_line[1][1] && human_left_line[1][1]<=obj_right_line[1][1])) {
                    return "left";
                }
            }
        }
        return "not";
    }
    // 衝突判定ここまで



    // 移動関数群ここから(non_moveはキーが押されていないときに徐々に速度が0に近づくように実行)
    // ※ジャンプと下移動は実装途中
    public void move_right() {
        this.set_non_move_flag(false);
        this.speed.accelerate_x();
        double vx0 = 0;
        if(this.speed.get_vx()<10 && this.speed.get_vx()>-10) {
            vx0 = this.speed.get_init_vx();
            this.speed.set_v(vx0+this.speed.get_vx(), this.speed.get_vy());
        }
    }

    public void move_left() {
        this.set_non_move_flag(false);
        this.speed.decelerate_x();
        double vx0 = 0;
        if(this.speed.get_vx()<10 && this.speed.get_vx()>-10) {
            vx0 = -this.speed.get_init_vx();
        }
        this.speed.set_v(vx0+this.speed.get_vx(), this.speed.get_vy());
    }

    public void jump() {
        this.set_non_move_flag(false);
        this.speed.accelerate_y();
        this.speed.set_v(this.speed.get_vx(), -this.speed.get_init_vy());
    }

    public void move_bottom() {
        this.set_non_move_flag(false);
    }

    public void non_move() {
        double current_vx = this.speed.get_vx(), current_vy = this.speed.get_vy();
        // x方向の処理(0に収束するように加速度を調整)
        if (current_vx > 0) {
            this.speed.set_a(-this.speed.get_default_ax()/1.1, this.speed.get_ay());
            if (current_vx + this.speed.get_ax()*TIMER_DERAY <= 0) {
                this.speed.set_v(0, current_vy);
                this.speed.set_a(0, this.speed.get_ay());
            }
        } else if (current_vx < 0) {
            this.speed.set_a(this.speed.get_default_ax()/1.1, this.speed.get_ay());
            if (current_vx + this.speed.get_ax()*TIMER_DERAY >= 0) {
                this.speed.set_v(0, current_vy);
                this.speed.set_a(0, this.speed.get_ay());
            }
        }
    }
    // 移動関数群ここまで
}






class Player extends Human {

    // インスタンス生成
    Image img = Toolkit.getDefaultToolkit().getImage("./img/character/Player(仮).png");
    ImageIcon icon1 = new ImageIcon("./img/character/Player(仮).png");
    JLabel player_lbl = new JLabel(icon1);
    

    static Player player = new Player(32, 384, 50, 100);

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, 100, 200, 200, 500, 300, 500);
    }

    /**
     * これを使って生成することで、どこからでも自由にplayer_x, player_yを変更することが出来る。
     * 速度とかも持たせる予定だから、オブジェクトを返さなくてはいけない
     * 
     * @author 綾部
     * @return 生成されたオブジェクトを返す
     */
    public static Player get_instance() {
        return player;
    }

    /**
     * プレイヤーの座標を変更する
     * 
     * @param x プレイヤーのx座標
     * @param y プレイヤーのy座標
     * @author 綾部
     */
    public void set(int x_, int y_) {
        x = x_;
        y = y_;
    }


    /**
     * プレイヤー描画
     * @param g
     */
    public void draw(Graphics g, int offset){
        g.drawImage(img, x-offset, y, player_lbl);
    }
}






class Enemy extends Human {
    public Enemy(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, 100, 100, 200, 200, 200, 200);
    }
}
