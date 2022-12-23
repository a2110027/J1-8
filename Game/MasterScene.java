import javax.swing.*;
import java.awt.*;


/** 
 * 全ての画面を司る。画面遷移にも関係している。
 *Jframeを作成するクラスで、画面遷移だったり再描画の際に他のクラスからJPanelやJLayeredPaneを取り出し、JFrameに描画する。
  @author 綾部
*/
public class MasterScene extends JFrame implements ActionListener{
  
  // インスタンス生成
  static MasterScene master = new MasterScene();
  Container contentPane = getContentPane();
  JPanel cardpanel;
  CardLayout layout;
  GameScene game; //これだけここで

  //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  //GraphicsDevice gd = ge.getDefaultScreenDevice();
  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public MasterScene() {
    super("ゲームウインドウ");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //なんでか真ん中で表示されないので切ってる。
    //setLocationRelativeTo(null);

    setResizable(false);
    setSize(1014,537);
    contentPane.add(game);
    
		//JFrameをフルスクリーンに
		//gd.setFullScreenWindow(this);
  
    // ↓戻す
    addKeyListener(game);

    // ↓戻す
    //タイマー開始。再描画を行う。
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        contentPane.add(game);
        contentPane.repaint();
			}
		},  0, 100);

    if(s == "GameScene"){
      addKeyListener(new KeyController());
      game.gamestart();
    }
  }


  public void end(){
    setVisible(false); 
    dispose(); 
    System.exit(0); 
  }
  
  /**
   * 画面遷移を感知する
   * もし画面遷移がGameStartのときは、タイマー(画面描画)を開始する
   * @author 綾部
   */
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if(cmd == "GameStart"){
      panel_change(start, game);
      timerstart();

    } 
  }
}
