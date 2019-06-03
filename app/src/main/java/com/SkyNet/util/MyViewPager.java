package com.SkyNet.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {


    private OnViewPagerTouchListener touchListener;

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (touchListener!=null){
                    touchListener.onPagerTouch(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (touchListener!=null){
                    touchListener.onPagerTouch(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnViewPagerTouchListener(OnViewPagerTouchListener listener){
        this.touchListener = listener;
    }

    public interface OnViewPagerTouchListener{
        void onPagerTouch(boolean isTouch);
    }
}
