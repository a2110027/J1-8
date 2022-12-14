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

    Player player = new Player(50, 300, 50, 100, null);
    PlayerVisible pl = PlayerVisible.get_instance();


    // esc,上下左右,vキー(書き換えメニュー)の入力受付
    @Override
    public void keyPressed(KeyEvent e) {
        // 関数名は適当
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // player.jump();
                pl.set(pl.get_x(),pl.get_y()-5);
                //System.out.println(y);
                System.out.println("jump");
                break;
            case KeyEvent.VK_DOWN:
                // player.move_bottom();
                pl.set(pl.get_x(),pl.get_y()+5);
                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                // player.move_left();
                pl.set(pl.get_x()-5,pl.get_y());
                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                // player.move_right();
                pl.set(pl.get_x()+5,pl.get_y());
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
