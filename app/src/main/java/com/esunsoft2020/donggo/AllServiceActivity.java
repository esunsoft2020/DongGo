package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class AllServiceActivity extends AppCompatActivity {

    TextView title;
    GridView gridView;
    GridviewAdapter adapter;
    ArrayList<GridViewItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        title = findViewById(R.id.title);

        gridView = findViewById(R.id.gird_view);
        adapter = new GridviewAdapter(this,items);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        title.setText(bundle.getString("service"));
        int serviceNum = bundle.getInt("s",0);
//        Toast.makeText(this, serviceNum+"", Toast.LENGTH_SHORT).show();
        items.clear();

        //TODO : serviceNum 안 넘어옴...
        switch (bundle.getString("service")){
            case "추천 서비스":
                inflatePic2();
                break;

            case "신규 서비스":
                inflatePic3();
                break;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String imgUrl = items.get(position).imgUrl;
                String service = items.get(position).title;

                Intent intent = new Intent(AllServiceActivity.this,ClickServiceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("service",new String[]{imgUrl,service});
                intent.putExtras(bundle);
                AllServiceActivity.this.startActivity(intent);
            }
        });
    }

    void inflatePic2(){
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/cafe.jpg","상업공간 인테리어"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/study.jpg","영어 과외"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/marketing.jpg","블로그 마케팅"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/box.jpg","원룸/소형 이사"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/insurance.jpg","보험 설계"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/camera.jpg","기업/상업용 영상 촬영"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/interior.jpg","아파트 인테리어"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service2/pulldown.jpg","철거"));
    }
    void inflatePic3(){
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/construct.jpg","외풍차단/틈막이 시공"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/whisky.jpg","위스키 레슨"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/choco.jpg","초콜릿 레슨"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/food.jpg","명절/제사 음식 대행"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/stock.jpg","투자 레슨"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/news.jpg","언론사 준비"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/law.jpg","금융 법률 상담"));
        items.add(new GridViewItem("http://donggo.dothome.co.kr/icon/service3/controllar.jpg","관세사 준비"));

    }
    public void clickClear(View view) {
        onBackPressed();
    }
}