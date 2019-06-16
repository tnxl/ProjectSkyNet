package com.SkyNet.layout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.SkyNet.R;
import com.example.administrator.recorddemo.RecorderActivity;
import com.example.ice.coursetable.CameraActivity;
import com.example.myletterapp.ZiMuActivity;
import com.example.mysensor.SensorActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
    private View view;
    private String list1[] = new String[]{"朋友圈"};
    private String list2[] = new String[]{"扫一扫","摇一摇"};
    private String list3[] = new String[]{"附近的人","语音备忘录","玩一玩"};
    private String list4[] = new String[]{"扭一扭","舔一舔","泡一泡"};

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_find,container,false);
        initView();
        return view;
    }

    private void initView(){
        listView1 = (ListView)view.findViewById(R.id.find_listView1);
        listView2 = (ListView)view.findViewById(R.id.find_listView2);
        listView3 = (ListView)view.findViewById(R.id.find_listView3);
        listView4 = (ListView)view.findViewById(R.id.find_listView4);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list1);
        listView1.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list2);
        listView2.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list3);
        listView3.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list4);
        listView4.setAdapter(adapter4);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //朋友圈
                Intent intent = new Intent(getContext(), ZiMuActivity.class);
                startActivity(intent);
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //扫一扫
                        handIntent = new Intent(getActivity(), CameraActivity.class);
                        handIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().getApplicationContext().startActivity(handIntent);
                        break;
                    case 1:
                        //摇一摇
                        handIntent = new Intent(getActivity(),SensorActivity.class);
                        handIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().getApplicationContext().startActivity(handIntent);
                        break;
                }
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //附近的人
                        Intent intent = new Intent(getContext(), baidumapsdk.demo.BMapApiDemoMain.class);
                        startActivity(intent);
                        break;
                    case 1:
                        //语音备忘录
                        Intent intent1 = new Intent(getContext(), RecorderActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }
    Message msg = new Message();
    Intent handIntent;
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    break;
                case 1:
                    break;
            }
        }
    };

//    class ListViewAdapter extends android.widget.BaseAdapter {
//        Context context;
//        List<String> datas;
//
//        public ListViewAdapter(Context _context,
//                               List<String> relativesList) {
//            this.datas = relativesList;
//            this.context = _context;
//        }
//
//        @Override
//        public int getCount() {
//            return datas.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//        public final class ViewHolder {
//            public TextView name;//昵称
//        }
//        @SuppressLint("InflateParams")
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if(convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(context).inflate(R.layout.fragment_find, null);
//                holder.name = (TextView) convertView.findViewById(R.id.name);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.name.setText(datas.get(position));
//            return convertView;
//        }
//
//
//    }

}
