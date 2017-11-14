package com.lv.mama.lv.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.mama.lv.R;
import com.lv.mama.lv.mine.presenter.Mipresenter;
import com.lv.mama.lv.mine.view.Miview;
import com.lv.mama.lv.utils.MessageEvent;
import com.lv.mama.lv.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Deng extends AppCompatActivity implements Miview {

    @BindView(R.id.deng)
    Button deng;
    @BindView(R.id.tex3)
    TextView tex3;
    @BindView(R.id.dishanf)
    ImageView dishanf;
    @BindView(R.id.ed2)
    EditText ed2;
    @BindView(R.id.ed1)
    EditText ed1;
    private Mipresenter mipresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng);
        ButterKnife.bind(this);
        mipresenter = new Mipresenter(this);
    }

    @OnClick({R.id.deng, R.id.tex3, R.id.dishanf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.deng:
                String trim = ed1.getText().toString().trim();
                String trim2 = ed2.getText().toString().trim();
                Toast.makeText(this, "名字"+trim+"密码"+trim2, Toast.LENGTH_SHORT).show();
                mipresenter.login(trim,trim2);
                break;
            case R.id.tex3:
                Intent in=new Intent(Deng.this,Zhuche.class);
                startActivity(in);
                break;
            case R.id.dishanf:
                break;
        }
    }

    @Override
    public void onLoginSuccess(String userBean) {
        if (userBean.equals("0")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            String trim = ed1.getText().toString().trim();
            EventBus.getDefault().post(new MessageEvent(trim));
            SharedPreferencesUtils.setParam(Deng.this, "deng",true);
            finish();
        } else {
            Toast.makeText(this, "登录失败"+userBean, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginFailed() {

    }
}
