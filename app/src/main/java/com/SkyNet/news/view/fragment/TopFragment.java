package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 头条新闻页面Fragment
 */
public  class TopFragment extends BaseFragment {

    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_top));
    }
}
