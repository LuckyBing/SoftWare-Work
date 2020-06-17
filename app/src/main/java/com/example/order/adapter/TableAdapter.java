package com.example.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Common.Table;
import com.example.order.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder>{
    private List<Table> tableList;
    private Context context;

    public TableAdapter(List<Table> tableList) {
        this.tableList = tableList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Table y=tableList.get(position);
        holder.tableId.setText(String.valueOf(y.getId())+"号桌");
        holder.tableNum.setText("限坐"+String.valueOf(y.getNum())+"人");
        holder.tableview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnItemClickListener.onItemcardClick(holder.getAdapterPosition(),tableList);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }
    private OnItemClickListener mOnItemClickListener;//声明接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemcardClick(int pos, List<Table> tableLists);//查看
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View tableview;
        TextView tableId;
        TextView tableNum;

        public ViewHolder(View itemView) {
            super(itemView);
            tableview=itemView;
            tableId = itemView.findViewById(R.id.tableId);
            tableNum=itemView.findViewById(R.id.tableNum);

        }
    }
}
