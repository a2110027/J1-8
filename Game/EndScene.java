import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EndScene extends JPanel implements ActionListener {
  // インスタンス生成
  JButton restart_btn, end_btn;
  MasterScene ms;
  Player player = Player.get_instance();
  GameScene gm = GameScene.get_instance();

  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;

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



  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if (cmd == "Exit") {

      System.exit(0);
    }else if(cmd == "GameScene"){
      MasterScene ms = MasterScene.master;
      Player player = Player.player;
      gm.reset_score();
      player.set(50, 384); //player位置初期化
      player.speed.set_a(0, 0); //速度初期化(他の関数?)
      player.speed.set_v(0, 0);
      player.timer.stop(); 
      player.set_death_flag(false);
      ms.ChangePanel("GameScene");
      this.setVisible(false);
    }
  }
  
  /** 
   * 文字列を表示する。
   */
  public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font1 = new Font("ＭＳＰゴシック",Font.PLAIN,50);
		g.setFont(font1);
    if(player.get_death()){
      g.drawString("You Died!",375, 150);
    }else{
      g.drawString("Game Clear!!",375, 150);
    }
    g.drawString("Your Score is "+ gm.get_score(), 300, 400);
	}
}
