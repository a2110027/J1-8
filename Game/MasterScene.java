import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/** 
 * 全ての画面を司る。画面遷移にも関係している。
 *Jframeを作成するクラスで、画面遷移だったり再描画の際に他のクラスからJPanelやJLayeredPaneを取り出し、JFrameに描画する。
  @author 綾部
*/
public class MasterScene extends JFrame{
  
  // インスタンス生成
  static MasterScene master = new MasterScene();
  Container contentPane = getContentPane();
  GameScene game = new GameScene();
  Timer tm = new Timer();
  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  GraphicsDevice gd = ge.getDefaultScreenDevice();
  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public MasterScene() {
    super("ゲームウインドウ");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    contentPane.add(game.get_pane());


		//JFrameをフルスクリーンに
		gd.setFullScreenWindow(this);


    // タイマー開始。再描画を行う。
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        game.reload();
        contentPane.add(game.get_pane());
        contentPane.repaint();
			}
		},  0, 100);

  }
  public void end(){
    setVisible(false); 
    dispose(); 
    System.exit(0); 
  }
  static MasterScene get_instance(){
    return master;
  }
}
