package com.SkyNet.main;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SkyNet.DB.OP;
import com.SkyNet.R;

public class LoginActivity extends AppCompatActivity {

    EditText edit_user;
    EditText edit_pass;
    Button btn_login;
    TextView tx_reg;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        edit_user = findViewById(R.id.userid);
        edit_pass = findViewById(R.id.userpwd);
        btn_login = findViewById(R.id.btn_login);
        tx_reg = findViewById(R.id.tx_reg);
        img_back = findViewById(R.id.img_logback);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String username = edit_user.getText().toString();
                        String password = edit_pass.getText().toString();

                        Looper.prepare();
                        OP op = new OP();
                        if (!username.equals("") && !password.equals("")) {
                            if (op.login(username, password)) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplication(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplication(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();

                        }
                        Looper.loop();
                    }
                }).start();
            }
        });

        tx_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

}

