import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

/** 
 * 全ての画面を司る。画面遷移にも関係している。
 *Jframeを作成するクラスで、画面遷移だったり再描画の際に他のクラスからJPanelやJLayeredPaneを取り出し、JFrameに描画する。
  @author 綾部
*/
public class MasterScene extends JFrame implements ActionListener{
  
  // インスタンス生成
  static MasterScene master = new MasterScene();
  GameScene game = new GameScene();
  StartScene start = new StartScene();
  Timer tm = new Timer();
  KeyController kc = new KeyController();
  
  JButton start_button = new JButton("Start");
  
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


    // ほんとはここら辺はStartSceneに組み込みたい
    start_button.addActionListener(this);
    start_button.setActionCommand("GameStart");
    start_button.setBounds(400,200,200,100 );
    start.add(start_button);
    //

    // スタート画面を作成し、キー入力を受付てフォーカスさせる。
    add(start);
    addKeyListener(kc);
    setFocusable(true);
  }
  public void end(){
    System.exit(0); 
  }
  static MasterScene get_instance(){
    return master;
  }

  /**
   * 画面再描画のタイマーを起動する。
   */
  public void timerstart(){
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        add(game);
        repaint();
			}
		},  0, 100);

  }

  /**
   * panel1からpanel2へ遷移する
   * @param panel1 遷移前
   * @param panel2 遷移後
   * @author 綾部
   */
  public void panel_change(JPanel panel1, JPanel panel2){
    remove(panel1);
    add(panel2);
    repaint();
    validate(); 
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
