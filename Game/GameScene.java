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
  BackGround bg = new BackGround();
  BetweenScene bet = BetweenScene.get_instance();
  Timer tm;
  Stage st = new Stage();
  int time, score;
  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;

  public int stage_number = 0;
  
  JButton backss, pauseb;
  JLabel scorep,timep, speedp;

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {
    
    // 各HumanにstageObjectListを渡す

    tm = new Timer();
    time = 300;
    score = 0;
    setLayout(null);

    //ボタン設定
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

    //パネル設定
    scorep = new JLabel();
    scorep.setForeground(Color.white);
    scorep.setFont(new Font(Font.SANS_SERIF,Font.BOLD | Font.ITALIC,15));
    scorep.setBounds(WIDTH - 120,0,120,20);

    timep = new JLabel();
    timep.setForeground(Color.white);
    timep.setFont(new Font(Font.SANS_SERIF,Font.BOLD | Font.ITALIC,15));
    timep.setBounds(WIDTH - 250,0,100,20);

    speedp = new JLabel();
    speedp.setForeground(Color.white); //speedによって色を変化させるとかどうでしょう
    speedp.setFont(new Font(Font.SANS_SERIF,Font.BOLD | Font.ITALIC,15));
    speedp.setBounds(WIDTH - 550,20,550,20);

    add(scorep); 
    add(timep);
    add(speedp);



  }

  /*********************************************************************** */
  /**
   * 死亡またはクリアによるゲーム終了画面遷移用
   * @author 綾部
   */
  public void end(){
    MasterScene ms = MasterScene.master;
    ms.ChangePanel("EndScene");
    st.set_stage_num(0);
    tm.cancel(); //描写停止
    player.timer.stop(); //位置更新停止
  }

  /**
   * ステージクリア後、次のステージへ
   */
  public void next(){
    MasterScene ms = MasterScene.master;
    tm.cancel(); //描写停止
    player.timer.stop(); //位置更新停止
    if(st.last_stage()){
      end();
    }else{
      bet.next_stage();
      st.stage_num_plus();
      st.production_stage();
      player.set_obstacle_list(st.get_obstacle_list());
      player.set(32, 384); //player位置初期化
      player.speed.set_a(0, 0); //速度初期化(他の関数?)
      player.speed.set_v(0, 0);
      time = 300;
      ms.ChangePanel("BetweenScene");
    }
  }

  /**
   * スコア初期化。タイマー初期化。ステージナンバー初期化。
   */
  public void reset_score(){
    time = 300;
    score = 0;
    st.set_stage_num(0);
  }

  /**
   * スコアを取得
   * @return score
   */
  public int get_score(){
    return score;
  }
  /**
   * クリア時等にスコアを加える用
   * @param point 加えたいポイント
   */
  public void score_plus(int point){
    score = score + point;
  }

  /**
   * インスタンスを返す
   * 
   * @return インスタンス
   */
  static GameScene get_instance() {
    return gs;
  }


  /****************************************************************************** */
  public void gamestart(){
    st.production_stage();
    player.set_obstacle_list(st.get_obstacle_list());
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
        // ゲームクリア
        if(player.get_x() >= 3104){
          score_plus(30000);
          next();
        }
        // ゲームオーバー
        if(player.get_death() || player.get_y()> 480){
          player.set_death_flag(true);
          end();
        }
        //下なくてもいい
        speedp.setText("Speed: " + String.format("(x, y) = (%d, %d), v = (%.1f, %.1f), a = (%.1f, %.1f)", player.x, player.y, player.speed.get_vx(), player.speed.get_vy(), player.speed.get_ax(), player.speed.get_ay()));
			}
		},  0, 100);

    tm.scheduleAtFixedRate(new TimerTask() { //time関係のタスク、1秒ごとに実行
			@Override
			public void run() {
        if(time == 0){
          player.set_death_flag(true);
        }
        time--;
        score = score + 100;
        scorep.setText("Score: " + String.format("%06d", score)); //score計算式は後で変更の必要あり
        timep.setText("Time: " + String.format("%05d", time));
        repaint();
      }
		},  0, 1000);

    System.out.println("gamestart!\n"); //実行確認用
  }
  /******************************************************************** */

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
      tm.cancel();
      player.timer.stop(); 
      st.set_stage_num(0);
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
