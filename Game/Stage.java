import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage extends JPanel{
  // インスタンス生成
  ImageIcon block = new ImageIcon("./img/object/Block(仮).png");
  ImageIcon sky = new ImageIcon("./img/object/sky(仮).png");
  JLabel block_lbl = new JLabel(block);
  JLabel sky_lbl = new JLabel(sky);
  // ファイル読み込み
  BufferedReader br = null;
  String object_file = "Stage.csv";

  // ステージのサイズ。仮にウインドウのサイズ分にした。
  final int MAX_DATA_NUM_WIDTH = 20;
  final int MAX_DATA_NUM_HEIGHT = 10;
  String[][] stage_data = new String[MAX_DATA_NUM_HEIGHT][MAX_DATA_NUM_WIDTH];
  JLabel[][] stage_matrix = new JLabel[MAX_DATA_NUM_HEIGHT][MAX_DATA_NUM_WIDTH];

  public JLabel[][] stage_object(){
    try{
      File stage = new File(object_file);
      br = new BufferedReader(new FileReader(stage));
      String line;
      int index = 0;
      while((line = br.readLine()) != null){
        stage_data[index] = line.split(",");
        index++;
      }
    }catch(Exception e){
      System.out.println(e.getMessage());
    }


    for(int i = 0; i< MAX_DATA_NUM_HEIGHT; i++){
      //if(stage_data[i] == null) break;
      for(int j = 0; j<MAX_DATA_NUM_WIDTH; j++){

        if(stage_data[i][j].equals("1")){
          stage_matrix[i][j] = new JLabel(block);

          stage_matrix[i][j].setBounds(j*50, (i-1)*50, 50, 50);
        }else if(stage_data[i][j].equals("0")){
          stage_matrix[i][j] = new JLabel();

          // 以下のコメントアウトをとると、キャラクターと背景が重なり、背景が描画されてしまう。
          //stage_matrix[i][j] = new JLabel(sky);
          //stage_matrix[i][j].setBounds(j*50,(i-1)*50,50,50);
        }
        //[i][j]に正しく入っているか確認用
        System.out.print(stage_data[i][j]+" "); 
      }
      
      System.out.println();
    }

    return stage_matrix;
  }
}
