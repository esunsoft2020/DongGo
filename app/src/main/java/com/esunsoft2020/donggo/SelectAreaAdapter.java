package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SelectAreaAdapter extends RecyclerView.Adapter<SelectAreaAdapter.VH> {

    Context context;
    String[] items;

    AreaListViewAdapter adapter;
    String[][] detail;


    public SelectAreaAdapter(Context context, String[] items, String[][] detail) {
        this.context = context;
        this.items = items;
        this.detail = detail;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        return new VH(inflater.inflate(R.layout.area_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        holder.tv.setText(items[position]);

        adapter = new AreaListViewAdapter(context,detail);
        holder.listView.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ListView listView;

        public VH(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            listView = itemView.findViewById(R.id.listview);
        }
    }
}
