package com.lv.mama.lv.utils;

import com.lv.mama.lv.home.bean.Mysup;
import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;
import com.lv.mama.lv.mine.bean.Mydeng;
import com.lv.mama.lv.mine.bean.Myzhu;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {

    @GET("umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Observable<Mysup> getHome();

   /* @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<HomeBeans> getHomes();*/
   @POST
   Observable<Mydeng> po(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<Myzhu> postpage(@Url String url, @QueryMap Map<String,String> map);
    @GET("index.php?act=goods_class")
    Observable<DataleftBean> gethom();
    @POST
    Observable<DatarightBean> pos(@Url String url, @QueryMap Map<String,String> map);

    @POST
    Observable< DateGridBean> poss(@Url String url, @QueryMap Map<String,String> map);
}
