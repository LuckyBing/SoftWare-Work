package com.example.order.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.ProductActivity;
import com.example.order.Data.Yuding;
import com.example.order.Http.HttpUtil;
import com.example.order.Http.JsonData;
import com.example.order.R;
import com.example.order.adapter.CheckAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DealFragment extends Fragment {
    private RecyclerView rv_yuding;
    private List<Yuding> yudingList;
    private CheckAdapter adapter;
    private SharedPreferences pre;
    Handler handler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_info_fragment, null);
        rv_yuding = (RecyclerView) view.findViewById(R.id.rv_yuding);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_yuding.setLayoutManager(manager);
        yudingList=new ArrayList<Yuding>();
        pre= PreferenceManager.getDefaultSharedPreferences(getActivity());
        init();
        return view;
    }
    private void init()
    {
        String url="https://www.luckyc.top/Order/FindallOrderbycus_id?id="+pre.getString("account","")+"&state=0";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                yudingList= JsonData.handleYResponse(responseText,0);
//                if(yudingList.size()!=0)
                handler.sendEmptyMessage(0);

            }
        });
        pay();
    }

    private void pay()
    {
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        //(Log.d("0",yudingList.get(0).getMrname());


                        adapter = new CheckAdapter(yudingList);
                        adapter.setOnItemClickListener(new CheckAdapter.OnItemClickListener() {
                            @Override
                            public void onItemOrderClick(final int pos, final List<Yuding> roomLists) {
                                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("确认付款?")
                                        .setCancelText("取消")
                                        .setConfirmText("付款")
                                        .showCancelButton(true)
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(final SweetAlertDialog sDialog) {

                                                String url = "https://www.luckyc.top/Order/changeorderstate?order_id=" + roomLists.get(pos).getOrdertime() + "&state=1";
                                                Log.d("dsf", url);
                                                HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
                                                    @Override
                                                    public void onFailure(Call call, IOException e) {

                                                    }

                                                    @Override
                                                    public void onResponse(Call call, Response response) throws IOException {
                                                        String responseText = response.body().string();
                                                        try {
                                                            JSONObject jsonObject = new JSONObject(responseText);
                                                            String info = jsonObject.getString("info");
                                                            if (info.equals("1")) {
                                                                handler.sendEmptyMessage(2);
                                                                sDialog.dismissWithAnimation();
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                            }
                                        })
                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.cancel();
                                            }
                                        })
                                        .show();
                            }

                            @Override
                            public void onItemcardClick(final int pos, final List<Yuding> roomLists) {
                                Intent intent = new Intent(getActivity(), ProductActivity.class);
                                intent.putExtra("info", "加菜");
                                intent.putExtra("tableId", roomLists.get(pos).getMrname());
                                startActivity(intent);

                            }
                        });
                        rv_yuding.setAdapter(adapter);

                        break;
                    case 2:
                        init();
                }


            }
        };
    }


    @Override
    public void onResume() {

        super.onResume();
        init();
    }


    public static DealFragment instantiate(String tag) {
        DealFragment fragment = new DealFragment();
        return fragment;
    }
}
