public class Main {
  /**
   * まずはゲームウインドウを表示させる。
   * 後々マルチスレッドを実装する気がするから、Mainの中は最低限に。
   * 
   * @author 綾部
   */
  public static void main(String[] args) {
    GameScene gw = new GameScene("テストウィンドウ");
    gw.setVisible(true);
  }
}
