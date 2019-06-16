package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 科技新闻页面Fragment
 */
public class TechnologyFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_technology));
    }
}
