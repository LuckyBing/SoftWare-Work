package com.example.order.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.order.R;
import com.example.order.adapter.OrderPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 87310 on 2019/1/12.
 */
public class DingdanFragment extends Fragment{
    private TabLayout tab_check_info;

    private List<String> titleList;
    private Fragment mfragment;
    private DealFragment dealFragment;
    private FinishFragment finishFragment;
    private FragmentTransaction transac;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dingdanfragment, container, false);


        tab_check_info = (TabLayout) view.findViewById(R.id.tab_check_info);

//        vp_check_info = (ViewPager) view.findViewById(R.id.vp_check_info);
//        vp_check_info.setOffscreenPageLimit(1);
        initTitile();
        initFragment();
        tab_check_info.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        Log.d("选中","h");
                        switchFragment(new DealFragment());
                        break;
                    case 1:
                        switchFragment(new FinishFragment());
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //init();
        return view;
    }
//    private void initFragment() {
//        fragmentList = new ArrayList<>();
//        fragmentList.add(DealFragment.instantiate("处理中"));
//        fragmentList.add(FinishFragment.instantiate("已完成"));
//
//
//    }
    private void initFragment() {
        dealFragment = new DealFragment();
        finishFragment=new FinishFragment();
        transac= getChildFragmentManager().beginTransaction();
        transac.add(R.id.frame,dealFragment)
                .commit();
        mfragment = dealFragment;
    }

    private void initTitile() {
        titleList = new ArrayList<>();
        titleList.add("处理中");
        titleList.add("已完成");
        tab_check_info.setTabMode(TabLayout.MODE_FIXED);
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(0)));
        tab_check_info.addTab(tab_check_info.newTab().setText(titleList.get(1)));
    }
//    private void init()
//    {
//        adapter = new OrderPagerAdapter(getChildFragmentManager(),fragmentList,titleList);
//        vp_check_info.setAdapter(adapter);
//        tab_check_info.setupWithViewPager(vp_check_info);
//    }



    private void switchFragment(Fragment fragment) {
//        //判断当前显示的Fragment是不是切换的Fragment
//        if(mfragment != fragment) {
//            //判断切换的Fragment是否已经添加过
//            if (!fragment.isAdded()) {
//                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getChildFragmentManager().beginTransaction().detach(mfragment)
                        .add(R.id.frame,fragment).commit();
//            } else {
//                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
//                getChildFragmentManager().beginTransaction().hide(mfragment).show(fragment).commit();
//            }
            mfragment = fragment;
//        }
    }
}
