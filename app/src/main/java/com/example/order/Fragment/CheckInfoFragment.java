package com.example.order.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.PingjiaActivity;
import com.example.order.Data.Yuding;
import com.example.order.R;
import com.example.order.adapter.CheckAdapter;
import com.example.order.adapter.HuiyiAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 87310 on 2019/2/10.
 */
public class CheckInfoFragment extends Fragment {
    private RecyclerView rv_yuding;
    private List<Yuding> yudingList;
    private CheckAdapter adapter1;

    public static CheckInfoFragment instantiate(String tag) {
        CheckInfoFragment fragment = new CheckInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tag",tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.check_info_fragment,null);
        rv_yuding = (RecyclerView) view.findViewById(R.id.rv_yuding);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_yuding.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        switch (arguments.getString("tag")) {
            case "待付款":
                Log.d("fsddsf",arguments.getString("tag"));
                yudingList = new ArrayList<>();
                Yuding x1 = new Yuding("2020-05-29","豪华特大床房","入住时间：2020/06/21","含早餐",0);
                yudingList.add(x1);
                Yuding x2 = new Yuding("2020-04-16","豪华单人客房","入住时间：2020/04/20","无特殊优惠",0);
                yudingList.add(x2);

                CheckAdapter adapterx = new CheckAdapter(yudingList);
                rv_yuding.setAdapter(adapterx);
                break;

            case "已预定":
                Log.d("fsddsf",arguments.getString("tag"));
                yudingList = new ArrayList<>();
                Yuding y1 = new Yuding("2020-04-16","豪华套房","入住时间：2020/04/20","含早餐",1);
                yudingList.add(y1);
                Yuding y2 = new Yuding("2019-04-16","豪华单人客房","入住时间：2020/04/20","含早餐",1);
                yudingList.add(y2);
                Yuding y3 = new Yuding("2019-04-16","豪华双人客房","入住时间：2020/04/20","含早餐",1);
                yudingList.add(y3);
                Yuding x7 = new Yuding("2019-04-16","小型会议室","开始时间：2020/04/21","当前正在会议室使用期间",4);
                yudingList.add(x7);

                CheckAdapter adapter = new CheckAdapter(yudingList);
                rv_yuding.setAdapter(adapter);
                break;
            case "已完成":
                yudingList = new ArrayList<>();
                Yuding y4 = new Yuding("2019-11-16","豪华单人客房","入住时间：2020/11/13","含早餐",2);
                yudingList.add(y4);
                Yuding x4 = new Yuding("2019-11-19","大型会议室","开始时间：2020/05/13","智能会议 刷脸进入",4);
                yudingList.add(x4);
                Yuding y5 = new Yuding("2019-09-28","豪华单人客房","入住时间：2020/09/28","含早餐",2);
                yudingList.add(y5);
                Yuding y6 = new Yuding("2019-05-17","尊贵单人客房","入住时间：2020/05/17","含早餐",2);
                yudingList.add(y6);
                Yuding x3 = new Yuding("2019-05-19","大型会议室","开始时间：2020/05/17","智能会议 刷脸进入",4);
                yudingList.add(x3);
                Yuding x6 = new Yuding("2019-03-15","豪华套房","入住时间：2020/03/14","含早餐",2);
                yudingList.add(x6);
                Yuding z6 = new Yuding("2019-01-11","行政双人客房","入住时间：2020/01/11","含早餐",2);
                yudingList.add(z6);

                adapter1 = new CheckAdapter(yudingList);
                adapter1.setOnItemClickListener(new HuiyiAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Log.d("s","ds");
                        Intent intent = new Intent(getContext(), PingjiaActivity.class);
                        intent.putExtra("name",yudingList.get(position).getMrname());
                        startActivityForResult(intent,1);
                    }
                });
                rv_yuding.setAdapter(adapter1);
                break;
            case "已取消":
                yudingList = new ArrayList<>();
                    Yuding y7 = new Yuding("2019-07-16","豪华双人客房","入住时间：2020/07/16","含早餐",3);
                    yudingList.add(y7);
                    Yuding y8 = new Yuding("2019-05-17","尊贵单人客房","入住时间：2020/05/18","含早餐",3);
                    yudingList.add(y8);
                    Yuding x9 = new Yuding("2019-05-17","大型会议室","开始时间：2020/05/19","智能会议 刷脸进入",4);
                    yudingList.add(x9);
                    Yuding y9 = new Yuding("2019-03-14","豪华双人客房","入住时间：2020/03/20","含早餐",3);
                    yudingList.add(y9);
                    Yuding y10 = new Yuding("2019-01-28","尊贵单人客房","入住时间：2020/01/28","含早餐",3);
                    yudingList.add(y10);
                CheckAdapter adapter2 = new CheckAdapter(yudingList);
                rv_yuding.setAdapter(adapter2);
                break;
            case "处理中":
                yudingList = new ArrayList<>();
                Yuding x17 = new Yuding("2020-05-16","豪华双人客房","入住时间：2020/05/20","含早餐",4);
                yudingList.add(x17);
                Yuding x8 = new Yuding("2019-05-17","小型会议室","开始时间：2020/05/20","当前正在会议室使用期间",4);
                yudingList.add(x8);
                CheckAdapter adapterx2 = new CheckAdapter(yudingList);
                rv_yuding.setAdapter(adapterx2);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode==1){
                adapter1.pingjia(0);
            }
        }
    }
}
