package com.lv.mama.lv.home.model;

import com.lv.mama.lv.home.bean.Mysup;
import com.lv.mama.lv.utils.RetroFactory;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 景瑾 on 2017/11/8.
 */

public class Mmodel implements Imodel {
    private OnFinishLisenter lisenter;
    public interface OnFinishLisenter{
        void onFinish(Mysup.DataBean dat);
    }
    public void setLisenter(OnFinishLisenter lisenter) {
        this.lisenter = lisenter;
    }

    @Override
    public void login() {
        Observable<Mysup> home = RetroFactory.getInstance().getHome();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Mysup>() {

                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Mysup mysup) {
                        Mysup.DataBean data = mysup.getData();
                        lisenter.onFinish(data);

                    }
                });

    }
}
