import javax.swing.*;
import java.awt.*;

// ゲーム画面表示
// プレイヤー表示
public class GameScene extends JFrame{

  // インスタンス生成
  PlayerVisible pl = new PlayerVisible(50,300);
  Stage st = new Stage();
  JPanel p = new JPanel();

  public GameScene(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		setLocationRelativeTo(null);
		setResizable(false);
    p.setLayout(null);



    // ステージ描画
    JLabel[][] ar = st.stage_object();
    for(int i = 0; i<10; i++){
      for(int j = 0; j < 20; j++){
        p.add(ar[i][j]);
      }
    }

    // Player描画
    p.add(pl.get());
    // JFrame のコンポーネントを取得し、JPanelを追加する。
    Container contentPane = getContentPane();
    contentPane.add(p);
	}
}
