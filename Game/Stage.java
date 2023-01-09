import java.nio.file.Files;
import java.nio.file.Path;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

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
  ObstacleList obstacle_list = new ObstacleList();

  // 1パネルのサイズ
  final int PANEL_SIZE = 32;
  // 最大の横のブロック数
  final int ROW = 100;
  // 縦のブロック数
  final int COL = 15;

  // 横のサイズ
  final int WIDTH = PANEL_SIZE * ROW;
  // 縦のサイズ
  final int HEIGHT = PANEL_SIZE * COL;

  public int lim = 0;
  
  final int MAX_STAGE_NUMBER = 2;
  // ステージ格納用配列
  public String[] STAGE = new String[MAX_STAGE_NUMBER];
  // 読み込むべきステージの番号
  public int stage_number = 0;

  String[][] stage_data = new String[COL][ROW];
  JLabel[][] stage_matrix = new JLabel[COL][ROW];

  /**
   * コンストラクタ
   * csvファイルを読み込み、配列化する。
   * 配列の中身が"1"のときにブロックを描画するようにした。
   * 配列の中身が"0"のときは何もないとして、描画しないことにする。
   * 
   * @author 綾部
   */
  public Stage(){
    STAGE[0] = "./stage/Stage1.csv"; 
    STAGE[1] = "./stage/Stage2.csv"; 
  }

  /**
   * ステージ生成用。csvファイルを読み込む。
   */
  public void production_stage(){
    // TODO  ここでobstacle_listを初期化する関数を呼び出す。
    try {
      stage_data = Files.readAllLines(Path.of(STAGE[stage_number])).stream()
      .map(line -> line.split(","))
      .toArray(String[][]::new);
      obstacle_list.load_from_str_arr(stage_data, COL, ROW);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  public ObstacleList get_obstacle_list() {
    return this.obstacle_list;
  }

  public int length(){
    return lim;
  }

  /**
   * ステージが何番目かを設定。基本的にはプレイ開始時に0に設定。
   * @param stage_num
   */
  public void set_stage_num(int stage_num){
    stage_number = stage_num;
  }

  /** 
   * 何番目のステージかをインクリメントする。ステージクリア時に実行。
   */
  public void stage_num_plus(){
    stage_number++;
  }

  /**
   * 
   * @return 最後のステージかを判別。そうであればtrue, 最後でなければfalseを返す。
   */
  public boolean last_stage(){
    if(stage_number+1 == MAX_STAGE_NUMBER){
      return true;
    }else{
      return false;
    }
  }

  /**
   * ステージ描画用
   * @param g
   * @param offset
   */
  public void draw(Graphics g, int offset) {
    for (Obstacle obstacle : obstacle_list.get_list()) {
      g.drawImage(obstacle.get_image(), obstacle.x - offset, obstacle.y, this);
    }
  }
}
