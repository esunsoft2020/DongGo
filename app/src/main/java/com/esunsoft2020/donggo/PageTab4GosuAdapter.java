package com.esunsoft2020.donggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PageTab4GosuAdapter extends PagerAdapter {

    Context context;
    ArrayList<TwoStringItem> items = new ArrayList<>();

    public PageTab4GosuAdapter(Context context, ArrayList<TwoStringItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View page = inflater.inflate(R.layout.tab4_page,null);

        ImageView[] imageViews = new ImageView[]{page.findViewById(R.id.iv1),
                page.findViewById(R.id.iv2),
                page.findViewById(R.id.iv3),
                page.findViewById(R.id.iv4),
                page.findViewById(R.id.iv5),
                page.findViewById(R.id.iv6),
                page.findViewById(R.id.iv7),
                page.findViewById(R.id.iv8)};

        for(int i=0;i<imageViews.length;i++){
            Glide.with(context).load("http://donggo.dothome.co.kr/icon/graycolor_point.png").into(imageViews[i]);
        }

        Glide.with(context).load("http://donggo.dothome.co.kr/icon/color_point.png").into(imageViews[position]);

        TextView tvExplain = page.findViewById(R.id.tv1);
        TextView tvBtn = page.findViewById(R.id.tv2);

        TwoStringItem item = items.get(position);

        tvExplain.setText(item.first);
        tvBtn.setText(item.second);

        RelativeLayout upload = page.findViewById(R.id.media_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.second+" 준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(page);

        return page;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view==object;
    }
}
