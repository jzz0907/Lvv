package com.lv.mama.lv.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.mama.lv.R;
import com.lv.mama.lv.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 景瑾 on 2017/11/9.
 */

public class MineFragment extends Fragment {
    @BindView(R.id.Head_portrait)
    ImageView HeadPortrait;
    Unbinder unbinder;
    @BindView(R.id.Number)
    TextView Number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        //注册事件

        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.Head_portrait)
    public void onViewClicked() {
        Intent in = new Intent(getActivity(), Deng.class);
        startActivity(in);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ononMoonStickyEvent(MessageEvent messageEvent) {

        Number.setText(messageEvent.getMessage());
    }
}
