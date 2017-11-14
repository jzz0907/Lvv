package com.lv.mama.lv.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lv.mama.lv.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
