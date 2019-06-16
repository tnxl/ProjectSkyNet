package com.SkyNet.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.SkyNet.DB.DataBase;
import com.SkyNet.DB.DataInfo;
import com.SkyNet.R;

public class WelcomeActivity extends AppCompatActivity {

    Button signin;
    Button signup;
    
    Button btn_confirm;
    EditText edit_ip;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        signin = findViewById(R.id.btn_signin);
        signup = findViewById(R.id.btn_signup);
        
        btn_confirm = findViewById(R.id.wel_btn);
        edit_ip = findViewById(R.id.wel_ip);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_ip.getText().toString()!=null){
                    DataInfo.setIp(edit_ip.getText().toString());
                    Toast.makeText(WelcomeActivity.this, "已更新IP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
