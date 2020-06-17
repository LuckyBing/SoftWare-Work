package com.example.order.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.order.Data.Huiyi;
import com.example.order.R;

import java.util.List;

/**
 * Created by hugeterry(http://hugeterry.cn)
 */

public class HuiyiAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<Huiyi> mDatas;

    public static final int TYPE_FAQI= 1;
    public static final int TYPE_YAOQING = 2;


    public HuiyiAdapter(List<Huiyi> mDatas, Context context) {
        this.mDatas = mDatas;
        this.mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view;
        RecyclerView.ViewHolder holder = null;
        if (viewType==TYPE_FAQI){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_faqi, parent, false);
            holder = new FaqingHolder(view);
        }else if (viewType==TYPE_YAOQING){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_yaoqing, parent, false);
            holder = new YaoqingHolder(view);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        Huiyi huiyi = mDatas.get(position);
        if (type==TYPE_FAQI){
            FaqingHolder faqiHolder = (FaqingHolder) holder;
            faqiHolder.tv_name.setText(huiyi.getName());
            faqiHolder.tv_time.setText(huiyi.getTime());
            if (huiyi.getState()==1){
                faqiHolder.typecolor.setBackgroundResource(R.color.blue);
                faqiHolder.btn_next.setBackgroundResource(R.mipmap.banyuan);
                faqiHolder.btn_next.setTextColor(mContext.getResources().getColor(R.color.blue));
                faqiHolder.tv_state.setText("进行中");
                faqiHolder.btn_next.setText("会中控制");
            }else if(huiyi.getState()==2){
                faqiHolder.typecolor.setBackgroundResource(R.color.blue);
                faqiHolder.btn_next.setBackgroundResource(R.mipmap.banyuan);
                faqiHolder.btn_next.setTextColor(mContext.getResources().getColor(R.color.blue));
                faqiHolder.tv_state.setText("未开始");
                faqiHolder.btn_next.setText("会前管理");
            } else{
                faqiHolder.typecolor.setBackgroundResource(R.color.blue);
                faqiHolder.btn_next.setBackgroundResource(R.mipmap.banyuan);
                faqiHolder.btn_next.setTextColor(mContext.getResources().getColor(R.color.blue));
                faqiHolder.tv_state.setText("已结束");
                faqiHolder.btn_next.setText("会后整理");
            }

        }else if (type==TYPE_YAOQING){
            YaoqingHolder yaoqingHolder = (YaoqingHolder) holder;
            yaoqingHolder.tv_name.setText(huiyi.getName());
            yaoqingHolder.tv_time.setText(huiyi.getTime());
            yaoqingHolder.tv_fuzeren.setText(huiyi.getFuzeren());
            yaoqingHolder.tv_place.setText(huiyi.getPlace());
            if (huiyi.getState()==1){
                yaoqingHolder.tv_choose.setText("参加");
                yaoqingHolder.tv_choose.setTextColor(mContext.getResources().getColor(R.color.blue));
                yaoqingHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("s","ds");
                        //Intent intent = new Intent(mContext,DetailActivity.class);
                        //mContext.startActivity(intent);
                    }
                });
            }else if (huiyi.getState()==2){
                yaoqingHolder.tv_choose.setText("谢绝");
                yaoqingHolder.tv_choose.setTextColor(mContext.getResources().getColor(R.color.blue));
                yaoqingHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("s","ds");
                        //Intent intent = new Intent(mContext,DetailActivity.class);
                        //mContext.startActivity(intent);
                    }
                });
            }else {
                yaoqingHolder.tv_choose.setText("未回复");
                yaoqingHolder.tv_choose.setTextColor(mContext.getResources().getColor(R.color.blue));

                if (mOnItemClickListener != null){
                    yaoqingHolder.cardview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onClick(v,position);
                        }
                    });
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener (OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void canjia(int position){
        Huiyi h = mDatas.get(position);
        h.setState(1);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public void xiejue(int position) {
        Huiyi h = mDatas.get(position);
        h.setState(2);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    private class FaqingHolder extends RecyclerView.ViewHolder{
        View typecolor;
        TextView tv_state;
        TextView tv_name;
        TextView tv_time;
        TextView btn_next;

        public FaqingHolder(View itemView) {
            super(itemView);
            typecolor = (View) itemView.findViewById(R.id.typecolor);
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            btn_next = (TextView) itemView.findViewById(R.id.btn_next);
        }
    }

    private class YaoqingHolder extends RecyclerView.ViewHolder{
        CardView cardview;
        TextView tv_name;
        TextView tv_time;
        TextView tv_fuzeren;
        TextView tv_place;
        TextView tv_choose;
        public YaoqingHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_fuzeren = (TextView) itemView.findViewById(R.id.tv_fuzeren);
            tv_place = (TextView) itemView.findViewById(R.id.tv_place);
            tv_choose = (TextView) itemView.findViewById(R.id.tv_choose);
        }
    }
}