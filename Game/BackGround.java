import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * BackGround描画用クラス
 * 水色にしといた
 * 
 * @author 綾部
 */
public class BackGround extends JPanel{
  // インスタンス生成
  
  Image sky = Toolkit.getDefaultToolkit().getImage("./img/background/room.png");


  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public void get_background() {
    return ;
  }

  
  /**
   * 背景描写
   * @param g
   * @param offset 横スクロールする際に、背景もスクロールできるようにする為の値
   */
  public void draw(Graphics g, int offset){
    g.drawImage(sky,  -offset/2, 0, this);
  }
}
