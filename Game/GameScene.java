import java.awt.Dimension;
import java.awt.Point;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画を行う
 * 
 * @author 綾部
 */
public class GameScene {

  // インスタンス生成
  static GameScene gs = new GameScene();
  Player pl = Player.get_instance();
  Stage st = new Stage();
  Timer tm = new Timer();
  BackGround bg = new BackGround();
  JLayeredPane p = new JLayeredPane();
  JLabel[][] ar = st.stage_object();
  MasterScene ms = MasterScene.get_instance();
  //MasterScene ms;

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {


    // 各stageObjectクラスにstageObjectListを付与
    pl.set_stage_object_list(st.get_stage_object_list());


    // 背景描画 layerは1
    p.add(bg.get_background());
    p.setLayer(bg.get_background(), 1);



    // ステージ描画 Layerは2
    for (int i = 0; i < ar.length; i++) {
      for (int j = 0; j <st.length(); j++) {
        ar[i][j].setBounds(j*50, i*50, 50, 50);
        p.add(ar[i][j]);
        p.setLayer(ar[i][j], 2);
      }
    }

    // Player描画 Layerは3
    p.add(pl.get());
    p.setLayer(pl.get(),3);

    // タイマー開始。再描画を行う。
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        reload();
        //ms = MasterScene.get_instance();
        //ms.contentPane.add(get_pane());
        //ms.contentPane.repaint();

        ms.get_contentpane().add(get_pane());
        ms.get_contentpane().repaint();
			}
		},  0, 100);
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
  public JLayeredPane get_pane(){
    return p;
  }  

/**
 * インスタンスを返す
 * @return インスタンス
 */
  static  GameScene get_instance(){
    return gs;
  }

}
