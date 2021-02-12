package com.esunsoft2020.donggo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Service2Adapter extends RecyclerView.Adapter<Service2Adapter.VH> {

    Context context;
    ArrayList<Service2Item> items;

    public Service2Adapter() {
    }

    public Service2Adapter(Context context, ArrayList<Service2Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.service_item,parent,false);

        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        VH vh = (VH)holder;
        Service2Item item = items.get(position);
        vh.tv.setText(item.service);

        Glide.with(context).load(item.imgUrl).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getLayoutPosition();

                    String imgUrl = items.get(position).imgUrl;
                    String service = items.get(position).service;

                    Intent intent = new Intent(context,ClickServiceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("service",new String[]{imgUrl,service});
                    intent.putExtras(bundle);
                    context.startActivity(intent);


                    //전환 효과 [ LoLLIPOP 버전 이상 -api.21 ]
//                    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity)context,new Pair<View,String>(ivImg,"img"));
//                        context.startActivity(intent, options.toBundle());
//                    }else {
//                        context.startActivity(intent);
//                    }


                }
            });

        }
    }
}
