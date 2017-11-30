package com.example.administrator.goodshop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Basectivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    public void initData(){}
    //初始化控件
    public void initView(){}

}
