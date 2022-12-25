import java.nio.file.Files;
import java.nio.file.Path;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Stage描画用クラス
 * ブロックのみとりあえず実装
 *  "1"通常のブロック
 *  "2" すり抜け可能ブロック
 *  "3" 上向き針ブロック(仮)
 * 
 * @author 綾部
 */
public class Stage extends  JPanel{
  // インスタンス生成
  Image block = Toolkit.getDefaultToolkit().getImage("./img/object/GroundBlock.png");
  Image board = Toolkit.getDefaultToolkit().getImage("./img/object/Board.png");
  Image needle = Toolkit.getDefaultToolkit().getImage("./img/object/Needle.png");
  StageObjectsList stage_object_list = new StageObjectsList();
  BufferedReader br = null;

  // 1パネルのサイズ
  final int PANEL_SIZE = 32;
  // 最大の横のブロック数
  final int ROW= 100;
  // 縦のブロック数
  final int COL = 15;

  // stage ファイル
  String stage_name = "./stage/Stage.csv";

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
/*     try {
      stage_data = Files.readAllLines(Path.of("./stage/Stage.csv")).stream()
      .map(line -> line.split(","))
      .toArray(String[][]::new);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
 */
  try {
    File file = new File(stage_name);
    br = new BufferedReader(new FileReader(file));
    // readLineで一行ずつ読み込む;
    int index = 0;
    String line;
    while ((line = br.readLine()) != null) {
      // lineをカンマで分割し、配列dataに保持
      stage_data[index] = line.split(",");
      index++;
      }
      // catch-finally部分は同様なので省略
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
      // 配列に格納したデータを表示（データ間にスペース）
      for (int i = 0; i < COL; i++) {
      // データがなくなったら終了
      if (stage_data[i] == null) break;
      for (int j = 0; j < stage_data[i].length; j++) {
        System.out.print(stage_data[i][j] + " ");
      }
      System.out.println();
    }
  }

  
  public StageObjectsList get_stage_object_list() {
    return this.stage_object_list;
  }

  public int length(){
    return lim;
  }

  public void draw(Graphics g, int offset) {
    int firstTileX = (int)Math.floor(offset / 32);
    int lastTileX = firstTileX + (int)Math.floor(960 / 32)+1;
    lastTileX = Math.min(lastTileX, 100);
    int firstTileY = 0;
    int lastTileY =  15;
    for (int i = firstTileY; i < lastTileY; i++) {
      for (int j = firstTileX; j < lastTileX; j++) {
        // この下のstage_data[i][j]をstage_data_[i][j]に変えると、csvファイルを読まなくなる。
        if (stage_data[i][j].equals("1")) {
            g.drawImage(block, j*32 - offset, i*32, this);        
        } else if(stage_data[i][j].equals("2")) {
            g.drawImage(board, j*32 - offset, i*32, this);
        } else if(stage_data[i][j].equals("3")) {
          g.drawImage(needle, j*32 - offset, i*32, this);
        } 
      }
    }
  }
}
