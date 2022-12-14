import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Playerキャラ描画用にする予定
 * @param player_x プレイヤーのx座標
 * @param player_y プレイヤーのy座標
 * @param direction プレイヤーの向き
 * @author 綾部
 */
public class PlayerVisible extends JFrame{
  // インスタンス生成
  ImageIcon icon1 = new ImageIcon("./img/character/Player(仮).png");
  JLabel player_lbl = new JLabel(icon1);

  public int player_x;
  public int player_y;
  public int direction;

/**
 * コンストラクタ
 * 
 * @author 綾部
 */
  PlayerVisible() {
    player_lbl.setBounds(player_x, player_y, 50, 100);
  }

  /**
   * プレイヤーラベルを返す
   *  
   * @author 綾部
   */
  public JLabel get() {
    return player_lbl;
  }

  public int get_x() {
    return player_x;
  }
  public int get_y() {
    return player_y;
  }
  
  /**
   * プレイヤーラベルの座標を変更する
   * @param x プレイヤーのx座標
   * @param y プレイヤーのy座標
   * @author 綾部
   */
  public void set(int x, int y){
    player_x = x;
    player_y = y;
    player_lbl.setBounds(x,y, 50, 100);
  }
}
