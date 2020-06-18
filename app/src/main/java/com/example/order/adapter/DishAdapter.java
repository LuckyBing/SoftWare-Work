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

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder>{
    private List<Table> tableList;
    private Context context;

    public DishAdapter(List<Table> tableList) {
        this.tableList = tableList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Table y=tableList.get(position);
        holder.tableId.setText(y.getId());
        holder.tableNum.setText(y.getNum()+"份");



    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View tableview;
        TextView tableId;
        TextView tableNum;

        public ViewHolder(View itemView) {
            super(itemView);
            tableview=itemView;
            tableId = itemView.findViewById(R.id.name);
            tableNum=itemView.findViewById(R.id.num);

        }
    }
}
