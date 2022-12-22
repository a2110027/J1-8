import javax.swing.*;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画を行う
 * 
 * @author 綾部
 */
public class GameScene extends JPanel{

  // インスタンス生成
  static GameScene gs = new GameScene();
  Player player = Player.get_instance();
  Stage st = new Stage();
  BackGround bg = new BackGround();
  Timer tm = new Timer();
  MasterScene ms = MasterScene.get_instance();

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {

    // 各stageObjectクラスにstageObjectListを付与
    player.set_stage_object_list(st.get_stage_object_list());

  }

  public void gamestart(){
    System.out.println("gamestart!\n");
    //タイマー開始。再描画を行う。
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        //contentPane.add(game);
        //contentPane.repaint();
			}
		},  0, 100);
  }

  /**
   * インスタンスを返す
   * 
   * @return インスタンス
   */
  static GameScene get_instance() {
    return gs;
  }


  /* ***************************************** */
  /**
   * 描画部分
   * @param offset ステージや背景(未実装)のスクロールをするための値
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int offset = this.player.get_x();
    offset = Math.min(offset, 1550);
    offset = Math.max(offset-400, 0);
    bg.draw(g, 0);
    st.draw(g, offset);
    player.draw(g);
  }

}