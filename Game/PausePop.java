import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

class ExitPop extends JPanel implements ActionListener{
    private JButton yb,nb;

    int initx = 1014*30/100, inity = 537*30/100, initw = 1014*40/100, inith = 537*40/100; //全体sizeの割合にするよう後で改良

    public ExitPop(){
        setLayout(null);
        
        setBorder(new TitledBorder("注意"));
        setBounds(initx,inity,initw,inith);
        
        JLabel lb = new JLabel("<html><body>ゲームを終了しますか？<br />(タイトル画面に戻ります)</body></html>");
        

        yb = new JButton("YES");
        nb = new JButton("NO");

        lb.setBounds(initx*40/100, inity*40/100, initw*45/100, inith*25/100);
        yb.setBounds(initx*5/100, inity*75/100, initw*45/100, inith*25/100);
        nb.setBounds(initx*65/100, inity*75/100, initw*45/100, inith*25/100);

        yb.setForeground(Color.blue);
        nb.setForeground(Color.red);
        
        yb.addActionListener(this);
        nb.addActionListener(this);

        yb.setActionCommand("exit");
        nb.setActionCommand("continue");

        add(lb);
        add(yb); add(nb); 

    }

    public void actionPerformed(ActionEvent e){
       String b = e.getActionCommand();

       if(b == "exit"){ //一時保存?とりあえず今は初期化で実装
        this.setVisible(false);
        MasterScene ms = MasterScene.master;
        Player player = Player.player;
        GameScene gs = GameScene.gs;

        player.set(50, 350); //player位置初期化
        player.speed.set_a(0, 0); //速度初期化(他の関数?)
        player.speed.set_v(0, 0);
        player.timer.stop(); 
        gs.score = 0; gs.time = 0; //初期化
        ms.ChangePanel("StartScene");
       }else if(b == "continue"){
        this.setVisible(false);
        PausePop pp = new PausePop();
        GameScene gs = GameScene.gs;
        gs.add(pp);
        repaint();
        pp.setVisible(true);
        pp.setFocusable(true);
       }else if(b == "setting"){ //音量など新たにpopupmenuを出すとか。

       }

    }
}

public class PausePop extends JPanel implements ActionListener{
    private JButton exitb, contib, setb;

    int initx = 1014*30/100, inity = 537*30/100, initw = 1014*40/100, inith = 537*40/100; //全体sizeの割合にするよう後で改良

    public PausePop(){
        setLayout(null);
        
        setBorder(new TitledBorder("Pause"));
        setBounds(initx,inity,initw,inith);
        

        exitb = new JButton("ゲーム終了");
        contib = new JButton("続ける");
        setb = new JButton("設定");

        exitb.setBounds(initx*5/100, inity*90/100, initw*45/100, inith*25/100);
        contib.setBounds(initx*65/100, inity*90/100, initw*45/100, inith*25/100);
        setb.setBounds(initx*35/100, inity*58/100, initw*45/100, inith*25/100);

        exitb.addActionListener(this);
        contib.addActionListener(this);
        setb.addActionListener(this);

        exitb.setActionCommand("exit");
        contib.setActionCommand("continue");
        setb.setActionCommand("setting");


        add(exitb); add(contib); add(setb);

    }

    public void actionPerformed(ActionEvent e){
       String b = e.getActionCommand();

       if(b == "exit"){ //一時保存?とりあえず今は初期化で実装
        this.setVisible(false);
        /*
        MasterScene ms = MasterScene.master;
        Player player = Player.player;
        GameScene gs = GameScene.gs;

        player.set(50, 350); //player位置初期化
        player.speed.set_a(0, 0); //速度初期化(他の関数?)
        player.speed.set_v(0, 0);
        player.timer.stop(); 
        gs.score = 0; gs.time = 0; //初期化
    
        ms.ChangePanel("StartScene");
        */
        
        ExitPop ep = new ExitPop();
        GameScene gs = GameScene.gs;
        gs.add(ep);
        repaint();
        ep.setVisible(true);
        ep.setFocusable(true);
       }else if(b == "continue"){
        this.setVisible(false);
        GameScene gs = GameScene.gs;
        gs.gamestart();
       }else if(b == "setting"){ //音量など新たにpopupmenuを出すとか。

       }

    }
}
