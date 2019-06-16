package com.SkyNet.main;

import android.content.Intent;
import android.os.Looper;
import android.support.annotation.Nullable;
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

public class RegisterActivity extends AppCompatActivity {

    EditText edit_userid;
    EditText edit_userpwd;
    Button btn_reg;
    TextView tx_login;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        edit_userid = findViewById(R.id.edit_userid);
        edit_userpwd = findViewById(R.id.edit_userpwd);
        tx_login = findViewById(R.id.tx_log);
        btn_reg = findViewById(R.id.btn_reg);
        img_back = findViewById(R.id.img_regback);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String userid = edit_userid.getText().toString();
                        String userpwd = edit_userpwd.getText().toString();

                        Looper.prepare();

                        if (!userid.equals("") && !userpwd.equals("")) {
                            OP op = new OP();
                            if (op.register(userid, userpwd)) {
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplication(), "注册成功", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplication(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                }).start();
            }
        });

        tx_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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
