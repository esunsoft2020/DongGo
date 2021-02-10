package com.esunsoft2020.donggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Tab1ClientFragment extends Fragment {

    RelativeLayout searchLayout;
    TextView[] all = new TextView[2];
    ImageView[] branchs = new ImageView[8];
    TextView[] titles = new TextView[8];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1client,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchLayout = view.findViewById(R.id.search);

        for(int i=0; i<all.length;i++){
            all[i] = view.findViewById(R.id.all1+i);
        }

        for(int i=0; i<branchs.length; i++){
            branchs[i] = view.findViewById(R.id.branch1+i);
        }

        for(int i =0 ; i<titles.length;i++){
            titles[i] = view.findViewById(R.id.tv10+i);
        }



    }

    @Override
    public void onResume() {
        super.onResume();

        Glide.with(this).load("http://donggo.dothome.co.kr/icon/resson.png").into(branchs[0]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/home.png").into(branchs[1]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/event.png").into(branchs[2]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/business.png").into(branchs[3]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/design.png").into(branchs[4]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/health.png").into(branchs[5]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/alba.png").into(branchs[6]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/else.png").into(branchs[7]);

        for (int i=0 ; i<branchs.length;i++){
            branchs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIcon(v);
                }
            });
        }

        for(int i=0 ; i<all.length ; i++){
            all[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickAll(v);
                }
            });
        }

        for(int i=0 ; i<titles.length ; i++){
            titles[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIcon(v);
                }
            });
        }

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });








    }

    void clickIcon(View v){

        //TODO : 다른 액티비티 생성하기

        Intent intent = new Intent(getActivity(),AllServiceActivity.class);
        if(v.getTag()==null){
            intent.putExtra("service",((TextView)v).getText().toString());

        }else{
            intent.putExtra("service",v.getTag().toString());
        }

        startActivity(intent);
    }

    void clickAll(View v){

        Intent intent = new Intent(getActivity(),AllServiceActivity.class);
        intent.putExtra("service",v.getTag().toString());
        startActivity(intent);

    }

}
