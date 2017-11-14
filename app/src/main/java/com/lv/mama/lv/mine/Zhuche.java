package com.lv.mama.lv.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.mama.lv.R;
import com.lv.mama.lv.mine.presenter.Mipresenter;
import com.lv.mama.lv.mine.view.Miview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Zhuche extends AppCompatActivity implements Miview {

    @BindView(R.id.deng)
    Button deng;
    @BindView(R.id.ed2)
    EditText ed2;
    @BindView(R.id.ed1)
    EditText ed1;
    private Mipresenter mipresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuche);
        ButterKnife.bind(this);
        mipresenter = new Mipresenter(this);

    }

    @Override
    public void onLoginSuccess(String userBean) {
        if (userBean.equals("0")) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "注册失败"+userBean, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginFailed() {

    }

    @OnClick(R.id.deng)
    public void onViewClicked() {
        String trim = ed1.getText().toString().trim();
        String trim2 = ed2.getText().toString().trim();
        Toast.makeText(this, "名字"+trim+"密码"+trim2, Toast.LENGTH_SHORT).show();
        mipresenter.zhu(trim,trim2);
    }
}
