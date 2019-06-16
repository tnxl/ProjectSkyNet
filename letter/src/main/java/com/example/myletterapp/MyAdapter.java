package com.example.myletterapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context ctx;
    private List<MyInfo> list;

    public MyAdapter(Context ctx,List<MyInfo> list){

        this.ctx = ctx;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            holder = new Holder();
            convertView = View.inflate(ctx, R.layout.childview,null);
            holder.topLayout = (LinearLayout) convertView.findViewById(R.id.Top_View);
            holder.bottomLayout = (LinearLayout) convertView.findViewById(R.id.Bottom_View);
            holder.topTextView = (TextView) convertView.findViewById(R.id.Top_Text);
            holder.BottomTextView = (TextView) convertView.findViewById(R.id.Bottom_Text);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        //该条数据是标题
        if(list.get(position).isHeaderFlag()){
            holder.topLayout.setVisibility(View.GONE);
            holder.bottomLayout.setVisibility(View.VISIBLE);
            holder.BottomTextView.setText(list.get(position).getTitle());
        }
        //该条数据是城市名称
        else{
            holder.topLayout.setVisibility(View.VISIBLE);
            holder.bottomLayout.setVisibility(View.GONE);
            holder.topTextView.setText(list.get(position).getHanZi());
            int NextPositon = position + 1;
            //如果该条数据的下一条是标题我们要隐藏topview 显示 bottomview
            if(NextPositon < list.size()){
                //如果该条item在view的最上边。并且该条item的下一条是标题的时候。我们进行隐藏topview并且显示bottomview
                if (list.get(NextPositon).isHeaderFlag() && list.get(position).isTouchFlag()) {
                    holder.topLayout.setVisibility(View.GONE);
                    holder.bottomLayout.setVisibility(View.VISIBLE);
                    holder.BottomTextView.setText(list.get(position).getTitle());
                }
            }
        }
        return convertView;
    }

    static class Holder{
        LinearLayout topLayout;
        LinearLayout bottomLayout;

        TextView topTextView;
        TextView BottomTextView;
    }
}
