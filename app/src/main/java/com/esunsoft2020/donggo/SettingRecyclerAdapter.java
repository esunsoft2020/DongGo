package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SettingRecyclerAdapter extends RecyclerView.Adapter<SettingRecyclerAdapter.VH> {

    Context context;
    ArrayList<TwoStringItem> items;

    public SettingRecyclerAdapter(Context context, ArrayList<TwoStringItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new VH(inflater.inflate(R.layout.setting_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TwoStringItem item = items.get(position);
        holder.tv.setText(item.second);
        Glide.with(context).load(item.first).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    String setting = items.get(position).second;
                    Toast.makeText(context, setting+"준비중입니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
