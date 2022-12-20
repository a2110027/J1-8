import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * プレイヤー描画
 * ステージ描画
 * 背景描画を行う
 * 
 * @author 綾部
 */
public class GameScene extends JPanel implements KeyListener {

  // インスタンス生成
  static GameScene gs = new GameScene();
  Player player = Player.get_instance();
  Stage st = new Stage();
  BackGround bg = new BackGround();
  JLayeredPane p = new JLayeredPane();
  JLabel[][] ar = st.stage_object();

  MasterScene ms = MasterScene.get_instance();

  /**
   * コンストラクタ
   * 
   * @author 綾部
   */
  public GameScene() {

    // 各stageObjectクラスにstageObjectListを付与
    player.set_stage_object_list(st.get_stage_object_list());

    // // 背景描画 layerは1
    // p.add(bg.get_background());
    // p.setLayer(bg.get_background(), 1);

    // // // ステージ描画 Layerは2
    // p.add(this);
    // p.setLayer(this,2);

    // // Player描画 Layerは3
    // p.add(player.get());
    // p.setLayer(player.get(),3);

  }

  /**
   * プレイヤーのみ再描画
   * 
   */
  public void reload() {
    player.set(player.get_x(), player.get_y());
    p.add(player.get());
    p.setLayer(player.get(), 3);
  }

  /**
   * インスタンスを返す
   * 
   * @return インスタンス
   */
  static GameScene get_instance() {
    return gs;
  }

  public JLayeredPane get() {
    return p;
  }

  /* ***************************************** */

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // int offset = 0;
    int offset = (int) this.player.get_x() - 50;
    offset = Math.min(offset, 500);
    offset = Math.max(offset, 0);
    bg.draw(g, 0);
    st.draw(g, offset);
    player.draw(g);
    System.out.println(offset);
  }

  /* ***************************************: */

  // esc,上下左右,vキー(書き換えメニュー)の入力受付
  @Override
  public void keyPressed(KeyEvent e) {
    // 関数名は適当
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        player.jump();
        System.out.println("jump");
        break;
      case KeyEvent.VK_DOWN:
        player.move_bottom();
        System.out.println("down");
        break;
      case KeyEvent.VK_LEFT:
        player.move_left();
        System.out.println("left");
        break;
      case KeyEvent.VK_RIGHT:
        player.move_right();
        System.out.println("right");
        break;
      case KeyEvent.VK_V:
        System.out.println("V");
        break;
      case KeyEvent.VK_ESCAPE:
        System.out.println("ESC");

        System.exit(0);

        break;
    }
  }

  // 下２つは使わない
  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        player.set_non_move_flag(true);
        break;
      case KeyEvent.VK_DOWN:
        player.set_non_move_flag(true);
        break;
      case KeyEvent.VK_LEFT:
        player.set_non_move_flag(true);
        break;
      case KeyEvent.VK_RIGHT:
        player.set_non_move_flag(true);
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
