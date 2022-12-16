import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyController implements KeyListener {
    // メインのフレームに追加する
    // main_frame.addKeyListener(this);
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */

    Player pl = Player.get_instance();


    // esc,上下左右,vキー(書き換えメニュー)の入力受付
    @Override
    public void keyPressed(KeyEvent e) {
        // 関数名は適当
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pl.jump();
                // pl.set(pl.get_x(),pl.get_y());
                //System.out.println(y);
                System.out.println("jump");
                break;
            case KeyEvent.VK_DOWN:
                pl.move_bottom();
                // pl.set(pl.get_x(),pl.get_y());
                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                pl.move_left();
                // pl.set(pl.get_x(),pl.get_y());
                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                pl.move_right();
                // pl.set(pl.get_x(),pl.get_y());
                System.out.println("right");
                break;
            case KeyEvent.VK_V:
                System.out.println("V");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("ESC");
                break;
        }
    }

    // 下２つは使わない
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pl.set_non_move_flag(true);
                break;
            case KeyEvent.VK_DOWN:
                pl.set_non_move_flag(true);
                break;
            case KeyEvent.VK_LEFT:
                pl.set_non_move_flag(true);
                break;
            case KeyEvent.VK_RIGHT:
                pl.set_non_move_flag(true);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
