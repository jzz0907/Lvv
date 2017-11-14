package com.lv.mama.lv.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lv.mama.lv.R;
import com.lv.mama.lv.home.bean.MyAdapter;
import com.lv.mama.lv.home.bean.Mysup;
import com.lv.mama.lv.home.presenter.Mpresenter;
import com.lv.mama.lv.home.view.Iview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 景瑾 on 2017/11/9.
 */

public class HomeFragment extends Fragment implements Iview{

    private Mpresenter mpresenter;
    private ImageView im1;
    private List<Mysup.DataBean.Ad1Bean> ad1;
    private View view;
    private XRecyclerView recyclerView;
    private Context context;
    private ArrayList<String> list_path1;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    //数据集合
    private List<String > list=new ArrayList<>();
    //获取数据的开始
    private int curr;
    private MyAdapter adapter;
    private List<Mysup.DataBean.Ad5Bean> ad5;
    // 倒计时
    private RelativeLayout countDown;
    private TextView daysTv, hoursTv, minutesTv, secondsTv;
    private long mDay = 10;
    private long mHour = 10;
    private long mMin = 30;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;

    private List<Mysup.DataBean.ActivityInfoBean.ActivityInfoListBean> ad11;
    private List<Mysup.DataBean.DefaultGoodsListBean> ad6;
    private String s;
    private List<Mysup.DataBean.SubjectsBean.GoodsListBeanX> goods;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,null);
        mpresenter = new Mpresenter(this);
        mpresenter.login();
        im1=view.findViewById(R.id.img1);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 1);*/
            }
        });
        context=getActivity();
        recyclerView=(XRecyclerView)view.findViewById(R.id.xre_xrv);

        //LinearLayoutManager是线性布局，setOrientation可以设置他的方向是横向还是纵向。
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            //下拉刷新
            public void onRefresh() {

            }

            @Override
            //上拉加载
            public void onLoadMore() {

            }
        });
        return view;
    }

    @Override
    public void onLoginSuccess(Mysup.DataBean a) {
        ad1 = a.getAd1();
        ad5 = a.getAd5();
        goods = a.getSubjects().get(2).getGoodsList();
        ad11 = a.getActivityInfo().getActivityInfoList();
        list_path = new ArrayList<>();
        s = a.getSubjects().get(2).getDescImage();
        ad6 = a.getDefaultGoodsList();
        //放标题的集合
        list_title = new ArrayList<>();
        for (int i = 0; i < ad1.size(); i++) {
            list_path.add(ad1.get(i).getImage().toString());
            list_title.add(ad1.get(i).getTitle().toString());
        }
        list_path1 = new ArrayList<>();

        for (int i = 0; i < ad11.size(); i++) {
            list_path1.add(ad11.get(i).getActivityImg().toString());
        }
        recyclerView.setAdapter(new MyAdapter(getActivity(),a));
    }
}
