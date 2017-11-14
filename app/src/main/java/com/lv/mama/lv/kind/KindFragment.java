package com.lv.mama.lv.kind;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lv.mama.lv.R;
import com.lv.mama.lv.kind.adpter.MyAdapter_left;
import com.lv.mama.lv.kind.adpter.MyAdapter_right;
import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;
import com.lv.mama.lv.kind.bean.Datebeanitem;
import com.lv.mama.lv.kind.presenter.Kpresenter;
import com.lv.mama.lv.kind.utils.GsonObjectCallback;
import com.lv.mama.lv.kind.utils.OkHttp3Utils;
import com.lv.mama.lv.kind.view.Kview;
import com.lv.mama.lv.utils.Api;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by 景瑾 on 2017/11/9.
 */

public class KindFragment extends Fragment implements Kview{
    TextView mtv;
    private RecyclerView rv_left, rv_right;
    private View view;
    private Kpresenter kpresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.kind_fragment,null);
        initView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        //得到WindowManager
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //得到屏幕宽
        int width = display.getWidth();
        //将RecyclerView宽设置为屏幕宽的1/5
        params.width = width * 1 / 5;
        rv_left.setLayoutParams(params);
        //得到RecyclerView布局管理器
        LinearLayoutManager leftLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_left.setLayoutManager(leftLayoutManager);
        //得到RecyclerView布局管理器
        LinearLayoutManager rightLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_right.setLayoutManager(rightLayoutManager);
        //获取后台数据，添加适配器
       /* getServerData();*/
        kpresenter = new Kpresenter(this);
        kpresenter.lo();
        return view;
    }
    //获取控件的方法
    private void initView() {
        rv_left =  view.findViewById(R.id.type_rvleft);
        rv_right = view.findViewById(R.id.type_rvright);
    }


    //获取网络数据的方法
    public static void getServerData(Context context, String url, final OnGetServerDateLisnter onGetServerDateLisnter) {
        OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<Datebeanitem>() {
            @Override
            public void onUi(Datebeanitem datebeanitem) {
                onGetServerDateLisnter.getData(datebeanitem.getDatas().toString());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public void onLoginSuccess(final DataleftBean a) {
        //适配器
        final MyAdapter_left myAdapter_left= new MyAdapter_left(getActivity(), a.getDatas().getClass_list());
        rv_left.setAdapter(myAdapter_left);
        //第一个子条目显示其二级数据

        //子条目点击监听
        myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {
                myAdapter_left.setTagPosition(position);
                myAdapter_left.notifyDataSetChanged();
                //请求二级数据

                kpresenter.lo2(a.getDatas().getClass_list().get(position).getGc_id(),position);
            }
        });
    }

    @Override
    public void onLoginFailed(DatarightBean pageBean) {
        MyAdapter_right myAdapter_right = new MyAdapter_right(getActivity(), pageBean.getDatas().getClass_list());
        rv_right.setAdapter(myAdapter_right);
    }

    @Override
    public void onLogin(DateGridBean pageBean) {

    }

    public interface OnGetServerDateLisnter {
        void getData(String string);
    }
    //请求二级数据
    public void getServerTypeData(final String gc_id, final int position) {
        OkHttp3Utils.doGet(Api.TYPE_PATHG + "&gc_id=" + gc_id, new GsonObjectCallback<DatarightBean>() {
            @Override
            public void onUi(DatarightBean datarightBean) {

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
}
