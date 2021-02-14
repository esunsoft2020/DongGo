package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.VH> {

    Context context;
    ArrayList<TwoString> items;

    public AccountRecyclerAdapter(Context context, ArrayList<TwoString> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new VH(inflater.inflate(R.layout.account_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TwoString item = items.get(position);
        holder.tv.setText(item.First);
        holder.tvInfo.setText(item.Second);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tv;
        TextView tvInfo;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tvInfo = itemView.findViewById(R.id.tv_info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "준비중입니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
