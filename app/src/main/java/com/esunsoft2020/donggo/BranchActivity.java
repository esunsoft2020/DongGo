package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class BranchActivity extends AppCompatActivity {


    ImageView[] branchs = new ImageView[8];
    TextView[] titles = new TextView[8];

    LinearLayout title;
    ImageView ivTitle;
    RelativeLayout dropdownLayout, container;
    boolean drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        for(int i=0; i<branchs.length; i++){
            branchs[i] = findViewById(R.id.branch1+i);
        }

        for(int i =0 ; i<titles.length;i++){
            titles[i] = findViewById(R.id.tv10+i);
        }

        title = findViewById(R.id.title);
        ivTitle = findViewById(R.id.iv);
        dropdownLayout = findViewById(R.id.dropdown_layout);
        container = findViewById(R.id.container);
        container.setBackgroundColor(getResources().getColor(R.color.white));

    }

    @Override
    protected void onResume() {
        super.onResume();
        drop = false;
        
        //TODO : container 변경 필요(Linear에서 나머지 부분 회색처리- 클릭시 이벤트 추가)
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drop){
                    Picasso.get().load(android.R.drawable.arrow_up_float).into(ivTitle);
                    dropdownLayout.setVisibility(View.VISIBLE);
                    container.setClickable(false);
                    container.setBackgroundColor(getResources().getColor(R.color.clear_gray));
                    drop = true;
                }else {
                    Picasso.get().load(android.R.drawable.arrow_down_float).into(ivTitle);
                    dropdownLayout.setVisibility(View.GONE);
                    container.setClickable(true);
                    container.setBackgroundColor(getResources().getColor(R.color.white));
                    drop = false;
                }
            }
        });


        Glide.with(this).load("http://donggo.dothome.co.kr/icon/lesson.png").into(branchs[0]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/home.png").into(branchs[1]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/event.png").into(branchs[2]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/business.png").into(branchs[3]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/design.png").into(branchs[4]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/health.png").into(branchs[5]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/alba.png").into(branchs[6]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/else.png").into(branchs[7]);
    }
}