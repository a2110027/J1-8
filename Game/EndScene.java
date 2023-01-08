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

      player.reset();
      player.timer.stop(); 
      ms.ChangePanel("GameScene");
      this.setVisible(false);
    }

    ms = MasterScene.get_instance();

    ms.ChangePanel(cmd);
  }
}
