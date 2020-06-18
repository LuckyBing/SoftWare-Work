package com.example.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.order.Activity.ViewActivity;
import com.example.order.Data.Yuding;
import com.example.order.Http.HttpUtil;
import com.example.order.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import android.os.Handler;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
        final Yuding y = mCheckList.get(position);
        holder.tv_ordertime.setText(y.getMeettime());
        holder.tv_mrname.setText(y.getMrname()+"号桌");
        holder.tv_meettime.setText("预定时间："+y.getOrdertime());
        holder.tv_meetname.setText("总价："+y.getMeetname()+"元");
        if (y.getState()==0){
            holder.tv_state.setText("处理中");
            holder.tv_edit.setText("加菜");
            holder.tv_cancel.setText("付款");
            holder.tv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemcardClick(holder.getAdapterPosition(), mCheckList);
                    Log.d("cao","Click"+String.valueOf(holder.getAdapterPosition()));
                }
            });
        }else if (y.getState()==1){

                holder.tv_type.setText("完成日期：");
                holder.tv_state.setText("已完成");
                holder.tv_edit.setText("评价");
                holder.tv_cancel.setText("删除");



        }
        holder.tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte=new Intent(view.getContext(), ViewActivity.class);
                inte.putExtra("pos",String.valueOf(position));
                inte.putExtra("state",String.valueOf(y.getState()));
                view.getContext().startActivity(inte);
                Log.d("ds","sgdf");

            }
        });
        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemOrderClick(holder.getAdapterPosition(), mCheckList);
                Log.d("fuc","Click"+String.valueOf(holder.getAdapterPosition()));
            }
        });

//        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (holder.tv_cancel.getText().toString().equals("删除")) {
//                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("您真的要删除吗?")
//                            .setCancelText("点错了")
//                            .setConfirmText("是的，删除")
//                            .showCancelButton(true)
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                    mCheckList.remove(position);
//                                    notifyItemRemoved(position);
//                                    notifyDataSetChanged();
//                                }
//                            })
//                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.cancel();
//                                }
//                            })
//                            .show();
//
//                }
//                else
//                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("确认付款?")
//                            .setCancelText("取消")
//                            .setConfirmText("付款")
//                            .showCancelButton(true)
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(final SweetAlertDialog sDialog) {
//
//                                    String url="http://120.26.185.147:10085/Order/changeorderstate?order_id="+y.getOrdertime()+"&state=over";
//                                    Log.d("dsf",url);
//                                    HttpUtil.sendOkHttpRequest(url, new Callback() {
//                                        @Override
//                                        public void onFailure(Call call, IOException e) {
//
//                                        }
//
//                                        @Override
//                                        public void onResponse(Call call, Response response) throws IOException {
//                                            String responseText = response.body().string();
//                                            try {
//                                                JSONObject jsonObject = new JSONObject(responseText);
//                                                String info=jsonObject.getString("info");
//                                                Log.d("ds",info);
//                                                if(info.equals("成功"))
//                                                {
//
//
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    });
////                                    handler = new Handler() {
////                                        public void handleMessage(Message msg) {
////                                            // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
////                                            switch (msg.what) {
////                                                case 0:
//                                                    sDialog.dismissWithAnimation();
//                                                    mCheckList.remove(position);
//                                                    notifyItemRemoved(position);
//                                                    notifyDataSetChanged();
////                                                    break;
////                                            }
////                                        }
////                                    };
//                                }
//                            })
//                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.cancel();
//                                }
//                            })
//                            .show();
//            }
//        });
//        holder.tv_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent inten=new Intent(context, ServiceActivity.class);
//                //context.startActivity(inten);
//            }
//        });
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

//    public interface OnItemClickListener {
//        void onClick(View view, int position);
//    }
    private OnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemOrderClick(int pos, List<Yuding> roomLists);//预定
        void onItemcardClick(int pos, List<Yuding> roomLists);//查看
    }

//    private HuiyiAdapter.OnItemClickListener mOnItemClickListener = null;
//    public void setOnItemClickListener (HuiyiAdapter.OnItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }

    public void pingjia(int position) {
        Yuding y = mCheckList.get(position);
        y.setIspingjia(true);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

}
