package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class BranchActivity extends AppCompatActivity {


    ImageView[] branchs = new ImageView[8];
    TextView[] titles = new TextView[8];

    LinearLayout title;
    ImageView ivTitle;
    RelativeLayout dropdownLayoutBottom, container;
    LinearLayout dropdownAllLayout;
    TextView tvTitle;
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
        dropdownLayoutBottom = findViewById(R.id.dropdown_layout1);
        container = findViewById(R.id.container);
        dropdownAllLayout = findViewById(R.id.dropdown_all);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle = findViewById(R.id.tv_title);

    }

    @Override
    protected void onResume() {
        super.onResume();
        drop = false;

        tvTitle.setText(getIntent().getStringExtra("service"));

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drop) clickDropFalse();
                else clickDropTrue();
            }
        });

        dropdownLayoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDropTrue();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void clickDropFalse(){
        Picasso.get().load(android.R.drawable.arrow_up_float).into(ivTitle);
        dropdownAllLayout.setVisibility(View.VISIBLE);
        container.setClickable(false);
        dropdownLayoutBottom.setClickable(true);
        drop = true;
    }

    void clickDropTrue(){
        Picasso.get().load(android.R.drawable.arrow_down_float).into(ivTitle);
        dropdownAllLayout.setVisibility(View.GONE);
        container.setClickable(true);
        dropdownLayoutBottom.setClickable(true);
        drop = false;
    }

    public void clickIcon(View view) {
        clickDropDownItem(view);
    }

    void clickDropDownItem(View view){
        String title;
        switch (view.getTag()+""){
            case "레슨":
                title = "레슨";
                break;
            case "홈/리빙":
                title = "홈/리빙";
                break;
            case "이벤트":
                title = "이벤트";
                break;
            case "비즈니스":
                title = "비즈니스";
                break;
            case "디자인/개발":
                title = "디자인/개발";
                break;
            case "건강/미용":
                title = "건강/미용";
                break;
            case "알바":
                title = "알바";
                break;
            case "기타":
                title = "기타";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getTag() + "");
        }
        tvTitle.setText(title);
        clickDropTrue();
    }
}