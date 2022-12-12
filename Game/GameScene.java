import javax.swing.*;
import java.awt.*;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画
 * 
 * @author 綾部
 */
public class GameScene extends JFrame {

  // インスタンス生成
  PlayerVisible pl = new PlayerVisible(50, 300);
  Stage st = new Stage();
  BackGround bg = new BackGround();
  JLayeredPane p = new JLayeredPane();

  /**
   * コンストラクタ
   * 
   * @param title JFrame(フォーム)のタイトル
   * @author 綾部
   */
  public GameScene(String title) {
    super(title);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1000, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    p.setLayout(null);

    // 背景描画 layerは1
    p.add(bg.get_background());
    p.setLayer(bg.get_background(), 1);


    // ステージ描画 Layerは2
    JLabel[][] ar = st.stage_object();
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 20; j++) {
        p.add(ar[i][j]);
        p.setLayer(ar[i][j], 2);
      }
    }

    // Player描画 Layerは3
    p.add(pl.get());
    p.setLayer(pl.get(),3);


    // JFrame のコンポーネントを取得し、JPanelを追加する。
    Container contentPane = getContentPane();
    contentPane.add(p);
  }
}
