package com.example.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.order.Data.Yuding;
import com.example.order.R;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 87310 on 2018/9/1.
 */
public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.ViewHolder> {
    private List<Yuding> mCheckList;
    private Context context;
    public CheckAdapter(List<Yuding> mCheckList) {
        this.mCheckList = mCheckList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Yuding y = mCheckList.get(position);
        holder.tv_ordertime.setText(y.getOrdertime());
        holder.tv_mrname.setText(y.getMrname());
        holder.tv_meettime.setText(y.getMeettime());
        holder.tv_meetname.setText(y.getMeetname());
        if (y.getState()==1){
            holder.tv_state.setText("已预定");
            holder.tv_edit.setText("修改");
            holder.tv_cancel.setText("取消");
        }else if (y.getState()==2){
            if(y.ispingjia()){
                holder.tv_edit.setText("已评价");
                holder.tv_edit.setClickable(false);
            }else {
                holder.tv_type.setText("完成日期：");
                holder.tv_state.setText("已完成");
                holder.tv_edit.setText("评价");
                holder.tv_cancel.setText("删除");
                holder.tv_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onClick(v, position);
                    }
                });
            }

        }else if (y.getState()==3){
            holder.tv_type.setText("取消日期：");
            holder.tv_state.setText("已取消");
            holder.tv_edit.setText("重新预定");
            holder.tv_cancel.setText("删除");
        }
        else if(y.getState()==0)
        {
            holder.tv_type.setText("预定日期：");
            holder.tv_state.setText("待付款");
            holder.tv_edit.setText("付款");
            holder.tv_cancel.setText("取消");
        }
        else if(y.getState()==4)
        {
            holder.tv_type.setText("预定日期：");
            holder.tv_state.setText("使用中");
            holder.tv_edit.setText("目标导航");
            holder.tv_cancel.setText("退房");
        }
        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tv_cancel.getText().toString().equals("取消")) {
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("您真的要取消预定吗?")
                            .setCancelText("点错了")
                            .setConfirmText("是的，取消")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    mCheckList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyDataSetChanged();
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
                else
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("确认删除此订单?")
                            .setCancelText("取消")
                            .setConfirmText("删除")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    mCheckList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyDataSetChanged();
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
        });
        holder.tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent inten=new Intent(context, ServiceActivity.class);
                //context.startActivity(inten);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCheckList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_type;
        TextView tv_more;
        TextView tv_ordertime;
        TextView tv_mrname;
        TextView tv_meettime;
        TextView tv_meetname;
        TextView tv_state;
        TextView tv_edit;
        TextView tv_cancel;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_ordertime = (TextView) itemView.findViewById(R.id.tv_ordertime);
            tv_mrname = (TextView) itemView.findViewById(R.id.tv_mrname);
            tv_meettime = (TextView) itemView.findViewById(R.id.tv_meettime);
            tv_meetname = (TextView) itemView.findViewById(R.id.tv_meetname);
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            tv_edit = (TextView) itemView.findViewById(R.id.tv_edit);
            tv_cancel = (TextView) itemView.findViewById(R.id.tv_cancel);
            tv_more=itemView.findViewById(R.id.tv_more);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    private HuiyiAdapter.OnItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener (HuiyiAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void pingjia(int position) {
        Yuding y = mCheckList.get(position);
        y.setIspingjia(true);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

}
