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
public class StartScene extends JPanel implements ActionListener{
  // インスタンス生成
  JButton startb,endb;
  MasterScene ms;
  ImageIcon startsky = new ImageIcon("./img/background/サイバー.png");
  JLabel sky_lbl = new JLabel(startsky);
  // 画面サイズ
  final static int WIDTH = 960;
  final static int HEIGHT = 480;
  public StartScene(){
    setSize(WIDTH, HEIGHT);
    setLayout(null);
    
    startb = new JButton("Start");
    startb.setActionCommand("GameScene");
    startb.addActionListener(this);
    startb.setBounds(240,206,240,64);
    add(startb);

    endb = new JButton("Exit");
    endb.setActionCommand("Exit");
    endb.addActionListener(this);
    endb.setBounds(0,0,200,50);
    add(endb);
    sky_lbl.setBounds(0,0,WIDTH,HEIGHT);
    add(sky_lbl);
}

public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if(cmd == "Exit"){
      
      System.exit(0);
    }

    ms = MasterScene.get_instance();

    ms.ChangePanel(cmd);
}

  /**
   * 背景画像を出力
   * 
   * @return startsky_lbl 空(仮)の画像
   * @author 藤
   */
  public JLabel get_background() {
    sky_lbl.setBounds(0, 0, WIDTH,HEIGHT);
    return sky_lbl;
  }

}
