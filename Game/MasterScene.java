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
  KeyController kc = new KeyController();
  CardLayout layout;
  GameScene game; //これだけここで

  // 画面サイズ
  final static int WIDTH = 974;
  final static int HEIGHT = 517;

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
    setSize(WIDTH,HEIGHT);
    setFocusable(true);


    // CardLayout用パネル
    cardpanel = new JPanel();
    layout = new CardLayout();
    cardpanel.setLayout(layout);

    StartScene start = new StartScene();
    new GameScene();
    game = GameScene.gs;
    EndScene end = new EndScene();
    

    cardpanel.add(start, "StartScene");
    cardpanel.add(game, "GameScene");
    cardpanel.add(end, "EndScene");
    contentPane.add(cardpanel);

  }

  public void ChangePanel(String s){
    layout.show(cardpanel, s);
    System.out.println(s + "Yes!");
    if(s == "GameScene"){
      addKeyListener(kc);
      game.gamestart();
    }else if(s == "EndScene"){
      removeKeyListener(kc);
    }else if(s == "StartScene"){
      removeKeyListener(kc);
    }
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
