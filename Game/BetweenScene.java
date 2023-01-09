import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ステージクリアし、スコアを表示。3秒後次のステージへ移動する。
 */
public class BetweenScene extends JPanel{
  // インスタンス生成

  static BetweenScene bet = new BetweenScene();
  Player player = Player.get_instance();
  GameScene gm = GameScene.get_instance();

  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;

  /**
   * コンストラクタ
   */
  public BetweenScene() {
    setSize(WIDTH, HEIGHT);
    setLayout(null);
  }

  /**
   * 3秒後ゲームシーンへ遷移する。
   * @author 綾部
   */
  public void next_stage(){
    Timer timer_bet = new Timer(false);
		TimerTask task_bet = new TimerTask() {

			@Override
			public void run() {
        timer_bet.cancel();
        MasterScene ms = MasterScene.master;
        ms.ChangePanel("GameScene");
			}
		};
		timer_bet.schedule(task_bet, 3000);
	}
  
  /**
   * インスタンスを返す
   */
  static BetweenScene get_instance(){
    return bet;
  }

  /** 
   * 文字列を表示する。
   */
  public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font1 = new Font("ＭＳＰゴシック",Font.PLAIN,50);
		g.setFont(font1);
    g.drawString("Stage Clear!!",300, 200);    
    g.drawString("Your Score is "+ gm.get_score(), 300,300);
	}
}
