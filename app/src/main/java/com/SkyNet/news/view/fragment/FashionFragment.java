package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 时尚新闻页面Fragment
 */
public class FashionFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_fashion));
    }
}
