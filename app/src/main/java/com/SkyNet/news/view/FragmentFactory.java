package com.SkyNet.news.view;

import android.annotation.SuppressLint;

import com.SkyNet.news.view.fragment.BaseFragment;
import com.SkyNet.news.view.fragment.EntertainmentFragment;
import com.SkyNet.news.view.fragment.FashionFragment;
import com.SkyNet.news.view.fragment.FinanceFragment;
import com.SkyNet.news.view.fragment.MilitaryFragment;
import com.SkyNet.news.view.fragment.SocietyFragment;
import com.SkyNet.news.view.fragment.SportsFragment;
import com.SkyNet.news.view.fragment.TechnologyFragment;
import com.SkyNet.news.view.fragment.TopFragment;

import java.util.HashMap;


/**
 *
 * 工程模式 ，生产各个新闻
 * 这个类真正的创建每个页面
 */
public class FragmentFactory {
    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, BaseFragment> mFragmentMap=new HashMap<Integer, BaseFragment>();
    public static BaseFragment createFragment(int pos){

        BaseFragment fragment=mFragmentMap.get(pos);
        if(fragment==null){
            switch (pos){
                case 0:
                    //头条界面
                    fragment=new TopFragment();
                    break;
                case 1:
                    //社会界面
                    fragment=new SocietyFragment();
                    break;
                case 2:
                    //娱乐界面
                    fragment=new EntertainmentFragment();
                    break;
                case 3:
                    //体育界面
                    fragment=new SportsFragment();
                    break;
                case 4:
                    //财经界面
                    fragment=new FinanceFragment();
                    break;
                case 5:
                    //科技界面
                    fragment=new TechnologyFragment();
                    break;
                case 6:
                    //军事界面
                    fragment=new MilitaryFragment();
                    break;
                case 7:
                    //时尚界面
                    fragment=new FashionFragment();
                    break;
                default:
                    break;
            }
            //将fragment保存到集合中
            mFragmentMap.put(pos,fragment);
        }
        return fragment;
    }
}
