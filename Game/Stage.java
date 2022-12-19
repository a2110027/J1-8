import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Stage描画用クラス
 * ブロックのみとりあえず実装
 * 
 * @author 綾部
 */
public class Stage{
  // インスタンス生成
  ImageIcon block = new ImageIcon("./img/object/Block(仮).png");
  JLabel block_lbl = new JLabel(block);
  StageObjectsList stage_object_list = new StageObjectsList();

  // ステージの 最大サイズ。
  final int MAX_DATA_NUM_WIDTH = 50;
  final int MAX_DATA_NUM_HEIGHT = 10;
  public int lim = 0;
  String[][] stage_data = new String[MAX_DATA_NUM_HEIGHT][MAX_DATA_NUM_WIDTH];
  JLabel[][] stage_matrix = new JLabel[MAX_DATA_NUM_HEIGHT][MAX_DATA_NUM_WIDTH];


  /**
   * csvファイルを読み込み、配列化する。
   * 配列の中身が"1"のときにブロックを描画するようにした。
   * 配列の中身が"0"のときは何もないとして、描画しないことにする。
   * 
   * @return stage_matrix csvファイルを読み込んで出来たステージラベルの配列
   * @author 綾部
   */
  public JLabel[][] stage_object() {
    try {
      stage_data = Files.readAllLines(Path.of("./stage/Stage.csv")).stream()
      .map(line -> line.split(","))
      .toArray(String[][]::new);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }


    for (int i = 0; i < MAX_DATA_NUM_HEIGHT; i++) {
      for (int j = 0; j < MAX_DATA_NUM_WIDTH; j++) {
        if (j == stage_data[i].length){
          lim = j;
          break;
        }
        if (stage_data[i][j].equals("1")) {
          stage_matrix[i][j] = new JLabel(block);
          stage_object_list.add_stage_object(new StageObject(j*50, i*50-25, 50, 50));
        } else if (stage_data[i][j].equals("0")) {
          stage_matrix[i][j] = new JLabel();
        }
      }
    }

    return stage_matrix;
  }

  public StageObjectsList get_stage_object_list() {
    return this.stage_object_list;
  }

  public int length(){
    return lim;
  }
}
