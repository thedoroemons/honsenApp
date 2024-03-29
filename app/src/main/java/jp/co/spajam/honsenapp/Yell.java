package jp.co.spajam.honsenapp;

/**
 * 応援オブジェクト
 * Created by fuji on 2015/07/05.
 */
public class Yell {
    // ユーザーのニックネーム
    private String mName;
    // 地方ID (北海道:0 東北:1 関東:2 中部:3 近畿:4 中国:5 四国:6 九州:7)
    private int mArea;
    // 声の音量 (1~5) (文字の大きさに反映)
    private int mVol;
    // 声色 (1~3) (文字色に反映)
    private int mType;
    // 現在の玉の大きさ
    private int mTamaSize;

    public Yell() {

    }

    public Yell(String name, int area, int vol, int type) {
        mName = name;
        mArea = area;
        mVol  = vol;
        mType = type;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getArea() {
        return mArea;
    }

    public void setArea(int area) {
        mArea = area;
    }

    public int getVol() {
        return mVol;
    }

    public void setVol(int vol) {
        mVol = vol;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getTamaSize() {
        return mTamaSize;
    }

    public void setTamaSize(int tamaSize) {
        mTamaSize = tamaSize;
    }

    @Override
    public String toString() {
        return "Yell{" +
                "mName='" + mName + '\'' +
                ", mArea=" + mArea +
                ", mVol=" + mVol +
                ", mType=" + mType +
                ", mTamaSize=" + mTamaSize +
                '}';
    }
}
