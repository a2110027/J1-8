import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class PausePop extends JPanel implements ActionListener{
    private JButton exitb, contib, setb;
    Score sc = Score.get_instance();
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
        MasterScene ms = MasterScene.master;
        Player player = Player.player;
        sc.stop();

        player.set(50, 350); //player位置初期化
        player.speed.set_a(0, 0); //速度初期化(他の関数?)
        player.speed.set_v(0, 0);
        player.timer.stop(); 

        ms.ChangePanel("StartScene");
        this.setVisible(false);
        }else if(b == "continue"){
        this.setVisible(false);
        GameScene gs = GameScene.gs;
        gs.gamestart();
        sc.restart();
       }else if(b == "setting"){ //音量など新たにpopupmenuを出すとか。

        }

    }
}
