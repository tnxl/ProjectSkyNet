package com.SkyNet.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SkyNet.R;
import com.SkyNet.layout.MineFragment;
import com.SkyNet.main.MainActivity;
import com.SkyNet.util.ImageUtils;

import java.io.IOException;

public class PersonInfoActivity extends AppCompatActivity {

    EditText edit_id;
    Button btn_changeImg;
    Button btn_checkInfo;
    ImageView img_touxiang;
    ImageView imageView;
    Bundle bundle;

    private TextView photoPath;

    private static final int REQUEST_CHOOSE_IMAGE = 0x01;

    private static final int REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT = 0xff;
    //广播标识
    public static final String MY_NEW_LIFEFORM="NEW_LIFEFORM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        initView();
        getBundle();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent.getStringExtra("uri") != null) {
            requiredHeight = intent.getIntExtra("width",0);
            requiredWidth = intent.getIntExtra("height",0);
            path = intent.getStringExtra("path");
            Bitmap bm = ImageUtils.decodeSampledBitmapFromDisk(path, requiredWidth, requiredHeight);
            img_touxiang.setImageBitmap(bm);
        }
    }

    private void initView() {
        edit_id = findViewById(R.id.info_id);
        btn_changeImg = findViewById(R.id.change_img);
        btn_checkInfo = findViewById(R.id.btn_checkInfo);
        img_touxiang = findViewById(R.id.info_img);
        imageView = findViewById(R.id.img_infoback);
        photoPath = findViewById(R.id.photo_path);
        bundle = new Bundle();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInfoActivity.this.finish();
            }
        });

        btn_changeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相册
                prepareToOpenAlbum();
            }
        });

        btn_checkInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setId()) {
                    Intent intent = new Intent(MY_NEW_LIFEFORM);
                    intent.putExtras(bundle);
                    if (uri != null) {
                        intent.putExtra("uri", uri.toString());
                        intent.putExtra("path", path);
                        intent.putExtra("width", requiredWidth);
                        intent.putExtra("height", requiredHeight);
                    }
                    sendBroadcast(intent);
                    setResult(1,intent);
                    finish();
                } else {
                    Toast.makeText(PersonInfoActivity.this, "用户ID不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void prepareToOpenAlbum() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_IMAGE);
    }


    private boolean setId() {
        String id = edit_id.getText().toString();

        if (!id.equals("")) {
            bundle.putString("userid", id);
            return true;
        }
        return false;
    }

    private Uri uri;
    String path;
    int requiredWidth = 0;
    int requiredHeight = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode){
//            case 1:
//                if(resultCode == RESULT_OK){
//                    Uri uri = data.getData();
//                    //通过uri的方式返回，部分手机uri可能为空
//                    if(uri != null){
//                        try {
//                            //通过uri获取到bitmap对象
//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                            img_touxiang.setImageBitmap(bitmap);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }else {
//                        //部分手机可能直接存放在bundle中
//                        Bundle bundleExtras = data.getExtras();
//                        if(bundleExtras != null){
//                            Bitmap  bitmaps = bundleExtras.getParcelable("data");
//                            img_touxiang.setImageBitmap(bitmaps);
//                        }
//                    }
//
//                }
//                break;
//        }
        if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == RESULT_OK) {
            uri = data.getData();
            Log.d("Tianma", "Uri = " + uri);
            path = ImageUtils.getRealPathFromUri(this, uri);
            Log.d("Tianma", "realPath = " + path);

            photoPath.setVisibility(View.VISIBLE);
            photoPath.setText(path);
            requiredHeight = imageView.getHeight();
            requiredWidth = imageView.getWidth();
            Bitmap bm = ImageUtils.decodeSampledBitmapFromDisk(path, requiredWidth, requiredHeight);
            img_touxiang.setImageBitmap(bm);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_PERMISSION_GRANT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(getApplicationContext(), "You denied the write_external_storage permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
