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

  JLayeredPane p = new JLayeredPane();
  PlayerVisible pl = new PlayerVisible();
  Stage st = new Stage();
  BackGround bg = new BackGround();


  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {

    pl.set(50, 300);
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
    p.setLayer(pl.get(), 3);

    // JFrame のコンポーネントを取得し、JPanelを追加する。
    Container contentPane = getContentPane();
    contentPane.add(p);
  }

  public JLayeredPane set() {
    return p;
  }
}
