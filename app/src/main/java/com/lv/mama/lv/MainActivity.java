package com.lv.mama.lv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.lv.mama.lv.cart.CartFragment;
import com.lv.mama.lv.home.HomeFragment;
import com.lv.mama.lv.kind.KindFragment;
import com.lv.mama.lv.mine.Deng;
import com.lv.mama.lv.mine.MineFragment;
import com.lv.mama.lv.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.homeRadioButton)
    RadioButton homeRadioButton;
    @BindView(R.id.classRadioButton)
    RadioButton classRadioButton;
    @BindView(R.id.cartRadioButton)
    RadioButton cartRadioButton;
    @BindView(R.id.userRadioButton)
    RadioButton userRadioButton;
    String TAG = "mylog";
    private FragmentManager s;
    private String ss;
    private FragmentTransaction fr;
    private HomeFragment fr1;
    private KindFragment fr2;
    private CartFragment fr3;
    private MineFragment fr4;
    private FragmentTransaction add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: 创建");
        ButterKnife.bind(this);
        SharedPreferencesUtils.setParam(this, "deng",false);

        /*System.out.println(ss.equals("any string"));*/
        s = getSupportFragmentManager();
        fr1 = new HomeFragment();
        fr2 = new KindFragment();
        fr3 =  new CartFragment();
        fr4 = new MineFragment();
        addFragment(fr1);
    }
    @OnClick({R.id.homeRadioButton, R.id.classRadioButton, R.id.cartRadioButton, R.id.userRadioButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeRadioButton:
                addFragment(fr1);
                break;
            case R.id.classRadioButton:
                addFragment(fr2);
                break;
            case R.id.cartRadioButton:

                addFragment(fr3);
                break;
            case R.id.userRadioButton:
                boolean deng = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "deng", false);
                if(deng==true){
                    addFragment(fr4);
                }else {
                    Intent in=new Intent(MainActivity.this,Deng.class);
                    startActivity(in);
                }

                break;
        }
    }
    //动态添加fragment
    public void addFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = s.beginTransaction();
        fragmentTransaction.replace(R.id.frm,fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: 重新启动");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: 开始");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: 视图");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 暂停");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: 停止");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 销毁");
    }
}
