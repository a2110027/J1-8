import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class PausePop extends JPanel implements ActionListener{
    private JButton exitb, contib, setb;

    public PausePop(){
        setLayout(null);

        setBounds(500,300,300,180);
        setBorder(new TitledBorder("Pause"));

        exitb = new JButton("ゲーム終了");
        contib = new JButton("続ける");
        setb = new JButton("設定");

        exitb.setBounds(20,120,100,50);
        contib.setBounds(170,120,100,50);
        setb.setBounds(75,60,150,50);

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
        MasterScene ms = MasterScene.master;
        Player player = Player.player;

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
       }else if(b == "setting"){ //音量など新たにpopupmenuを出すとか。

       }

    }
}