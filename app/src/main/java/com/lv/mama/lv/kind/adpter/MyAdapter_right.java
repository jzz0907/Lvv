package com.lv.mama.lv.kind.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.lv.mama.lv.R;
import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;
import com.lv.mama.lv.kind.presenter.Kpresenter;
import com.lv.mama.lv.kind.utils.GsonObjectCallback;
import com.lv.mama.lv.kind.utils.OkHttp3Utils;
import com.lv.mama.lv.kind.view.Kview;
import com.lv.mama.lv.utils.Api;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * autour: 樊彦龙
 * date: 2017/10/20 13:21
 * update: 2017/10/20
 */

public class MyAdapter_right extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Kview{
    private Context context;
    private List<DatarightBean.DatasBean.ClassListBean> list;

    Kpresenter kpresenter = new Kpresenter(this);
    public MyAdapter_right(Context context, List<DatarightBean.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.typeson_item, parent, false);
        final MyLeftViewHolder leftViewHolder = new MyLeftViewHolder(view);
        return leftViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //设置种类标题
        final MyLeftViewHolder  myHolder = new MyLeftViewHolder(holder.itemView);
        //设置标题
        myHolder.tv_left_type.setText(list.get(position).getGc_name());

        /*kpresenter.lo3(list.get(position).getGc_id(),position);*/
        //第三次请求网络 获取第三级数据
        OkHttp3Utils.doGet(Api.TYPE_PATHG+ "&gc_id=" + list.get(position).getGc_id(), new GsonObjectCallback<DateGridBean>() {
            @Override
            public void onUi(DateGridBean dateGridBean) {
              myHolder.gv.setAdapter(new MyAdapter_TypeGridView(context,dateGridBean.getDatas().getClass_list()));
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onLoginSuccess(DataleftBean a) {

    }

    @Override
    public void onLoginFailed(DatarightBean pageBean) {

    }

    @Override
    public void onLogin(DateGridBean pageBean) {

    }

    public class MyLeftViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_left_type;
        private GridView gv;
        public MyLeftViewHolder(View itemView) {
            super(itemView);
            tv_left_type = (TextView) itemView.findViewById(R.id.tv_type);
            gv = (GridView) itemView.findViewById(R.id.type_son);
        }
    }

    //声明成员变量
    public OnRecycleViewItemClickListener recycleViewItemClickListener;

    //定义点击接口
    public interface OnRecycleViewItemClickListener{
        void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder);
    }

    //提供set方法
    public void setRecycleViewItemClickListener(OnRecycleViewItemClickListener recycleViewItemClickListener) {
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }
}
