import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;


/**
 * スタート画面
 * 
 * @author 綾部
 */
public class StartScene extends JPanel  {

  // インスタンス生成
  MasterScene ms = MasterScene.get_instance();

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public StartScene() {
    setBackground(Color.DARK_GRAY);
    setLayout(null);
  }
  /**
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
