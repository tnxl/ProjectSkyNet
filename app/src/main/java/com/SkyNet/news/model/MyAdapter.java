package com.SkyNet.news.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.SkyNet.R;
import com.SkyNet.news.utils.Utils;
import com.SkyNet.news.view.FragmentFactory;


/**
 * @author WJB
 * @date 2018/11/22
 * ViewPager的Adapt
 */
public class MyAdapter extends FragmentPagerAdapter {
    private String[] mTabName;
    private Fragment fragment;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        //加载页面标题数组
        mTabName = Utils.getStringArray(R.array.tab_names);
    }

    /**
     * 返回当前页面Fragment对象
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        fragment = FragmentFactory.createFragment(position);
        return fragment;
    }

    /**
     * 数量
     *
     * @return
     */
    @Override
    public int getCount() {
        return mTabName.length;
    }

    /**
     * 返回标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabName[position];
    }
}
