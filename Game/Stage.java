import java.nio.file.Files;
import java.nio.file.Path;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Stage描画用クラス
 * ブロックのみとりあえず実装
 * 
 * @author 綾部
 */
public class Stage extends  JPanel{
  // インスタンス生成
  Image img = Toolkit.getDefaultToolkit().getImage("./img/object/Block(仮).png");
  ImageIcon block = new ImageIcon("./img/object/Block(仮).png");
  JLabel block_lbl = new JLabel(block);
  StageObjectsList stage_object_list = new StageObjectsList();

  // 1パネルのサイズ
  final int PANEL_SIZE = 50;
  // 最大の横のブロック数
  final int ROW= 50;
  // 縦のブロック数
  final int COL = 10;


  // 横のサイズ
  final int WIDTH = PANEL_SIZE * ROW;
  // 縦のサイズ
  final int HEIGHT = PANEL_SIZE * COL;

  public int lim = 0;
  String[][] stage_data = new String[COL][ROW];
  JLabel[][] stage_matrix = new JLabel[COL][ROW];
  String stage_data_[][] = {
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","1","1","1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","1","1","1","1","1","0","0","0","1","1","1","0","0","1","1","1","0","0","0","1","1","1","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
  };

  /**
   * コンストラクタ
   * csvファイルを読み込み、配列化する。
   * 配列の中身が"1"のときにブロックを描画するようにした。
   * 配列の中身が"0"のときは何もないとして、描画しないことにする。
   * 
   * @author 綾部
   */
  public Stage(){
    try {
      stage_data = Files.readAllLines(Path.of("./stage/Stage.csv")).stream()
      .map(line -> line.split(","))
      .toArray(String[][]::new);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }



  public StageObjectsList get_stage_object_list() {
    return this.stage_object_list;
  }

  public int length(){
    return lim;
  }

  public void draw(Graphics g, int offsetX) {
    int firstTileX = (int)Math.floor(offsetX / 50);
    int lastTileX = firstTileX + (int)Math.floor(1000 / 50);
    lastTileX = Math.min(lastTileX, 50);
    int firstTileY = 0;
    int lastTileY =  10;
    for (int i = firstTileY; i < lastTileY; i++) {
      for (int j = firstTileX; j < lastTileX; j++) {
        // この下のstage_data[i][j]をstage_data_[i][j]に変えると、csvファイルを読まなくなる。
        if (stage_data[i][j].equals("1")) {
            g.drawImage(img, j*50 - offsetX, i*50, this);
        } 
      } 
    } 
  }
}
