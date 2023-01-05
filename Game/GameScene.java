import javax.swing.*;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.awt.*;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画を行う
 * 
 * @author 綾部
 */
public class GameScene extends JPanel implements ActionListener{

  // インスタンス生成
  static GameScene gs = new GameScene(); //MaterSceneコンストラクタでのnew以降ならいつでもアクセス可能です
  Player player = Player.player;
  Stage st = new Stage();
  BackGround bg = new BackGround();
  Timer tm;
  
  JButton backss, pauseb;

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {
    tm = new Timer();
    setLayout(null);

    backss = new JButton("Home");
    backss.setBounds(0,0,100,50);
    add(backss);
    backss.addActionListener(this);
    backss.setActionCommand("StartScene"); //転移先のpanel名を指定する。

    pauseb = new JButton("Pause");
    pauseb.setBounds(120,0,100,50);
    add(pauseb);
    pauseb.addActionListener(this);
    pauseb.setActionCommand("Pause");

    // 各HumanにstageObjectListを渡す
    player.set_obstacle_list(st.get_obstacle_list());

  }

  public void gamestart(){
    for(Component c : gs.getComponents()){ //gs上のすべてのComponentを有効化(無効化することがあるので)
      c.setEnabled(true);
    }
    
    tm = new Timer();

    player.timer.start();
    //タイマー開始。再描画を行う。
    tm.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
        repaint();
        // プレイヤーのx座標が最後のパネルに行った時にエンドシーンに切り替わる。
        if(player.get_x() >= 3136){
          end();
        }
			}
		},  0, 100);

    System.out.println("gamestart!\n"); //実行確認用
  }

  
  /**
   * 死亡またはクリアによるゲーム終了画面遷移用
   * @author 綾部
   */
  public void end(){
    MasterScene ms = MasterScene.master;
    ms.ChangePanel("EndScene");
    tm.cancel(); //描写停止
    player.timer.stop(); //位置更新停止

  }



  /**
   * インスタンスを返す
   * 
   * @return インスタンス
   */
  static GameScene get_instance() {
    return gs;
  }


  /* ***************************************** */
  /**
   * 描画部分
   * @param offset ステージや背景(未実装)のスクロールをするための値
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int offset = this.player.get_x();
    offset = Math.min(offset, 2560);
    offset = Math.max(offset-320, 0);
    bg.draw(g, offset);
    st.draw(g, offset);
    player.draw(g, offset);
  }

  public void actionPerformed(ActionEvent e) { 
    String cmd = e.getActionCommand();
    
    MasterScene ms = MasterScene.master;
    ms.ChangePanel(cmd);

    if(cmd == "StartScene"){
      player.set(32, 384); //player位置初期化

      player.speed.set_a(0, 0); //速度初期化(他の関数?)
      player.speed.set_v(0, 0);
      
      player.timer.stop(); 
    }else if(cmd == "Pause"){
      tm.cancel(); //描写停止
      player.timer.stop(); //位置更新停止

      PausePop pp = new PausePop();
      gs.add(pp);
      for(Component c : gs.getComponents()){ //gs上のすべてのComponentを無効化
        c.setEnabled(false);
      }
      
      repaint(); //再描画しないとppが覆い隠される
      pp.setVisible(true);
      pp.setFocusable(true);
    }
    
  }

}
