package com.qinlei.customview3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qinlei.customview3.dingwei.DingdingDingWeiActivity;
import com.qinlei.customview3.loading.TouTiaoLoadingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DingWeiView(View view) {
        startActivity(new Intent(this, DingdingDingWeiActivity.class));
    }

    public void toutiaoview(View view) {
        startActivity(new Intent(this, TouTiaoLoadingActivity.class));
    }
}
