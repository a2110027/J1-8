import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Stage描画用クラス
 * ブロックのみとりあえず実装
 * 
 * @author 綾部
 */
public class Stage extends JPanel {
  // インスタンス生成
  ImageIcon block = new ImageIcon("./img/object/Block(仮).png");
  JLabel block_lbl = new JLabel(block);

  // ファイル読み込み
  BufferedReader br = null;
  String object_file = "./Stage/Stage.csv";

  // ステージのサイズ。仮にウインドウのサイズ分にした。
  final int MAX_DATA_NUM_WIDTH = 20;
  final int MAX_DATA_NUM_HEIGHT = 10;
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
      File stage = new File(object_file);
      br = new BufferedReader(new FileReader(stage));
      String line;
      int index = 0;
      while ((line = br.readLine()) != null) {
        stage_data[index] = line.split(",");
        index++;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    for (int i = 0; i < MAX_DATA_NUM_HEIGHT; i++) {
      if (stage_data[i] == null)
        break;
      for (int j = 0; j < MAX_DATA_NUM_WIDTH; j++) {

        if (stage_data[i][j].equals("1")) {
          stage_matrix[i][j] = new JLabel(block);

          stage_matrix[i][j].setBounds(j * 50, (i - 1) * 50, 50, 50);
        } else if (stage_data[i][j].equals("0")) {
          stage_matrix[i][j] = new JLabel();
        }
      }
    }

    return stage_matrix;
  }
}
