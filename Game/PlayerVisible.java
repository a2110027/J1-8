import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class PlayerVisible{
  // インスタンス生成
  ImageIcon icon1 = new ImageIcon("./img/character/Player(仮).png");
  JLabel player_lbl = new JLabel(icon1);

  public int player_x ;
  public int player_y ;

  PlayerVisible(int x, int y){
    player_x = x;
    player_y = y;
    player_lbl.setBounds(player_x,player_y,50, 100);
  }

  public JLabel get(){
    return player_lbl;
  }
}
