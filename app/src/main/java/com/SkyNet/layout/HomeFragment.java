package com.SkyNet.layout;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.SkyNet.R;
import com.SkyNet.main.MainActivity;
import com.SkyNet.main.PlayVideoActivity;
import com.SkyNet.util.LooperPagerAdapter;
import com.SkyNet.util.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MyViewPager.OnViewPagerTouchListener {

    private ViewPager viewPager;
    private List<Integer> pics;
    private View view;

    private MyViewPager loopPager;
    private LooperPagerAdapter looperPagerAdapter;

    private Handler handler;
    private boolean isTouch = false;

//    boolean flag= false;
//    Thread th;
//    private GestureDetector.SimpleOnGestureListener simpleOnGestureListener;
//    private GestureDetector gestureDetector;

    public HomeFragment() {
        // Required empty public constructor
    }

//    final private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            if (msg.what == 1){
//                int curr = loopPager.getCurrentItem();
//                loopPager.setCurrentItem(++curr,true);
//            }
//        }
//    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        initView();

//        initGD();

        handler.post(loopTask);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(loopTask);
    }

    //    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        //当我界面绑定到窗口的时候
//        handler.post(loopTask);
//    }
//
//    @Override
//    public void onDetachedFromWindow() {
//        super.onDetachedFromWindow();
//        handler.removeCallbacks(loopTask);
//    }

    private Runnable loopTask = new Runnable() {
        @Override
        public void run() {
            if (!isTouch){
                //切换viewpager里的图片到下一个
                int curr = loopPager.getCurrentItem();
                loopPager.setCurrentItem(++curr,true);
            }
            handler.postDelayed(this,3000);

        }
    };


    private void initView() {
        handler = new Handler();

        loopPager = (MyViewPager)view.findViewById(R.id.lunbo);

        pics = new ArrayList<Integer>();
        pics.add(R.drawable.icon_1);
        pics.add(R.drawable.icon_2);
        pics.add(R.drawable.icon_3);
        pics.add(R.drawable.icon_4);
        pics.add(R.drawable.icon_5);

        //设置适配器
        looperPagerAdapter = new LooperPagerAdapter();
        looperPagerAdapter.setData(pics);
        loopPager.setAdapter(looperPagerAdapter);
        loopPager.setCurrentItem(looperPagerAdapter.getDataRealSize()*1000,false);
        loopPager.setOnViewPagerTouchListener(this);

    }


    @Override
    public void onPagerTouch(boolean isTouch) {
        this.isTouch = isTouch;
    }





//    @Override
//    public void run() {
//
//        while (flag){
//            try{
//                Message msg = new Message();
//                msg.what = 1;
//                handler.sendEmptyMessage(1);
//                Thread.sleep(2500);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }

//    private void initGD(){
//        simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//
//                System.out.println(velocityX);
//                try {
//                    if (e1.getX() - e2.getX() < -89) {
//                        int curr = loopPager.getCurrentItem();
//                        loopPager.setCurrentItem(--curr,true);
//                        return true;
//                    } else if (e1.getX() - e2.getX() > 89) {
//                        int curr = loopPager.getCurrentItem();
//                        loopPager.setCurrentItem(++curr,true);
//                        return true;
//                    }
//                } catch (Exception e) {
//                }
//                return false;
//            }
//        };
//
//        gestureDetector = new GestureDetector(simpleOnGestureListener);
//
//        SensorActivity.MyOnTouchListener myOnTouchListener = new SensorActivity.MyOnTouchListener() {
//            @Override
//            public boolean onTouch(MotionEvent ev) {
//                gestureDetector.onTouchEvent(ev);
//                return false;
//            }
//        };
//
//        ((SensorActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener);
//    }

}

