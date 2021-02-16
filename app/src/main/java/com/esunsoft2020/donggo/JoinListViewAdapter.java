package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class JoinListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;

    public JoinListViewAdapter(Context context, ArrayList<String> items) {
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
            convertView = inflater.inflate(R.layout.gosu_join2_listview_item,parent,false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.cb);
        TextView tvTitle = convertView.findViewById(R.id.tv);

        tvTitle.setText(items.get(position));
        checkBox.setTag(items.get(position));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) tvTitle.setTextColor(context.getResources().getColor(R.color.brandColor));
                else tvTitle.setTextColor(context.getResources().getColor(R.color.text_normal));
            }
        });

        return convertView;
    }
}
