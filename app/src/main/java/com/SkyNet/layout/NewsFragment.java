package com.SkyNet.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.SkyNet.R;
import com.SkyNet.news.model.MyAdapter;
import com.SkyNet.news.view.FragmentFactory;
import com.SkyNet.news.view.fragment.BaseFragment;
import com.SkyNet.news.view.fragment.TopFragment;
import com.astuetz.PagerSlidingTabStrip;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    /**
     * 顶部导航栏
     */
    private PagerSlidingTabStrip mPagerTab;
    /**
     * 导航栏对应的新闻分类列表页面
     */
    private ViewPager mViewPager;
    /**
     * 新闻Adapter
     */
    private MyAdapter mAdapter;
    /**
     * fragment
     */
    public BaseFragment fragment;

    private View view;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }

        fragment = new TopFragment();

        //拿到对应的控件
        mPagerTab = view.findViewById(R.id.ps_tab);
        mViewPager = view.findViewById(R.id.vp_news);

        mAdapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        // 导航栏绑定ViewPager
        mPagerTab.setViewPager(mViewPager);
        // 对用户滑动界面的监听。根据滑动到的页面，去读取改页面对应的信息
        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //监听到页面滑动了，就开始生产对应页面 ，比如滑动到了健康页面，就会加载健康类型的新闻信息，然后显示
                fragment = FragmentFactory.createFragment(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }


}
