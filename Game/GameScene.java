import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画を行う
 * 
 * @author 綾部
 */
public class GameScene extends JFrame {

  // インスタンス生成
  static GameScene gs = new GameScene();
  Player pl = Player.get_instance();
  Stage st = new Stage();
  BackGround bg = new BackGround();
  JLayeredPane p = new JLayeredPane();
  JLabel[][] ar = st.stage_object();
  JPanel panel = new JPanel();
  JScrollPane scrollpane = new JScrollPane();
  JViewport view = scrollpane.getViewport();


  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {


    // 背景描画 layerは1
    p.add(bg.get_background());
    p.setLayer(bg.get_background(), 1);



    // ステージ描画 Layerは2
    for (int i = 0; i < ar.length; i++) {
      for (int j = 0; j <st.length(); j++) {
        ar[i][j].setBounds(j*50, i*50-25, 50, 100);
        p.add(ar[i][j]);
        p.setLayer(ar[i][j], 2);
      }
    }

    // Player描画 Layerは3
    p.add(pl.get());
    p.setLayer(pl.get(),3);



    scrollpane.setPreferredSize(new Dimension(1540, 860));


    view.setView(p);
    view.setViewPosition(new Point(0, 0));

    panel.add(scrollpane);
  }
  /**
   * プレイヤーのみ再描画
   * 
   */
  public void reload(){
    pl.set(pl.get_x(),pl.get_y());
    p.add(pl.get());
    p.setLayer(pl.get(),3);
  }

  /**
   * パネルを返す。
   * @return 現在のJLayeredPane
   */
  public JPanel get_pane(){
    return panel;
  }  


  static  GameScene get_instance(){
    return gs;
  }

}
