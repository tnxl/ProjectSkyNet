package com.SkyNet.news.view.fragment;


import com.SkyNet.R;

/**
 *
 * 社会新闻页面Fragment
 */
public class SocietyFragment extends BaseFragment {
    @Override
    public void onGetNewType() {
        getDataFromServer(getString(R.string.type_society));
    }
}
