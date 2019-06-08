package com.SkyNet.main;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.SkyNet.R;


public class PlayVideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;
    private String path = "";
    private String name = "";
    private TextView videoName;


    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        initView();
    }

    private void initView(){

        videoView = ((VideoView) findViewById(R.id.videoView));
        videoName = ((TextView) findViewById(R.id.videoName));

        bundle = this.getIntent().getExtras();
        path = bundle.getString("path");
        name = bundle.getString("name");
        videoName.setText(name);
        Uri uri = Uri.parse(path);

        mediaController = new MediaController(this);
        videoView.setVideoURI(uri);
        videoView.start();
        //获取焦点
        videoView.requestFocus();

    }
}
