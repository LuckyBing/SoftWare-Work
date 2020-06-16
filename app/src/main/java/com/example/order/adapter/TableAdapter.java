package com.example.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Common.table;
import com.example.order.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder>{
    private List<table> tableList;
    private Context context;

    public TableAdapter(List<table> tableList) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        table y=tableList.get(position);
        holder.tableId.setText(y.getTableId());
        holder.tableNum.setText(y.getTableNum());


    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tableId;
        TextView tableNum;

        public ViewHolder(View itemView) {
            super(itemView);
            tableId = itemView.findViewById(R.id.tableId);
            tableNum=itemView.findViewById(R.id.tableNum);

        }
    }
}
