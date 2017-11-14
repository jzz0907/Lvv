package com.lv.mama.lv.home.presenter;

import com.lv.mama.lv.home.bean.Mysup;
import com.lv.mama.lv.home.model.Mmodel;
import com.lv.mama.lv.home.view.Iview;

/**
 * Created by 景瑾 on 2017/11/8.
 */

public class Mpresenter implements Mmodel.OnFinishLisenter{
    private final Iview iview;
    private final Mmodel mmodel;

    public Mpresenter(Iview iview) {
        this.iview = iview;
        this.mmodel =new Mmodel();
    }
    public void login(){
        mmodel.setLisenter(this);
        mmodel.login();


    }
    @Override
    public void onFinish(Mysup.DataBean dat) {
        iview.onLoginSuccess(dat);
    }
}
