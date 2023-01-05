import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EndScene extends JPanel implements ActionListener {
  // インスタンス生成
  JButton restart_btn, end_btn;
  MasterScene ms;
  Player player = Player.get_instance();
  GameScene gm = GameScene.get_instance();
  Score sc = Score.get_instance();

  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;

  int count = 0;
  int score = count *1000;

  public EndScene() {
    setSize(WIDTH, HEIGHT);
    setLayout(null);

    restart_btn = new JButton("<html><font size = 8 color = red>Restart</font></html>");
    restart_btn.setActionCommand("GameScene");
    restart_btn.addActionListener(this);
    restart_btn.setBounds(400, 206, 160, 64);
    add(restart_btn);

    end_btn = new JButton("<html><font size = 10 color = green>EXIT</font></html>");
    end_btn.setActionCommand("Exit");
    end_btn.addActionListener(this);
    end_btn.setBounds(416, 280, 128, 64);
    add(end_btn);
  }

  /**
   * カウントダウンタイマーの値を取得し、スコアの計算をする。
   */
  public void set_end_scene(){
    count = sc.get_count();
    score = count * 1000;
  }


  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if (cmd == "Exit") {

      System.exit(0);
    }else if(cmd == "GameScene"){
      MasterScene ms = MasterScene.master;
      Player player = Player.player;

      player.set(50, 384); //player位置初期化
      player.speed.set_a(0, 0); //速度初期化(他の関数?)
      player.speed.set_v(0, 0);
      player.timer.stop(); 
      ms.ChangePanel("GameScene");
      this.setVisible(false);
    }
  }
  /** 
   * スコアを表示する。
   */
  public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font1 = new Font("ＭＳ Ｐゴシック",Font.PLAIN,30);
		g.setFont(font1);
    set_end_scene();
		g.drawString("Your Score Is "+score, 350, 150);
	}
}
