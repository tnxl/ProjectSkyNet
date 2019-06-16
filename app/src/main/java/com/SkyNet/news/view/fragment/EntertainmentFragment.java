package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 娱乐新闻页面Fragment
 */

public class EntertainmentFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_entertainment));
    }
}
