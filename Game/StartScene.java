import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * BackGround描画用クラス + Start画面
 * 
 * 
 * @author 藤
 */
<<<<<<< HEAD
public class StartScene extends JPanel implements ActionListener{
  // インスタンス生成
  JButton startb,endb;
  MasterScene ms;
  ImageIcon startsky = new ImageIcon("./img/background/sky.png");
  JLabel sky_lbl = new JLabel(startsky);

  public StartScene(){
    setSize(1920, 1080);
    setLayout(null);
    
    startb = new JButton("Start");
    startb.setActionCommand("GameScene");
    startb.addActionListener(this);
    startb.setBounds(300,300,200,100);
    add(startb);

    endb = new JButton("Exit");
    endb.setActionCommand("Exit");
    endb.addActionListener(this);
    endb.setBounds(0,0,200,50);
    add(endb);
    sky_lbl.setBounds(0,0,1920,1080);
    add(sky_lbl);
}

public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if(cmd == "Exit"){
      
=======
public class StartScene extends JPanel implements ActionListener {
  // インスタンス生成
  JButton startb, endb;
  MasterScene ms;
  ImageIcon start = new ImageIcon("./img/background/サイバー.png");
  JLabel start_lbl = new JLabel(start);
  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;

  public StartScene() {
    setSize(WIDTH, HEIGHT);
    setLayout(null);

    startb = new JButton("<html><font size = 10 color = red>Start</font></html>");
    startb.setActionCommand("GameScene");
    startb.addActionListener(this);
    startb.setBounds(416, 206, 128, 64);
    add(startb);

    endb = new JButton("<html><font size = 10 color = green>EXIT</font></html>");
    endb.setActionCommand("Exit");
    endb.addActionListener(this);
    endb.setBounds(416, 280, 128, 64);
    add(endb);
    start_lbl.setBounds(0, 0, WIDTH, HEIGHT);
    add(start_lbl);
  }

  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if (cmd == "Exit") {

>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859
      System.exit(0);
    }

    ms = MasterScene.get_instance();

    ms.ChangePanel(cmd);
<<<<<<< HEAD
}
=======
  }
>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859

  /**
   * 背景画像を出力
   * 
   * @return startsky_lbl 空(仮)の画像
   * @author 藤
   */
  public JLabel get_background() {
<<<<<<< HEAD
    sky_lbl.setBounds(0, 0, 1000, 500);
    return sky_lbl;
=======
    start_lbl.setBounds(0, 0, WIDTH, HEIGHT);
    return start_lbl;
>>>>>>> 39766ca4b20a5356552c022aa85f9284c54e1859
  }

}
