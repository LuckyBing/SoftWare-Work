package com.example.order.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.TableActivity;
import com.example.order.Http.HttpUtil;
import com.example.order.R;
import com.example.order.Tool.BottomDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 87310 on 2019/1/12.
 */
public class HomepageFragment extends Fragment {
    private MZBannerView mMZBanner;
    private List list = new ArrayList();
    private RefreshLayout refreshLayout;
    TextView ad;
    String cont;
    //private Button book;
    private Button online;
    Handler handler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagefragment,container,false);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        //book=view.findViewById(R.id.book);
        online=view.findViewById(R.id.online);
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        ad=view.findViewById(R.id.ad);
        initBanner();
        refreshLayout = (RefreshLayout) view.findViewById(R.id.smart);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getContext(),"frsh",Toast.LENGTH_LONG).show();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
//        book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TableActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initBanner()
    {
        list.add(R.mipmap.cting1);
        list.add(R.mipmap.cting2);
        list.add(R.mipmap.canting3);
        list.add(R.mipmap.cting4);
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.setIndicatorAlign(MZBannerView.IndicatorAlign.CENTER);
        mMZBanner.setIndicatorPadding(5,0,5,1);
        String url="https://www.luckyc.top/Order/getaffiche";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                try {
                    JSONObject parse = new JSONObject(responseText);
                    cont = parse.getString("info");
                    handler.sendEmptyMessage(0);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        ad.setText(cont);
                        break;
                }
            }
        };
    }


    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();//开始轮播
    }




}
