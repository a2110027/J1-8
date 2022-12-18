import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * BackGround描画用クラス
 * 水色にしといた
 * 
 * @author 綾部
 */
public class BackGround {
  // インスタンス生成
  ImageIcon sky = new ImageIcon("./img/background/sky.png");
  JLabel sky_lbl = new JLabel(sky);

  /**
   * 背景画像を出力
   * 
   * @return sky_lbl 水色の画像
   * @author 綾部
   */
  public JLabel get_background() {
    sky_lbl.setBounds(0, 0, 1920, 1080);
    return sky_lbl;
  }

}
