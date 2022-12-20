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


  /**
   * csvファイルを読み込み、配列化する。
   * 配列の中身が"1"のときにブロックを描画するようにした。
   * 配列の中身が"0"のときは何もないとして、描画しないことにする。
   * 
   * @return stage_matrix csvファイルを読み込んで出来たステージラベルの配列
   * @author 綾部
   */
  public JLabel[][] stage_object() {

    // ここから
    try {
      stage_data = Files.readAllLines(Path.of("./stage/Stage.csv")).stream()
      .map(line -> line.split(","))
      .toArray(String[][]::new);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    // ここまで


    // Stage.csvが無いと表示される人は、この下のコードのコメントアウトを外して、上のコードををコメントアウトしてください。

    // String stage_data[][] = {
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    //   {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
    //   {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
    // };
    // ここまで

    // for (int i = 0; i < COL; i++) {
    //   for (int j = 0; j < ROW; j++) {
    //     if (j == stage_data[i].length){
    //       lim = j;
    //       break;
    //     }
    //     if (stage_data[i][j].equals("1")) {
    //       stage_matrix[i][j] = new JLabel(block);
    //       stage_object_list.add_stage_object(new StageObject(j*50, i*50-25, 50, 50));
    //     } else if (stage_data[i][j].equals("0")) {
    //       stage_matrix[i][j] = new JLabel();
    //     }
    //   }
    // }

    return stage_matrix;
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
        if (stage_data[i][j].equals("1")) {
            g.drawImage(img, j*50 - offsetX, i*50, this);
        } 
      } 
    } 

    System.out.println(firstTileX + ", "+ lastTileX);
    System.out.println(firstTileY + ", "+ lastTileY);
  }
}
