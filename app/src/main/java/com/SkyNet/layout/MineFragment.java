package com.SkyNet.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.SkyNet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private View view;
    private String list[] = new String[]{"扭一扭","舔一舔","泡一泡"};
    private ListView listView;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_mine,container,false);
        }
        initView();
        return view;
    }

    private void initView(){
        listView = view.findViewById(R.id.mine_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }

}
