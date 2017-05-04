package com.qinlei.customview3.dingwei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qinlei.customview3.R;

public class DingdingDingWeiActivity extends AppCompatActivity {
    private DingDingDingWeiView dingWeiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingding_ding_wei);
        dingWeiView = (DingDingDingWeiView) findViewById(R.id.dingwei);
    }

    public void start(View view) {
        dingWeiView.startScan();
    }

    public void stop(View view) {
        dingWeiView.stopScan();
    }
}
