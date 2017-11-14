package com.lv.mama.lv.mine.presenter;

import com.lv.mama.lv.mine.model.Miodel;
import com.lv.mama.lv.mine.view.Miview;

/**
 * Created by 景瑾 on 2017/11/13.
 */

public class Mipresenter implements Miodel.OnFinishLisenter{
    private final Miview userView;
    private final Miodel userMode;


    public Mipresenter(Miview userView) {
        this.userView = userView;
        this.userMode = new Miodel();
    }
    public void login(String name, String password){
        userMode.setOnFinishLisenter(this);
        userMode.deng(name,password);


    }
    public void zhu(String name, String password){
        userMode.setOnFinishLisenter(this);
        userMode.zhu(name,password);


    }

    @Override
    public void onFinish(String userBean) {
        userView.onLoginSuccess(userBean);
    }
}
