package com.SkyNet.util;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class LooperPagerAdapter extends PagerAdapter {

    private List<Integer> pics = null;

    @Override
    public int getCount() {
        if (pics != null) {
            //设置最大数达到伪无限轮播
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    //设置图片数据
    public void setData(List<Integer> colors) {
        this.pics = colors;
    }
    //获得当前轮播坐标
    public int getDataRealSize(){
        if (pics!=null){
            return pics.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position % pics.size();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setImageResource(pics.get(realPosition));
        //设置完数据后，就添加到容器里
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
