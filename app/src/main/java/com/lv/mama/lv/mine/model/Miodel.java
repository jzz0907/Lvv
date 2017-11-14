package com.lv.mama.lv.mine.model;

import com.lv.mama.lv.mine.bean.Mydeng;
import com.lv.mama.lv.mine.bean.Myzhu;
import com.lv.mama.lv.utils.Api;
import com.lv.mama.lv.utils.ApiServer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 景瑾 on 2017/11/13.
 */

public class Miodel implements Mimodel{
    // 定义接口变量
    private OnFinishLisenter lisenter;
    //定义接口
    public interface OnFinishLisenter{
        void onFinish(String userBean);
    }
    public void setOnFinishLisenter(OnFinishLisenter lisenter){
        this.lisenter = lisenter;
    }
    @Override
    public void deng(String name, String password) {
        Map<String,String> map=new HashMap<>();

        map.put("mobile",name);
        map.put("password",password);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.de).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiServer apiService = retrofit.create(ApiServer.class);

        Observable<Mydeng> postpages = apiService.po("user/login",map);

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Mydeng>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Mydeng mydeng) {
                String code = mydeng.getCode();
                lisenter.onFinish(code);
            }
        });
    }

    @Override
    public void zhu(String name, String password) {
        Map<String,String> map=new HashMap<>();

        map.put("mobile",name);
        map.put("password",password);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.de).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiServer apiService = retrofit.create(ApiServer.class);

        Observable<Myzhu> postpages = apiService.postpage("user/reg",map);

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Myzhu>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Myzhu myzhu) {
                String code = myzhu.getCode();
                lisenter.onFinish(code);
            }


        });
    }
}
