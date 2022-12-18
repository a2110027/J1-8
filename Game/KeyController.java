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
    GameScene gs = GameScene.get_instance();
    MasterScene ms = MasterScene.get_instance();

    // esc,上下左右,vキー(書き換えメニュー)の入力受付
    @Override
    public void keyPressed(KeyEvent e) {
        // 関数名は適当
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pl.jump();
                System.out.println("jump");
                break;
            case KeyEvent.VK_DOWN:
                pl.move_bottom();
                System.out.println("down");
                break;
            case KeyEvent.VK_LEFT:
                pl.move_left();
                System.out.println("left");
                break;
            case KeyEvent.VK_RIGHT:
                pl.move_right();
                System.out.println("right");
                break;
            case KeyEvent.VK_V:
                System.out.println("V");
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("ESC");
                ms.end();
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
