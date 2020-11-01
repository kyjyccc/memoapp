package com.swufe.memoapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper mDBHelper;
    private EditText edit1,edit2;
    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initView();
        mDBHelper = new DBHelper(this);
    }

    private void initView() {
        // 初始化控件
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        // 设置点击事件监听器
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.button2:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.button1:
                String name = edit1.getText().toString().trim();
                String password = edit2.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "您已成功登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "您输入的用户名或密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入您的用户名和密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}