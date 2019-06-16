package com.example.myletterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ZiMuActivity extends AppCompatActivity {

    private List<MyInfo> list = new ArrayList<MyInfo>();
    private MyAdapter myAdapter;
    private LinearLayout MainTitle;
    private TextView mainTxtTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimu);

        MyZhuan();
        MyLetterView myLetterView = (MyLetterView) findViewById(R.id.myZiMuLetterView1);
        final ListView listView = (ListView) findViewById(R.id.myListView);
        myAdapter = new MyAdapter(ZiMuActivity.this, list);
        listView.setAdapter(myAdapter);
        MainTitle = (LinearLayout) findViewById(R.id.MainTitle);
        mainTxtTextView = (TextView) findViewById(R.id.MainTitleTxt);
        if(list.size() > 0)
            mainTxtTextView.setText(list.get(0).getTitle());
        listView.setOnScrollListener(new AbsListView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                mainTxtTextView.setText(list.get(firstVisibleItem).getTitle());
                int NextItem = firstVisibleItem + 1;
                if(NextItem < list.size()){
                    //如果这条顶部数据的下一条是标题的时候隐藏MainView
                    if(list.get(NextItem).isHeaderFlag()){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setTouchFlag(false);
                        }
                        list.get(firstVisibleItem).setTouchFlag(true);
                        myAdapter.notifyDataSetChanged();
                        MainTitle.setVisibility(View.GONE);
                    }
                    //如果该条数据的下一条不是标题的时候我们显示MainView
                    if(!list.get(NextItem).isHeaderFlag()){
                        MainTitle.setVisibility(View.VISIBLE);
                    }

                    //如果当前条数为标题的情况下，我们的其他的条数值为false
                    if(!list.get(firstVisibleItem + 1).isHeaderFlag()){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setTouchFlag(false);
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        myLetterView.setOnTouchingLetterChangedListener(new MyLetterView.OnTouchingLetterChangedListener(){
            @Override
            public void onTouchingLetterChanged(String s) {
                int TouchId = 0;
                String myItem = ""+toLower(s.charAt(0));
                for(int i = 0;i< list.size();i++){

                    if(list.get(i).getHanZi().equals(myItem)){
                        Log.e("",""+myItem);
                        TouchId = i;
                    }
                }
                //设定顶部item
                listView.setSelection(TouchId);
            }
        });
    }

    public static char toLower(char ch)
    {
        if(ch <= 'Z' && ch >= 'A')
        {
            return (char)(ch-'A'+'a');
        }
        return ch;
    }

    private void MyZhuan(){

        //首字母拼音存入的
        List<String> mumList = new ArrayList<String>();
        //定义一个set来去掉拼音的重复的。
        HashSet<String> mySet = new HashSet<String>();
        //转换成拼音去掉重复
        for (int i = 0; i < MyHanzi.MyChina.length; i++) {
            //获取第一个城市名称的拼音
            String str = getPinYin(MyHanzi.MyChina[i].substring(0,1));
            //首字母的拼音
            String firString = String.valueOf(str.charAt(0));
            mySet.add(firString);
        }
        //set数据添加给我们的list
        mumList.addAll(mySet);
        //排序操作
        Collections.sort(mumList);

        //比对数据比较数据

        for (int i = 0; i < mumList.size(); i++) {
            //title  具体的abcd
            String mumTitle = mumList.get(i);
            //添加标题相关数据
            MyInfo info = new MyInfo();
            info.setTitle(mumTitle);
            info.setHeaderFlag(true);
            info.setTouchFlag(false);
            info.setHanZi(mumTitle);
            info.setId("");
            list.add(info);
            //城市名称
            for (int j = 0; j < MyHanzi.MyChina.length; j++) {

                String str = getPinYin(MyHanzi.MyChina[j].substring(0,1));
                //首字母的拼音
                String firString = str.substring(0,1);
                //如果相同
                if(mumTitle.equals(firString)){

                    MyInfo valueInfo = new MyInfo();
                    valueInfo.setHanZi(MyHanzi.MyChina[j]);
                    valueInfo.setHeaderFlag(false);
                    info.setTouchFlag(false);
                    valueInfo.setTitle(mumTitle);
                    valueInfo.setId("");
                    list.add(valueInfo);
                }

            }
        }
    }

    public static String getPinYin(String input) {

        ArrayList<Convert.Token> tokens = Convert.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (Convert.Token token : tokens) {
                if (Convert.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
