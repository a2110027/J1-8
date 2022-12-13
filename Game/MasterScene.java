import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 * 画面遷移を司る
 * Start画面→Play画面→GameOver or GameClear
 * 
 * @author 綾部
 */
public class MasterScene extends JFrame implements ActionListener {

  // インスタンス生成
  JLayeredPane p = new JLayeredPane();
  JPanel cardPanel;
  CardLayout layout;
  JPanel btnPanel = new JPanel();

  JPanel startpanel = new JPanel();
  GameScene game = new GameScene();

  JButton startButton = new JButton("Game Start");

  KeyController kc = new KeyController();

  /**
   * コンストラクタ
   * 
   * @param title ウインドウのタイトル
   * @author 綾部
   */
  public MasterScene(String title) {
    super(title);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(1000, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    p.setLayout(null);
    addKeyListener(kc);
    startpanel.setBackground(Color.DARK_GRAY);
    startpanel.setLayout(null);
    // CardLayout用パネル
    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);

    cardPanel.add(startpanel, "Start");
    cardPanel.add(game.set(), "Game Scene");

    startButton.addActionListener(this);
    startButton.setActionCommand("Game Scene");

    btnPanel.add(startButton);

    // cardPanelとカード移動用ボタンをJFrameに配置
    Container contentPane = getContentPane();
    contentPane.add(cardPanel, BorderLayout.CENTER);
    contentPane.add(btnPanel, BorderLayout.PAGE_END);

  }

  /**
   * 画面を遷移させる
   */
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    layout.show(cardPanel, cmd);
    btnPanel.setVisible(false);
  }
}
