import javax.swing.*;
import java.awt.*;


/** 
 * 全ての画面を司る。画面遷移にも関係している。
 *Jframeを作成するクラスで、画面遷移だったり再描画の際に他のクラスからJPanelやJLayeredPaneを取り出し、JFrameに描画する。
  @author 綾部
*/
public class MasterScene extends JFrame{
  
  // インスタンス生成
  static MasterScene master = new MasterScene();
  Container contentPane = getContentPane();
  JPanel cardpanel;
  CardLayout layout;
  GameScene game; //これだけここで

<<<<<<< HEAD
  //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
  //GraphicsDevice gd = ge.getDefaultScreenDevice();
=======
  // 画面サイズ
  final static int WIDTH = 974;
  final static int HEIGHT = 517;

>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859
  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public MasterScene() {
    super("ゲームウインドウ");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //setLocationRelativeTo(null);
    setResizable(false);
<<<<<<< HEAD
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
=======
    setSize(WIDTH,HEIGHT);
    setFocusable(true);


    // CardLayout用パネル
    cardpanel = new JPanel();
    layout = new CardLayout();
    cardpanel.setLayout(layout);

    StartScene start = new StartScene();
    new GameScene();
    game = GameScene.gs;
    

    cardpanel.add(start, "StartScene");
    cardpanel.add(game, "GameScene");
    
    contentPane.add(cardpanel);

  }

  public void ChangePanel(String s){
    layout.show(cardpanel, s);
    System.out.println(s + "Yes!");
>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859

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
<<<<<<< HEAD
  
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
=======
  static MasterScene get_instance(){
    return master;
>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859
  }
}
