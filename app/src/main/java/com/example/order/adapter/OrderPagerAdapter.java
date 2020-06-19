package com.example.order.adapter;


import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.order.Fragment.DealFragment;
import com.example.order.Fragment.FinishFragment;

import java.util.List;

/**
 * Created by 87310 on 2019/1/26.
 */
public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mTilteLis;

    public OrderPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tilteList) {
        super(fm);
        mFragmentList = fragmentList;
        mTilteLis = tilteList;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTilteLis.get(position);
    }


}
