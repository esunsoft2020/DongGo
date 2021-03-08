package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Tab2RecyclerAdapter extends RecyclerView.Adapter<Tab2RecyclerAdapter.VH> {

    Context context;
    ArrayList<Tab2RecyclerItem> items;

    public Tab2RecyclerAdapter(Context context, ArrayList<Tab2RecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        return new VH(inflater.inflate(R.layout.tab2_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Tab2RecyclerItem item = items.get(position);

        holder.tvName.setText(item.name);
        holder.tvDetail.setText(item.detail);
        holder.ratingBar.setRating(item.ratingBar);
        holder.rate.setText(item.ratingBar+"");
        holder.reviewCnt.setText(item.reviewCnt);
        holder.workCnt.setText(item.workCnt);

        Glide.with(context).load(item.imgUrl).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvName;
        TextView tvDetail;
        RatingBar ratingBar;
        TextView rate;
        TextView reviewCnt;
        TextView workCnt;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDetail = itemView.findViewById(R.id.detail);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            rate = itemView.findViewById(R.id.tv_ratingNum);
            reviewCnt = itemView.findViewById(R.id.tv_reviewcnt);
            workCnt = itemView.findViewById(R.id.tv_workcnt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(context).setTitle(tvName.getText().toString()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
                }
            });


        }
    }
}
