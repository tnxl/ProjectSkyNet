package com.SkyNet.layout;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.SkyNet.R;

import java.util.List;

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
    private String list3[] = new String[]{"飘一飘","买一买","玩一玩"};
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
    }

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
