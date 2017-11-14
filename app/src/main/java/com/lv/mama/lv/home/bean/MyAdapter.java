package com.lv.mama.lv.home.bean;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lv.mama.lv.R;
import com.lv.mama.lv.home.Fragment_Home_Banner_adapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by 景瑾 on 2017/11/9.
 */


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
   private Mysup.DataBean a;
    private  Context context;
    ArrayList<String> list=new ArrayList<>();

    // 倒计时
    private RelativeLayout countDown;
    private TextView daysTv, hoursTv, minutesTv, secondsTv;
    private long mDay = 10;
    private long mHour = 10;
    private long mMin = 30;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                daysTv.setText(mDay+"");
                hoursTv.setText(mHour+"");
                minutesTv.setText(mMin+"");
                secondsTv.setText(mSecond+"");
                if (mDay==0&&mHour==0&&mMin==0&&mSecond==0) {
                    countDown.setVisibility(View.GONE);
                }
            }
        }
    };
    public MyAdapter(Context context, Mysup.DataBean a) {
        this.context = context;
        this.a = a;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                //给Adapter添加布局，bq把这个view传递给HoldView，让HoldView找到空间
                View view= LayoutInflater.from(context).inflate(R.layout.buju1, parent,false);
                HoldView holdView=new HoldView(view);
                return holdView;
            case 1:
                View view1= LayoutInflater.from(context).inflate(R.layout.buju2, parent,false);
                HoldView2 holdView1=new HoldView2(view1);
                return holdView1;
            case 2:
                View view2= LayoutInflater.from(context).inflate(R.layout.buju3, parent,false);
                HoldView3 holdView2=new HoldView3(view2);
                return holdView2;
            case 3:
                //给Adapter添加布局，bq把这个view传递给HoldView，让HoldView找到空间
                View view3= LayoutInflater.from(context).inflate(R.layout.buju1, parent,false);
                HoldView holdView3=new HoldView(view3);
                return holdView3;
            case 6:
                View view4= LayoutInflater.from(context).inflate(R.layout.buju2, parent,false);
                HoldView2 holdView4=new HoldView2(view4);
                return holdView4;
            case 4:
                View view5= LayoutInflater.from(context).inflate(R.layout.recycle_item_d, parent,false);
                ViewHolderD viewHolder = new ViewHolderD(view5);
                return viewHolder;
            case 5:
                View view6= LayoutInflater.from(context).inflate(R.layout.recycle_item_e, parent,false);
                ViewHolderE viewHolder1 = new ViewHolderE(view6);
                return viewHolder1;

        }
        return  null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
               /* Log.d("main",list.size()+"-------------");
                //position为Adapter的位置，数据从list里面可以拿出来。
                //设置内置样式，共有六种可以点入方法内逐一体验使用。
                ((HoldView)holder).banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                //设置图片加载器，图片加载器在下方
                ((HoldView)holder).banner.setImageLoader((ImageLoaderInterface) new MyLoader());
                //设置图片网址或地址的集合
                ((HoldView)holder).banner.setImages(list);
                //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验

                //设置轮播图的标题集合
            //    ((HoldView)holder).banner.setBannerTitles(a.getAd1().get(position));
                //设置轮播间隔时间
                ((HoldView)holder).banner.setDelayTime(3000);
                //设置是否为自动轮播，默认是“是”。
                ((HoldView)holder).banner.isAutoPlay(true);
                //设置指示器的位置，小点点，左中右。
                ((HoldView)holder).banner.setIndicatorGravity(BannerConfig.CENTER)
                        //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                        //必须最后调用的方法，启动轮播图。
                    .start();*/
                Fragment_Home_Banner_adapter fragment_home_banner_adapter = new Fragment_Home_Banner_adapter(((HoldView) holder).banner, a.getAd1());
                fragment_home_banner_adapter.setBanner();
                break;
            case 1:
                HoldView2 h= (HoldView2) holder;
                h.re.setLayoutManager(new GridLayoutManager(context, a.getAd5().size()));
                HomeAdapter mAdapter;
                h. re.setAdapter( mAdapter= new HomeAdapter(context,a.getAd5()));
                mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(context, "点击+"+position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(context, "长按"+position, Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 2:
                startRun();
                break;
            case 3:
                HoldView hh= (HoldView) holder;
                //设置图片加载器，图片加载器在下方
                hh.banner.setImageLoader(new MyLoader());
                //设置图片网址或地址的集合
           //     hh.banner.setImages(list_path1);
                //设置轮播图的标题集合
                //设置指示器的位置，小点点，左中右。
                hh.banner.start();
                Fragment_Home_Banner_adapter2 fragment_home_banner_adapter2 = new Fragment_Home_Banner_adapter2(((HoldView) holder).banner, a.getActivityInfo().getActivityInfoList());
                fragment_home_banner_adapter2.setBanner();
                break;
            case 6:
                HoldView2 h1= (HoldView2) holder;
                h1.re.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                h1.re.setAdapter(new HomeAdapter1(a.getDefaultGoodsList(),context));
                break;
            case 4:

                ViewHolderD holderD= (ViewHolderD) holder;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(a.getSubjects().get(2).getDescImage(),((ViewHolderD) holder).d_img);

                break;
            case 5:

                ViewHolderE holderE= (ViewHolderE) holder;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                holderE.recy2.setLayoutManager(linearLayoutManager);
                Home_fourAdpater adapter2 = new Home_fourAdpater(a.getSubjects().get(2).getGoodsList(),context);
                holderE.recy2.setAdapter(adapter2);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 7;
    }
    private class HoldView extends RecyclerView.ViewHolder{
        private Banner banner;
        public HoldView(View itemView) {
            super(itemView);
            //根据onCreateViewHolder的HoldView所添加的xml布局找到空间
            banner=itemView.findViewById(R.id.banner);
        }
    }
    private class HoldView2 extends RecyclerView.ViewHolder{
        private RecyclerView re;
        public HoldView2(View itemView) {
            super(itemView);
            //根据onCreateViewHolder的HoldView所添加的xml布局找到空间
            re=itemView.findViewById(R.id.id_recyclerview);
        }
    }
    class ViewHolderD extends RecyclerView.ViewHolder {

         public Banner mybanner1;
        public ImageView d_img;
        public ViewHolderD(View itemView) {
            super(itemView);
           /* mybanner1 = (Banner) itemView.findViewById(R.id.mybanner1);*/
            d_img=(ImageView) itemView.findViewById(R.id.d_img);
        }
    }
    class ViewHolderE extends RecyclerView.ViewHolder {
        RecyclerView recy2;
        public ViewHolderE(View itemView) {
            super(itemView);
            recy2=(RecyclerView) itemView.findViewById(R.id.recy2);
        }
    }
    private class HoldView3 extends RecyclerView.ViewHolder{

        public HoldView3(View itemView) {
            super(itemView);
            //根据onCreateViewHolder的HoldView所添加的xml布局找到空间
            daysTv =  itemView.findViewById(R.id.days_tv);
            hoursTv =  itemView.findViewById(R.id.hours_tv);
            minutesTv =  itemView.findViewById(R.id.minutes_tv);
            secondsTv = itemView.findViewById(R.id.seconds_tv);

        }
    }
    //自定义的图片加载器
    private class MyLoader extends ImageLoader {

        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    } private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 23;
                    mDay--;
                }
            }
        }
    }
}