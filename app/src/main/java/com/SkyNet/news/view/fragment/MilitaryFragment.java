package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 军事新闻页面Fragment
 */
public class MilitaryFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_military));
    }
}
