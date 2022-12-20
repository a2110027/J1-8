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
  
  Image sky = Toolkit.getDefaultToolkit().getImage("./img/background/sky.png");
  //ImageIcon sky = new ImageIcon("./img/background/sky.png");
  //JLabel sky_lbl = new JLabel(sky);

  /**
   * 背景画像を出力
   * 
   * @return sky_lbl 水色の画像
   * @author 綾部
   */
  public void get_background() {
    return ;
  }

  public void draw(Graphics g, int offset){
    g.drawImage(sky, 0 - offset, 0, this);
    //g.setColor(Color.BLUE);
    //g.fillRect(0 - offset, 0, 1014,537);
  }
}
