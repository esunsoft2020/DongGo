package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class GosuJoin2Activity extends AppCompatActivity {

    ListView listView;
    JoinListViewAdapter adapter;
    ArrayList<String> items = new ArrayList<>();

    RelativeLayout next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join2);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listview);
        adapter = new JoinListViewAdapter(this,items);
        listView.setAdapter(adapter);

        next = findViewById(R.id.next_layout);
    }


    @Override
    protected void onResume() {
        super.onResume();
        next.setClickable(false);
        next.setBackgroundResource(R.color.text_light_gray);

        items.clear();
        String branch = RegisterGosu.gosuBranch;

        switch (branch){
            case "레슨":
                items.add("학업");
                items.add("외국어");
                items.add("외국어시험");
                items.add("공예");
                items.add("미술");
                items.add("음악이론/보컬");
                break;
            case "홈/리빙":
                items.add("이사");
                items.add("청소 업체");
                items.add("인테리어");
                items.add("야외 시공");
                items.add("부동산");
                items.add("철거/정리");
                items.add("펫/반려동물");
                items.add("문/창문");
                break;
            case "이벤트":
                items.add("웨딩");
                items.add("촬영 및 편집");
                items.add("뮤직/엔터테인먼트");
                items.add("음식");
                items.add("뷰티/미용");
                items.add("기획 및 장식");
                break;
            case "비즈니스":
                items.add("번역");
                items.add("통역");
                items.add("문서");
                items.add("인쇄");
                items.add("마케팅");
                break;
            case "디자인/개발":
                items.add("디자인 외주");
                items.add("개발 외주");
                break;
            case "건강/미용":
                items.add("심리");
                items.add("미용");
                items.add("건강");
                break;
            case "알바":
                items.add("서빙.주방 알바");
                items.add("매장관리.판매 알바");
                items.add("서비스.행사 알바");
                items.add("문화.여가.생활 알바");
                items.add("방송.미디어 알바");
                break;
            case "기타":
                items.add("여행");
                items.add("공예제작");
                items.add("의류/잡화");
                items.add("자동차");
                items.add("대여/대관");
                items.add("금융");
                break;
        }

        //TODO : CheckBox 클릭시 다음으로 넘어갈 수 있는 작업 필요 (아래 작업 작동X)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb = view.findViewById(R.id.cb);
                if(cb.isChecked()){
                    next.setClickable(true);
                    next.setBackgroundResource(R.color.brandColor);
                }
            }
        });
    }





    public void clickNext(View view) {
        startActivity(new Intent(this,GosuJoin3Activity.class));
        finish();
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

    public void clickClear(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,GosuJoinActivity.class));
        finish();
    }

}