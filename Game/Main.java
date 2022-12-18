public class Main {
  /**
   * まずはゲームウインドウを表示させる。
   * 後々マルチスレッドを実装する気がするから、Mainの中は最低限に。
   * 
   * @author 綾部
   */

  public static void main(String[] args) {
    MasterScene gw = new MasterScene();
    gw.setVisible(true);

    KeyController kc = new KeyController();
    gw.addKeyListener(kc);

  }
}
