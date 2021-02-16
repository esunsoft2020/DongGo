package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {

    Context context;
    ArrayList<TwoStringItem> items;

    public GridviewAdapter(Context context, ArrayList<TwoStringItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.gridview_item,parent,false);
        }

        TwoStringItem g = items.get(position);

        ImageView iv = convertView.findViewById(R.id.iv);
        TextView tv = convertView.findViewById(R.id.tv);

        Glide.with(context).load(g.first).into(iv);
        tv.setText(g.second);

        return convertView;
    }
}
