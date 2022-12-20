import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Human extends StageObject implements ActionListener {
    protected Speed speed;
    private javax.swing.Timer timer;
    private double TIMER_DERAY;
    private boolean non_move_flag;

    public Human(int x, int y, int width, int height, double init_vx, double init_vy, double max_vx, double max_vy, double default_ax, double default_ay) {
        super(x, y, width, height);
        this.non_move_flag = true;
        this.speed = new Speed(default_ax, default_ay, init_vx, init_vy, max_vx, max_vy);
        this.TIMER_DERAY = 0.03;
        this.timer = new javax.swing.Timer((int)(TIMER_DERAY * 1000), this);
        this.timer.start();
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
        if (this.is_collision()) {
            System.out.println("HumanClass.java; Human.set_position: セットした座標は衝突判定のある座標です。");
            this.x = prev_x;
            this.y = prev_y;
        }
        System.out.println(String.format("(x, y) = (%d, %d), v = (%.3f, %.3f), a = (%.3f, %.3f)", this.x, this.y, this.speed.get_vx(), this.speed.get_vy(), this.speed.get_ax(), this.speed.get_ay()));
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



    // 衝突判定ここから
    public boolean is_collision() {
        if (this.get_stage_object_list() == null) {
            System.out.println("HumanClass.java; Human.is_collision: stage_object_listがnullです");
            return false;
        }
        for (StageObject collision_obj : stage_obj_list.get_collision_list(this.range_x, this.range_y)) {
            
        }
        return false;
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
    

    static Player player = new Player(50, 350, 50, 100);

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, 100, 200, 200, 500, 300, 500);
        player_lbl.setBounds(x, y, width, height);
    }

    /**
     * プレイヤーラベルを返す
     * 
     * @author 綾部
     */
    public JLabel get() {
        return player_lbl;
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
     * プレイヤーラベルの座標を変更する
     * 
     * @param x プレイヤーのx座標
     * @param y プレイヤーのy座標
     * @author 綾部
     */
    public void set(int x_, int y_) {
        x = x_;
        y = y_;
        player_lbl.setBounds(x, y, 50, 100);
    }


    public void draw(Graphics g){
        g.drawImage(img, x, y, player_lbl);
    }
}






class Enemy extends Human {
    public Enemy(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, 100, 100, 200, 200, 200, 200);
    }
}
