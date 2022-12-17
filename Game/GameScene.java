import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画
 * 
 * @author 綾部
 */
public class GameScene extends JFrame {

  // インスタンス生成
  Player pl = Player.get_instance();
  Stage st = new Stage();
  BackGround bg = new BackGround();
  JLayeredPane p = new JLayeredPane();
  Timer tm = new Timer();
  Container contentPane = getContentPane();

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {
    super("ゲームウインドウ");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1920, 1080);
    setLocationRelativeTo(null);
    setResizable(false);
    p.setLayout(null);

    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        reload();
			}
		},  0, 50);

    // 背景描画 layerは1
    p.add(bg.get_background());
    p.setLayer(bg.get_background(), 1);



    // ステージ描画 Layerは2
    JLabel[][] ar = st.stage_object();
    for (int i = 0; i < ar.length; i++) {
      for (int j = 0; j <st.length(); j++) {

        p.add(ar[i][j]);
        p.setLayer(ar[i][j], 2);
      }
    }
    System.out.println(ar.length);

    // Player描画 Layerは3
    p.add(pl.get());
    p.setLayer(pl.get(),3);

    // 描画
    contentPane.add(p);
  }
  /**
   * プレイヤーのみ再描画
   * 
   */
  public void reload(){
    pl.set(pl.get_x(),pl.get_y());
    p.add(pl.get());
    p.setLayer(pl.get(),3);
    contentPane.add(p);
    contentPane.repaint();
  }
}
