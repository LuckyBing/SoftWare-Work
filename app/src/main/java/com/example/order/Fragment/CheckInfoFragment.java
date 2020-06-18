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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.PingjiaActivity;
import com.example.order.Activity.ProductActivity;
import com.example.order.Common.Product;
import com.example.order.Data.Yuding;
import com.example.order.Http.HttpUtil;
import com.example.order.Http.JsonData;
import com.example.order.R;
import com.example.order.adapter.CheckAdapter;
import com.example.order.adapter.HuiyiAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 87310 on 2019/2/10.
 */
public class CheckInfoFragment extends Fragment {
    private RecyclerView rv_yuding;
    private List<Yuding> yudingList;
    private CheckAdapter adapter1;
    private SharedPreferences pre;
    Handler handler;
    Handler handler1;
    Handler handler3,handler4;
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
        pre= PreferenceManager.getDefaultSharedPreferences(getActivity());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();

        switch (arguments.getString("tag")) {



            case "已完成":
                init();
//                yudingList = new ArrayList<>();
//                Yuding y4 = new Yuding("2019-11-16","豪华单人客房","入住时间：2020/11/13","含早餐",1);
//                yudingList.add(y4);
//                Yuding x4 = new Yuding("2019-11-19","大型会议室","开始时间：2020/05/13","智能会议 刷脸进入",1);
//                yudingList.add(x4);
//                Yuding y5 = new Yuding("2019-09-28","豪华单人客房","入住时间：2020/09/28","含早餐",1);
//                yudingList.add(y5);
//                Yuding y6 = new Yuding("2019-05-17","尊贵单人客房","入住时间：2020/05/17","含早餐",1);
//                yudingList.add(y6);
//                Yuding x3 = new Yuding("2019-05-19","大型会议室","开始时间：2020/05/17","智能会议 刷脸进入",1);
//                yudingList.add(x3);
//                Yuding x6 = new Yuding("2019-03-15","豪华套房","入住时间：2020/03/14","含早餐",1);
//                yudingList.add(x6);
//                Yuding z6 = new Yuding("2019-01-11","行政双人客房","入住时间：2020/01/11","含早餐",1);
//                yudingList.add(z6);
                handler = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                        switch (msg.what) {
                            case 0:
                                adapter1 = new CheckAdapter(yudingList);
                                Log.d("0",yudingList.get(0).getMrname());
                                adapter1.setOnItemClickListener(new CheckAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemOrderClick(final int pos, final List<Yuding> roomLists) {
                                        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("确认删除?")
                                                .setCancelText("取消")
                                                .setConfirmText("删除")
                                                .showCancelButton(true)
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(final SweetAlertDialog sDialog) {

                                                        String url="https://www.luckyc.top/Order/changeorderstate?order_id="+roomLists.get(pos).getOrdertime()+"&state=2";
                                                        Log.d("dsf",url);
                                                        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
                                                            @Override
                                                            public void onFailure(Call call, IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(Call call, Response response) throws IOException {
                                                                String responseText = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject = new JSONObject(responseText);
                                                                    String info=jsonObject.getString("info");
                                                                    if(info.equals("成功"))
                                                                    {
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
                                    public void onItemcardClick(int pos, List<Yuding> roomLists) {
                                        Log.d("click:","clickadpter1Ocard");
                                    }
                                });
                                rv_yuding.setAdapter(adapter1);
                                break;
                            case 2:
                                init();
                        }
                    }
                };
                break;

            case "处理中":
                init1();
                handler1 = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                        switch (msg.what) {
                            case 0:
                                //Log.d("0",yudingList.get(0).getMrname());
                                adapter1 = new CheckAdapter(yudingList);
                                adapter1.setOnItemClickListener(new CheckAdapter.OnItemClickListener() {
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

                                                        String url="https://www.luckyc.top/Order/changeorderstate?order_id="+roomLists.get(pos).getOrdertime()+"&state=1";
                                                        Log.d("dsf",url);
                                                        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
                                                            @Override
                                                            public void onFailure(Call call, IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(Call call, Response response) throws IOException {
                                                                String responseText = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject = new JSONObject(responseText);
                                                                    String info=jsonObject.getString("info");
                                                                    if(info.equals("成功"))
                                                                    {
                                                                        handler1.sendEmptyMessage(2);
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
                                        Intent intent=new Intent(getActivity(), ProductActivity.class);
                                        intent.putExtra("info","加菜");
                                        intent.putExtra("tableId",roomLists.get(pos).getMrname());
                                        startActivity(intent);

                                    }
                                });
                                rv_yuding.setAdapter(adapter1);
                                break;
                            case 2:
                                init1();
                        }


                    }
                };
                break;



        }
    }
    private  void init()
    {
        String url="https://www.luckyc.top/Order/FindallOrderbycus_id?id="+pre.getString("account","")+"&state=1";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                yudingList= JsonData.handleYResponse(responseText,1);
                handler.sendEmptyMessage(0);

            }
        });
    }
    private void init1()
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
                handler1.sendEmptyMessage(0);

            }
        });
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
