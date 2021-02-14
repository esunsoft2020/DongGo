package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AreaListViewAdapter extends BaseAdapter {

    Context context;
    String[][] items;
    int position;


    //TODO : https://lakue.tistory.com/15
    public AreaListViewAdapter(Context context, String[][] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items[position].length;
    }

    @Override
    public Object getItem(int position) {
        return items[position][position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.position = position;

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.area_area_item,parent,false);
        }
        TextView tv = convertView.findViewById(R.id.tv);
        String s = items[position][0];
        tv.setText(s);


        return convertView;
    }
}
