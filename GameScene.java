import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFrame;

import java.awt.*;


// ゲーム画面表示
// プレイヤー表示
public class GameScene extends JFrame{

  // インスタンス生成
  PlayerVisible pl = new PlayerVisible(50,300);
  Block bl = new Block(50,100);

  // ファイル読み込み
  BufferedReader br = null;
  String object_file = "Stage.csv";
  final int MAX_DATA_NUM = 200;

  String[][] stage_data = new String[MAX_DATA_NUM][];
  Block[][] block = new Block[MAX_DATA_NUM][MAX_DATA_NUM];


  public GameScene(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		setLocationRelativeTo(null);
		setResizable(false);

    // ステージを表示させる予定
    stage_object();
    // プレイヤーをフレームに追加
    getContentPane().add( pl );
	}


  // csvファイルを読み込み、stage_dataに格納
  // Blockの画像を表示させられていない。
  public void stage_object(){
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


    for(int i = 0; i< MAX_DATA_NUM; i++){
      if(stage_data[i] == null) break;
      for(int j = 0; j<stage_data[i].length; j++){
        if(stage_data[i][j] == null) break;

        if(stage_data[i][j].equals("1")){
          block[i][j] = new Block(j*50,i*50);
          add(block[i][j]);
        }
        // [i][j]に正しく入っているか確認用
        // System.out.print(stage_data[i][j]+" "); 
      }
      
      //System.out.println();
    }

  }


}
