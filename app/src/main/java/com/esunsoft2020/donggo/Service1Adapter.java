package com.esunsoft2020.donggo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class Service1Adapter extends RecyclerView.Adapter<Service1Adapter.VH> {

    Context context;
    ArrayList<Service1Item> items;

    public Service1Adapter() {
    }

    public Service1Adapter(Context context, ArrayList<Service1Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        return new VH(inflater.inflate(R.layout.tab1_popular_service_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Service1Item item = items.get(position);
        holder.tvTitle.setText(item.title);
        holder.tvCount.setText(item.count);

        Glide.with(context).load(item.imgUrl).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvTitle;
        TextView tvCount;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCount = itemView.findViewById(R.id.tv_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                    String imgUrl = items.get(position).imgUrl;
                    String service = items.get(position).title;

                    Intent intent = new Intent(context,ClickServiceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("service",new String[]{imgUrl,service});
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }
}
