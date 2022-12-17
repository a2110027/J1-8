import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Human extends StageObject implements ActionListener {
    protected Speed speed;
    private javax.swing.Timer timer;
    private double TIMER_DERAY;

    public Human(int x, int y, int width, int height, double max_vx, double max_vy, double default_ax,
            double default_ay, StageObjectsList stage_obj_list) {
        super(x, y, width, height, stage_obj_list);
        this.speed = new Speed(default_ax, default_ay, max_vx, max_vy);
        this.TIMER_DERAY = 0.03;
        this.timer = new javax.swing.Timer((int) (TIMER_DERAY * 1000), this);
        this.timer.start();
    }

    // 時間更新関数ここから
    public void actionPerformed(ActionEvent e) {
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
        System.out.println(String.format("(x, y) = (%d, %d), v = (%.3f, %.3f), a = (%.3f, %.3f)", this.x, this.y,
                this.speed.get_vx(), this.speed.get_vy(), this.speed.get_ax(), this.speed.get_ay()));
        setChanged();
        notifyObservers();
    }
    // セット関数ここまで

    // 移動関数群ここから(non_moveはキーが押されていないときに徐々に速度が0に近づくように実行)
    // ※ジャンプと下移動は実装途中
    public void move_right() {
        this.speed.accelerate_x();
    }

    public void move_left() {
        this.speed.decelerate_x();
    }

    public void jump() {
        this.speed.accelerate_y();
        this.speed.set_v(this.speed.get_vx(), -10);
    }

    public void move_bottom() {
    }

    public void non_move() {
        double current_vx = this.speed.get_vx(), current_vy = this.speed.get_vy();
        // x方向の処理(0に収束するように加速度を調整)
        if (current_vx > 0) {
            this.speed.decelerate_x();
            if (current_vx + this.speed.get_ax() <= 0) {
                this.speed.set_v(0, current_vy);
                this.speed.set_a(0, this.speed.get_ay());
            }
        } else if (current_vx < 0) {
            this.speed.accelerate_x();
            if (current_vx + this.speed.get_ax() >= 0) {
                this.speed.set_v(0, current_vy);
                this.speed.set_a(0, this.speed.get_ay());
            }
        }
        // y方向の処理(0に収束するように加速度を調整)
        if (current_vy > 0) {
            this.speed.decelerate_x();
            if (current_vy + this.speed.get_ay() <= 0) {
                this.speed.set_v(current_vx, 0);
                this.speed.set_a(this.speed.get_ax(), 0);
            }
        } else if (current_vy < 0) {
            this.speed.accelerate_y();
            if (current_vy + this.speed.get_ay() >= 0) {
                this.speed.set_v(current_vx, 0);
                this.speed.set_a(this.speed.get_ax(), 0);
            }
        }
    }
    // 移動関数群ここまで
}

class Player extends Human {

    // インスタンス生成
    ImageIcon icon1 = new ImageIcon("./img/character/Player(仮).png");
    JLabel player_lbl = new JLabel(icon1);

    static Player player = new Player(50, 600, 50, 100, null);

    public Player(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, 200, 200, 200, 10, stage_obj_list);
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

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
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
}

class Enemy extends Human {
    public Enemy(int x, int y, int width, int height, StageObjectsList stage_obj_list) {
        super(x, y, width, height, 100, 100, 10, 10, stage_obj_list);
    }
}
