package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 财经新闻页面Fragment
 */
public class FinanceFragment extends BaseFragment {

    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_sports));
    }
}
