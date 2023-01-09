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

  KeyController kyecont; //今現在設定中のkey操作を表現
  //以下、使用しうるkey操作をすべてインスタンス化する
  KeyController GSkey = new KeyController(); //GameSceneで使用

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
    BetweenScene bet = new BetweenScene();
    

    cardpanel.add(start, "StartScene");
    cardpanel.add(game, "GameScene");
    cardpanel.add(end, "EndScene");
    cardpanel.add(bet, "BetweenScene");
    contentPane.add(cardpanel);

  }
  /** 
   * パネルを変える
   * @param s
   */
  public void ChangePanel(String s){
    layout.show(cardpanel, s);
    System.out.println(s + "Yes!");
    if(s == "GameScene"){
      kyecont = GSkey;
      addKeyListener(kyecont); //keyを更新
      game.gamestart();
    }else if(s == "EndScene"){
      removeKeyListener(kyecont);
    }else if(s == "StartScene"){
      game.reset_score();
      removeKeyListener(kyecont);
    }else if(s =="BetweenScene"){
    }
  }

  static MasterScene get_instance(){
    return master;
  }
}
