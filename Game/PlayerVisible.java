import javax.swing.JPanel;
import java.awt.*;

public class PlayerVisible extends JPanel{
  // 画像読み込み
  Image player_img = Toolkit.getDefaultToolkit().getImage("./img/character/Player(仮).png");
  public int player_x ;
  public int player_y ;
  PlayerVisible(int x, int y){
    player_x = x;
    player_y = y;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // 画像の表示
    g.drawImage(player_img, player_x, player_y, this);
  }
}
