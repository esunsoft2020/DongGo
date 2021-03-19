package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class SelectAreaAdapter extends RecyclerView.Adapter<SelectAreaAdapter.VH> {

    Context context;
    String[] items;

    String[][] detail;
    ArrayAdapter adapter1;


    public SelectAreaAdapter(Context context, String[] items, String[][] detail) {
        this.context = context;
        this.items = items;
        this.detail = detail;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.area_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.tv.setText(items[position]);
        adapter1 = new ArrayAdapter(context,android.R.layout.simple_list_item_1,detail[position]);
        holder.listView.setAdapter(adapter1);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean click = false;

                if(!click) {
                    holder.layout.setVisibility(View.VISIBLE);
                    click = true;
                } else {
                    holder.layout.setVisibility(View.GONE);
                    click = false;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ListView listView;
        RelativeLayout layout;

        public VH(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.clickable_layout);
            tv = itemView.findViewById(R.id.tv);
            listView = itemView.findViewById(R.id.listview);

        }
    }
}
