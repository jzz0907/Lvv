package com.lv.mama.lv.kind.model;


import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;
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
 * Created by 景瑾 on 2017/11/14.
 */

public class Kmodel implements Kimodel {
    // 定义接口变量
    private OnFinishLisenter lisenter;
    //定义接口
    public interface OnFinishLisenter{
        void onFinish(DataleftBean userBean);
    }
    public void setOnFinishLisenter(OnFinishLisenter lisenter){
        this.lisenter = lisenter;
    }
    // 定义接口变量
    private OnFinis lisent;
    //定义接口
    public interface OnFinis{
        void onF(DateGridBean userBean);
    }
    public void setOnFin(OnFinis lisent){
        this.lisent = lisent;
    }
    // 定义接口变量
    private OnFi lisen;
    //定义接口
    public interface OnFi{
        void onFin(DatarightBean userBean);
    }
    public void setOnFini(OnFi lisen){
        this.lisen = lisen;
    }
    @Override
    public void da() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_PATH).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiServer apiser = retrofit.create(ApiServer.class);
        Observable<DataleftBean> gethom = apiser.gethom();
        gethom.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataleftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataleftBean home) {
                        lisenter.onFinish(home);
                    }
                });
    }

    @Override
    public void dat(String gc_id, int position) {
        Map<String,String> map=new HashMap<>();
        map.put("gc_id",gc_id);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_ER).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiServer apiService = retrofit.create(ApiServer.class);

        Observable<DatarightBean> postpages = apiService.pos("index.php?act=goods_class",map);

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DatarightBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DatarightBean pageBean) {

                lisen.onFin(pageBean);
            }
        });
    }

    @Override
    public void dat2(String gc_id, int position) {
        Map<String,String> map=new HashMap<>();
        map.put("gc_id",gc_id);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_ER).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        ApiServer apiService = retrofit.create(ApiServer.class);

        Observable<DateGridBean> postpages = apiService.poss("index.php?act=goods_class",map);

        postpages.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer< DateGridBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext( DateGridBean pageBean) {

                lisent.onF(pageBean);
            }
        });
    }
}
