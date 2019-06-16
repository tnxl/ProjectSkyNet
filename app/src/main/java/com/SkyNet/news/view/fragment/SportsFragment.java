package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 体育新闻页面Fragment
 */
public class SportsFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_sports));
    }
}
