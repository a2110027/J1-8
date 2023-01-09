import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class KeyController implements KeyListener, ActionListener {
    // メインのフレームに追加する
    // main_frame.addKeyListener(this);
    /*
    * (non-Javadoc)
    *
    * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
    */

    Player pl = Player.get_instance();
    GameScene gs = GameScene.get_instance();
    MasterScene ms = MasterScene.get_instance();
    Timer timer = new javax.swing.Timer((30),this);
    //入力をフラグで判定する
    boolean right_frag = false;
    boolean left_frag = false;
    boolean up_frag = false;
    boolean down_frag = false;
    boolean v_frag = false;
    boolean esc_frag = false;
    boolean rl_frag = false;

    public KeyController(){
        timer.start();
        }
        @Override
        public void actionPerformed(ActionEvent e){
        Hantei();
    }

    // esc,上下左右,vキー(書き換えメニュー)の入力受付
    @Override
    public void keyPressed(KeyEvent e) {
        // 関数名は適当
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        //pl.jump();
        //System.out.println("jump");
        up_frag = true;
        break;
        case KeyEvent.VK_DOWN:
        //pl.move_bottom();
        //System.out.println("down");
        down_frag = true;
        break;
        case KeyEvent.VK_LEFT:
        //pl.move_left();
        //System.out.println("left");
        left_frag = true;
        break;
        case KeyEvent.VK_RIGHT:
        //pl.move_right();
        //System.out.println("right");
        right_frag = true;
        break;
        case KeyEvent.VK_V:
        //System.out.println("V");
        v_frag = true;
        break;
        case KeyEvent.VK_ESCAPE:
        //System.out.println("ESC");
        esc_frag = true;
        //ms.end();
        break;
        }
    }

    // fragをfalseにする
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        //pl.set_non_move_flag(true);
        up_frag = false;
        break;
        case KeyEvent.VK_DOWN:
        //pl.set_non_move_flag(true);
        down_frag = false;
        break;
        case KeyEvent.VK_LEFT:
        //pl.set_non_move_flag(true);
        left_frag = false;
        break;
        case KeyEvent.VK_RIGHT:
        //pl.set_non_move_flag(true);
        right_frag = false;
        break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    /*
    if(up_frag == false){
    pl.set_non_move_flag(true);
    }
    if(down_frag == false){
    pl.set_non_move_flag(true);
    }*/
    //動くときの判定
    public void Hantei(){
        if(up_frag == true){
            pl.jump();
            //System.out.println("Jump");
        }
        if(down_frag == true){
            pl.run_count_increment();
            pl.move_bottom();
        }
        if(right_frag == true){
            if(left_frag==false){
                pl.run_count_increment();
                pl.move_right();
            }
        //System.out.println("RIGHT");
            if(left_frag == true){
                pl.run_count_increment();
                pl.set_non_move_flag(true);
            }
        }
        if(left_frag == true){
            if(right_frag==false){
                pl.run_count_increment();
                pl.move_left();}
            if(right_frag==true){
                pl.set_non_move_flag(true);
            }
            //System.out.println("LEFT");
        }
        if(esc_frag == true){
            System.exit(0); 
        }
        //動かないときの判定
        if(left_frag == false && right_frag == false){
            pl.set_non_move_flag(true);
        }
        /*if(right_frag == true && left_frag == true){
        pl.set_non_move_flag(true);
        }
        if(up_frag == false){
        pl.set_non_move_flag(true);
        }
        if(down_frag == false){
        pl.set_non_move_flag(true);
        }*/
    }
}
