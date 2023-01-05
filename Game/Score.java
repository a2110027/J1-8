import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
public class Score extends JPanel{
  // 開始前カウント
	int prepare_count_down = 500;
  // 現在のカウント
  int count = prepare_count_down;
  // カウントを減らすかどうか
  boolean do_count = false;

  // インスタンス生成
  static Score sc = new Score();
  Font font1 = new Font("ＭＳＰゴシック", Font.PLAIN,25);

  //タイマー
  Timer timer = new Timer(true);
  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      if ( count > 0 ) {
        if(do_count){
          count--;
        }
      }else {
        end();
      }
    }
  };

  public Score(){
		//タイマーの起動
		timer.scheduleAtFixedRate(task, 1000, 1000);
  }
  //スコアスタート
	public void start() {
    count = prepare_count_down;
    do_count = true;
	}

  // スコア終了
  public void end(){
    if(do_count){
      // タイマーが0になったときの処理はまだ
      System.out.println("End");
    }
    do_count = false;
  }
  // スコア一時停止
  public void stop() {
    do_count = false;
  }

  // スコア再開
  public void restart() {
    do_count = true;
  }

  static Score get_instance(){
    return sc;
  }

  public int get_count(){
    return count;
  }

  public void draw(Graphics g) {
    g.setColor(Color.white);
		g.setFont(font1);
    g.drawString(""+count, 800, 25);
  }
}
