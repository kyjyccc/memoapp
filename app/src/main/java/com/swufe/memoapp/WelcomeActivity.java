package com.swufe.memoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import  android.content.Intent;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private int recLen = 4;//跳过倒计时提示4秒
    private TextView text1,text2;
    private Handler handler;
    private Runnable runnable;

    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_welcome);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        //不点击跳过
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);//延迟4S后发送handler信息
    }

    private void initView() {
        text1 = findViewById(R.id.text1);//跳过
        text1.setOnClickListener(this);//跳过监听
    }

        TimerTask task = new TimerTask() {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    text1.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        text1.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    //点击跳过
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text1:
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }

}