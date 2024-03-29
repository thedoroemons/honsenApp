package jp.co.spajam.honsenapp;

import android.content.Context;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kenjendo on 2015/07/05.
 */
public class TamaHelper {

	private static final String TAG = TamaHelper.class.getSimpleName();
	private ImageView mTama;
	private static int mTamaSize = 50;
	private ViewGroup mParent; //たまの親のレイアウト
	private List<TextView> mNicknameList = new ArrayList();
	private static final int MAX_NICKNAME_NUM1 = 3;
	private static final int MAX_NICKNAME_NUM2 = 10;
	private static final int MAX_NICKNAME_NUM3 = 30;
	private static final int MAX_NICKNAME_NUM4 = 50;
	private static final double MAX_TAMA_MULTI = 2.0/3.0;
	private int mMaxTamaHeight;
	private int mRootWidth;
	private int mRootHeight;

	public TamaHelper(ImageView tama,ViewGroup parent) {
		mTama = tama;
		mParent = parent;
		mRootWidth = parent.getWidth();
		mRootHeight = parent.getHeight();
		mMaxTamaHeight = (int)(mRootHeight * MAX_TAMA_MULTI);
        // 大きさの初期化（共有データの分）
        tamaBig(0);
	}

	// たまを大きくする
	public void tamaBig(int vol) {

        // ３倍
        vol *= 2;

		ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)mTama.getLayoutParams();
		int currentHeight = mTama.getLayoutParams().height;

		// 親レイアウト(画面全体を想定)の上部MAX_TAMA_MULTIサイズまでを上限とする
		if (currentHeight > mMaxTamaHeight) {
			return;
		}

        // 引き継ぎ用球サイズ
        setTamaSize(currentHeight + vol);

		int afterWidth = mTamaSize * 2;
		int afterHeight = mTamaSize;
		int afterMarginTop = (int)(afterHeight * -0.5);


		mTama.getLayoutParams().width = afterWidth;
		mTama.getLayoutParams().height = afterHeight;
		mlp.topMargin = afterMarginTop;
		mTama.setLayoutParams(mlp);
		mTama.requestLayout();
	}

	// たまに打ち上げたyellのニックネームを表示
	public void showNameInTama(Context context,String nickname,int type,int vol) {
		Pair<Integer,Integer> topLeft = getRandomInTamaTopLeft();

		// nicknameを指定された色に
		int color = YellApplication.getColor(R.color.yell_red); //default
		if (type==1) {
			color = YellApplication.getColor(R.color.yell_red);
		} else if (type==2) {
			color = YellApplication.getColor(R.color.yell_blue);
		} else if (type==3) {
			color = YellApplication.getColor(R.color.yell_yellow);
		}

		int fontSizeSP;
		if (vol==1) {
			fontSizeSP = 12;
		} else if (vol==2) {
			fontSizeSP = 14;
		} else if (vol==3) {
			fontSizeSP = 16;
		} else if (vol==4) {
			fontSizeSP = 18;
		} else if (vol==5) {
			fontSizeSP = 20;
		} else {
			fontSizeSP = 12;
		}

		// nicknameを表示
		int width = YellApplication.dp2int(50); //　適当
		int height = YellApplication.dp2int(10);//　適当
		final TextView nicknameText = new TextView(context);
		nicknameText.setText(nickname);
		nicknameText.setTextSize(fontSizeSP);//sp
		nicknameText.setLines(1);
		nicknameText.setTextColor(color);
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		int top = topLeft.first - height; //位置調整
		int left = topLeft.second - width;
		rlp.topMargin = top;
		rlp.leftMargin = left;
		mParent.addView(nicknameText, rlp);
		mNicknameList.add(nicknameText);

		double tamaHeight = mTama.getLayoutParams().height;
		if (tamaHeight / mMaxTamaHeight < 0.2) {
			if (mNicknameList.size()>MAX_NICKNAME_NUM1) {
				int removeNum = mNicknameList.size() - MAX_NICKNAME_NUM1;
				for (int i=0;i<removeNum;i++) {
					TextView removeNickname = mNicknameList.get(i);
					mParent.removeView(removeNickname);
				}
			}
		} else if (tamaHeight / mMaxTamaHeight < 0.65) {
			if (mNicknameList.size()>MAX_NICKNAME_NUM2) {
				int removeNum = mNicknameList.size() - MAX_NICKNAME_NUM2;
				for (int i=0;i<removeNum;i++) {
					TextView removeNickname = mNicknameList.get(i);
					mParent.removeView(removeNickname);
				}
			}
		} else if (tamaHeight / mMaxTamaHeight < 0.8){
			if (mNicknameList.size()>MAX_NICKNAME_NUM3) {
				int removeNum = mNicknameList.size() - MAX_NICKNAME_NUM3;
				for (int i=0;i<removeNum;i++) {
					TextView removeNickname = mNicknameList.get(i);
					mParent.removeView(removeNickname);
				}
			}
		} else {
			if (mNicknameList.size()>MAX_NICKNAME_NUM4) {
				int removeNum = mNicknameList.size() - MAX_NICKNAME_NUM4;
				for (int i=0;i<removeNum;i++) {
					TextView removeNickname = mNicknameList.get(i);
					mParent.removeView(removeNickname);
				}
			}
		}

	}

	// たまのなかの適当な座標をランダムで返す
	private Pair<Integer,Integer> getRandomInTamaTopLeft() {
		int rLeft;
		int rTop;

		while (true) {
			// 楕円を内包する矩形領域内でランダムな座標(rLeft,rTop)を発行する
			int width = mTama.getLayoutParams().width;
			int height = (int) (mTama.getLayoutParams().height * 0.5);
			int leftStart = mTama.getLeft();
			int leftEnd = leftStart + width;
			int topStart = 0;
			int topEnd = topStart + height;

			Random r = new Random();
			int rWidth = r.nextInt(width + 1); //widht10とすると 0〜10のランダムな数
			int rHeight = r.nextInt(height + 1);
			rLeft = leftStart + rWidth;
			rTop = topStart + rHeight;

			// 発行したランダムな座標が楕円に内接する二等辺三角形の内部か判定
			double heightPos = 1 - ((double)rHeight / (double)height);
			int b = (int) (height * 0.5); ///楕円の高さ/2
			int a = (int) (width * 0.5); //楕円の幅/2
			int centerX = leftStart + a; //楕円の中心座標x
			int centerY = topStart + b;
			int minX = centerX - (int)(width * heightPos);
			int maxX = centerX + (int)(width * heightPos);
			if (rLeft >= minX && rLeft <= maxX) {
				break;
			}
		}

		return Pair.create(rTop,rLeft);
	}

	public void setTamaSize(int tamasize) {
        Log.d("TEST",  mTamaSize + ":" + tamasize);
		if(mTamaSize < tamasize){
			mTamaSize = tamasize;
		}
	}

    public int getTamaSize() {
        return  Math.max(mTamaSize, mTama.getLayoutParams().height);
	}
}
