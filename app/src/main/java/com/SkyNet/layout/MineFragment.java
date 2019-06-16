package com.SkyNet.layout;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.SkyNet.R;
import com.SkyNet.util.ImageUtils;
import com.SkyNet.view.PersonInfoActivity;
import com.SkyNet.weather.WeatherActivity;
import com.bumptech.glide.load.engine.Resource;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private View view;
    private String list[] = new String[]{"查一查", "舔一舔", "泡一泡"};
    private ListView listView;
    private Button btn_edit;
    private TextView mine_id;
    private ImageView img_touxiang;
    Bundle bundle;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        }
        initView();
        return view;
    }

    private void initView() {
        listView = view.findViewById(R.id.mine_list);
        btn_edit = view.findViewById(R.id.mine_edit);
        mine_id = view.findViewById(R.id.mine_id);
        img_touxiang = view.findViewById(R.id.mine_touxiang);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                if (uri != null) {
                    intent.putExtra("uri", uri.toString());
                    intent.putExtra("path", path);
                    intent.putExtra("width", requiredWidth);
                    intent.putExtra("height", requiredHeight);
                }
                startActivity(intent);
//                startActivityForResult(intent, 1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getContext(), WeatherActivity.class);
                        startActivity(intent);
                }
            }
        });
    }

//    private void getInfo() {
//        bundle = getArguments();
//        if (bundle != null) {
//            String id = bundle.getString("userid");
//            if (id != null)
//                id = "ID: "+id;
//                mine_id.setText(id);
//        }
//    }

    private Uri uri;
    String path;
    int requiredWidth = 0;
    int requiredHeight = 0;

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (resultCode) {
//            case 1:
//                System.out.println("MineFragment");
//                Toast.makeText(getActivity(), "MineFragment", Toast.LENGTH_SHORT).show();
//                bundle = data.getExtras();
//                String id = bundle.getString("userid");
//                id = "ID: " + id;
//                mine_id.setText(id);
//                if (data.getStringExtra("uri") != null) {
//                    uri = Uri.parse(data.getStringExtra("uri"));
//                    path = data.getStringExtra("path");
//                    requiredWidth = data.getIntExtra("width", 0);
//                    requiredHeight = data.getIntExtra("height", 0);
//                    Bitmap bm = ImageUtils.decodeSampledBitmapFromDisk(path, requiredWidth, requiredHeight);
//                    img_touxiang.setImageBitmap(bm);
//                }
//                break;
//        }
//    }

    MineBroadcast myBroadcast;
    IntentFilter intentFilter;

    class MineBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            bundle = intent.getExtras();
            String id = bundle.getString("userid");
            id = "ID: " + id;
            mine_id.setText(id);
            if (intent.getStringExtra("uri") != null) {
                uri = Uri.parse(intent.getStringExtra("uri"));
                path = intent.getStringExtra("path");
                requiredWidth = intent.getIntExtra("width", 0);
                requiredHeight = intent.getIntExtra("height", 0);
                Bitmap bm = ImageUtils.decodeSampledBitmapFromDisk(path, requiredWidth, requiredHeight);
                img_touxiang.setImageBitmap(bm);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myBroadcast = new MineBroadcast();
        intentFilter = new IntentFilter();
        intentFilter.addAction("NEW_LIFEFORM");
        context.getApplicationContext().registerReceiver(myBroadcast,intentFilter);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myBroadcast);


//        broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
    }

//    LocalBroadcastManager broadcastManager;
//
//    /**
//     * 注册广播接收器
//     */
//    private void receiveAdDownload() {
//        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("NEW_LIFEFORM");
//        broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
//    }
//
//    BroadcastReceiver mAdDownLoadReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.i("BroadCast","MineFragment");
//            bundle = intent.getExtras();
//            String id = bundle.getString("userid");
//            id = "ID: " + id;
//            mine_id.setText(id);
//            if (intent.getStringExtra("uri") != null) {
//                uri = Uri.parse(intent.getStringExtra("uri"));
//                path = intent.getStringExtra("path");
//                requiredWidth = intent.getIntExtra("width", 0);
//                requiredHeight = intent.getIntExtra("height", 0);
//                Bitmap bm = ImageUtils.decodeSampledBitmapFromDisk(path, requiredWidth, requiredHeight);
//                img_touxiang.setImageBitmap(bm);
//            }
//        }
//    };

}
