package com.kuduta.navrefill;

/**
 * Created by osx on 10/19/2017 AD.
 */
//strNumPhone + " , " + strMoney + " , " + strExpire
public class Data {
    private String mNumphon ;
    private String mMoney;
    private String mExpire;

    public Data(String numphone , String money, String expire){

        mNumphon = numphone;
        mMoney = money;
        mExpire = expire;
    }

    public String getmNumphon() {
        return mNumphon;
    }

    public void setmNumphon(String mNumphon) {
        this.mNumphon = mNumphon;
    }

    public String getmMoney() {
        return mMoney;
    }

    public void setmMoney(String mMoney) {
        this.mMoney = mMoney;
    }

    public String getmExpire() {
        return mExpire;
    }

    public void setmExpire(String mExpire) {
        this.mExpire = mExpire;
    }
}
