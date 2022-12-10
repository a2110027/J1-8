import javax.swing.JPanel;
import java.awt.*;

public class Block extends JPanel{
  // 画像読み込み
  Image block_img = Toolkit.getDefaultToolkit().getImage("./img/object/Block(仮).png");
  int loc_x;
  int loc_y;
  Block(int x, int y){ 
    loc_x = x;
    loc_y = y;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // 画像の表示
    g.drawImage(block_img, loc_x, loc_y, this);
  }
}
