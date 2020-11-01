package com.swufe.memoapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private String realCode;
    private DBHelper mDBHelper;
    private Button button3,button4;
    private EditText edit3,edit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initView();

        mDBHelper = new DBHelper(this);
    }

    private void initView(){
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);

        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4: //返回登录页面
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.button3:    //注册按钮
                //获取用户输入的用户名、密码
                String username = edit3.getText().toString().trim();
                String password = edit4.getText().toString().trim();

                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)  ) {

                    //将用户名和密码加入到数据库中
                    mDBHelper.add(username, password);
                    //Intent intent2 = new Intent(this, LoginActivity.class);
                    //startActivity(intent2);
                    //finish();
                    Toast.makeText(this,  "您已成功注册", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "您的信息还未完善，请完善信息", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
