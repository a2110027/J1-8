import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyController implements KeyListener{
    //メインのフレームに追加する
    main_frame.addKeyListener(this);
    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */

    //esc,上下左右,vキー(書き換えメニュー)の入力受付
    @Override
    public void keyPressed(KeyEvent e){
        //関数名は適当
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                push_up();
            break;
            case KeyEvent.VK_DOWN:
               push_down();
            break;
            case KeyEvent.VK_LEFT:
               push_lefe();
            break;
            case KeyEvent.VK_RIGHT:
                push_right();
            break;
            case KeyEvent.VK_V:
                push_v()
            break;
            case KeyEvent.VK_ESCAPE:
                push_escape();
            break;
        }
    }
//下２つは使わない
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}

    
