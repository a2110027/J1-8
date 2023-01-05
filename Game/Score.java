import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

/**
 * スコアを表示するクラス
 * @author 綾部
 */
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

  /**
   * インスタンス
   * タイマーを起動する
   */
  public Score(){
		//タイマーの起動
		timer.scheduleAtFixedRate(task, 1000, 1000);
  }
  /**
   * カウントダウンタイマーをスタートさせる
   */
	public void start() {
    count = prepare_count_down;
    do_count = true;
	}

  /**
   * カウントダウンタイマーを終了させる。
   */
  public void end(){
    if(do_count){
      // タイマーが0になったときの処理はまだ
      System.out.println("End");
    }
    do_count = false;
  }

  /**
   * カウントダウンタイマーをストップさせる。
   */
  public void stop() {
    do_count = false;
  }

  /**
   * カウントダウンタイマーを再開させる。
   */
  public void restart() {
    do_count = true;
  }

  /**
   * インスタンスを返す
   * @return
   */
  static Score get_instance(){
    return sc;
  }

  /**
   * カウントを返す
   * @return 現在のカウントダウンタイマーの値
   */
  public int get_count(){
    return count;
  }

  /**
   * カウントダウンタイマーを描画する
   * @param g
   */
  public void draw(Graphics g) {
    g.setColor(Color.white);
		g.setFont(font1);
    g.drawString(""+count, 800, 25);
  }
}
