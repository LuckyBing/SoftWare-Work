package com.example.order.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.order.R;
import com.example.order.adapter.OrderPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 87310 on 2019/1/12.
 */
public class DingdanFragment extends Fragment {
    private TabLayout tab_check_info;
    private ViewPager vp_check_info;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdanfragment,container,false);



        tab_check_info = (TabLayout) view.findViewById(R.id.tab_check_info);
        vp_check_info = (ViewPager) view.findViewById(R.id.vp_check_info);
        initTitile();
        initFragment();

        OrderPagerAdapter adapter = new OrderPagerAdapter(getChildFragmentManager(),fragmentList,titleList);
        vp_check_info.setAdapter(adapter);
        tab_check_info.setupWithViewPager(vp_check_info);
        return view;
    }
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(CheckInfoFragment.instantiate("待付款"));
        fragmentList.add(CheckInfoFragment.instantiate("已预定"));
        fragmentList.add(CheckInfoFragment.instantiate("处理中"));
        fragmentList.add(CheckInfoFragment.instantiate("已取消"));
        fragmentList.add(CheckInfoFragment.instantiate("已完成"));

    }

    private void initTitile() {
        titleList = new ArrayList<>();
        titleList.add("待付款");
        titleList.add("已预定");
        titleList.add("处理中");
        titleList.add("已取消");
        titleList.add("已完成");
        tab_check_info.setTabMode(TabLayout.MODE_FIXED);
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(0)));
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(1)));
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(2)));
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(3)));
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(4)));
    }


}
