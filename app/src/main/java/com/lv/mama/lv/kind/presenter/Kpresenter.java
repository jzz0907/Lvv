package com.lv.mama.lv.kind.presenter;

import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;
import com.lv.mama.lv.kind.model.Kmodel;
import com.lv.mama.lv.kind.view.Kview;

/**
 * Created by 景瑾 on 2017/11/14.
 */

public class Kpresenter implements Kmodel.OnFinishLisenter,Kmodel.OnFi,Kmodel.OnFinis{
    private final Kview userView;
    private final Kmodel userMode;

    public Kpresenter(Kview userView) {
        this.userView = userView;
        this.userMode = new Kmodel();
    }
    public void lo(){
        userMode.setOnFinishLisenter(this);
        userMode.da();


    }
    public void lo2(String gc_id, int position){
        userMode.setOnFini(this);
        userMode.dat(gc_id,position);


    }
    public void lo3(String gc_id, int position){
        userMode.setOnFin(this);
        userMode.dat2(gc_id,position);


    }


    @Override
    public void onFinish(DataleftBean userBean) {
        userView.onLoginSuccess(userBean);
    }

    @Override
    public void onFin(DatarightBean userBean) {
        userView.onLoginFailed(userBean);
    }

    @Override
    public void onF(DateGridBean userBean) {
        userView.onLogin(userBean);
    }
}
