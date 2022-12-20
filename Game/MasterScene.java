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
  GameScene game = new GameScene();
  JPanel cardpanel;
  CardLayout layout;

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
    setSize(720,480);
		//JFrameをフルスクリーンに
		//gd.setFullScreenWindow(this);


    // CardLayout用パネル
    cardpanel = new JPanel();
    layout = new CardLayout();
    cardpanel.setLayout(layout);

    StartScene start = new StartScene();
    GameScene game = new GameScene();

    cardpanel.add(start, "StartScene");
    cardpanel.add(game.get_pane(), "GameScene");
    
    contentPane.add(cardpanel);

  }

  public void ChangePanel(String s){
    layout.show(cardpanel, s);
    System.out.println(s + "Yes!");
  }

  public Container get_contentpane(){
    return contentPane;
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
