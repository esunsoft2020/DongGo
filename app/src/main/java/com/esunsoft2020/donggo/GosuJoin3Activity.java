package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;


public class GosuJoin3Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> items = new ArrayList<>();

    RelativeLayout next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join3);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = findViewById(R.id.listview);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,items);
        listView.setAdapter(adapter);

        next = findViewById(R.id.next_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        next.setClickable(false);
        next.setBackgroundResource(R.color.text_light_gray);

        initItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                next.setClickable(true);
                next.setBackgroundResource(R.color.brandColor);
            }
        });


    }
    private void initItem(){
        items.clear();
        ArrayList<String> detail = RegisterGosu.service;

        for(int i =0 ; i<detail.size() ; i++){
            TypedArray arrayText;
            switch (detail.get(i)) {
                case "학업":
                    arrayText = getResources().obtainTypedArray(R.array.study1);
                    break;
                case "외국어":
                    arrayText = getResources().obtainTypedArray(R.array.language1);
                    break;
                case "외국어시험":
                    arrayText = getResources().obtainTypedArray(R.array.foreign_test1);
                    break;
                case "공예":
                    arrayText = getResources().obtainTypedArray(R.array.crafts1);
                    break;
                case "미술":
                    arrayText = getResources().obtainTypedArray(R.array.art1);
                    break;
                case "음악이론/보컬":
                    arrayText = getResources().obtainTypedArray(R.array.music1);
                    break;


                case "이사":
                    arrayText = getResources().obtainTypedArray(R.array.esa2);
                    break;
                case "청소 업체":
                    arrayText = getResources().obtainTypedArray(R.array.clean2);
                    break;
                case "인테리어":
                    arrayText = getResources().obtainTypedArray(R.array.interior2);
                    break;
                case "야외 시공":
                    arrayText = getResources().obtainTypedArray(R.array.outside2);
                    break;
                case "부동산":
                    arrayText = getResources().obtainTypedArray(R.array.home_buy2);
                    break;
                case "철거/정리":
                    arrayText = getResources().obtainTypedArray(R.array.pull_down2);
                    break;
                case "펫/반려동물":
                    arrayText = getResources().obtainTypedArray(R.array.pet2);
                    break;
                case "문/창문":
                    arrayText = getResources().obtainTypedArray(R.array.door2);
                    break;


                case "웨딩":
                    arrayText = getResources().obtainTypedArray(R.array.wedding3);
                    break;
                case "촬영 및 편집":
                    arrayText = getResources().obtainTypedArray(R.array.camera3);
                    break;
                case "뮤직/엔터테인먼트":
                    arrayText = getResources().obtainTypedArray(R.array.music3);
                    break;
                case "음식":
                    arrayText = getResources().obtainTypedArray(R.array.food3);
                    break;
                case "뷰티/미용":
                    arrayText = getResources().obtainTypedArray(R.array.beauty3);
                    break;
                case "기획 및 장식":
                    arrayText = getResources().obtainTypedArray(R.array.plan3);
                    break;


                case "번역":
                    arrayText = getResources().obtainTypedArray(R.array.writer4);
                    break;
                case "통역":
                    arrayText = getResources().obtainTypedArray(R.array.speaker4);
                    break;
                case "문서":
                    arrayText = getResources().obtainTypedArray(R.array.paper4);
                    break;
                case "인쇄":
                    arrayText = getResources().obtainTypedArray(R.array.print4);
                    break;
                case "마케팅":
                    arrayText = getResources().obtainTypedArray(R.array.marketing4);
                    break;


                case "디자인 외주":
                    arrayText = getResources().obtainTypedArray(R.array.design5);
                    break;
                case "개발 외주":
                    arrayText = getResources().obtainTypedArray(R.array.develop5);
                    break;


                case "심리":
                    arrayText = getResources().obtainTypedArray(R.array.mental6);
                    break;
                case "미용":
                    arrayText = getResources().obtainTypedArray(R.array.miyong6);
                    break;
                case "건강":
                    arrayText = getResources().obtainTypedArray(R.array.health6);
                    break;


                case "서빙.주방 알바":
                    arrayText = getResources().obtainTypedArray(R.array.alba7_1);
                    break;
                case "매장관리.판매 알바":
                    arrayText = getResources().obtainTypedArray(R.array.alba7_2);
                    break;
                case "서비스.행사 알바":
                    arrayText = getResources().obtainTypedArray(R.array.alba7_3);
                    break;
                case "문화.여가.생활 알바":
                    arrayText = getResources().obtainTypedArray(R.array.alba7_4);
                    break;
                case "방송.미디어 알바":
                    arrayText = getResources().obtainTypedArray(R.array.alba7_5);
                    break;


                case "여행":
                    arrayText = getResources().obtainTypedArray(R.array.else8_1);
                    break;
                case "공예제작":
                    arrayText = getResources().obtainTypedArray(R.array.else8_2);
                    break;
                case "의류/잡화":
                    arrayText = getResources().obtainTypedArray(R.array.else8_3);
                    break;
                case "자동차":
                    arrayText = getResources().obtainTypedArray(R.array.else8_4);
                    break;
                case "대여/대관":
                    arrayText = getResources().obtainTypedArray(R.array.else8_5);
                    break;
                case "금융":
                    arrayText = getResources().obtainTypedArray(R.array.else8_6);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + detail);
            }
            for(int j=0 ; j< arrayText.length() ; j++){
                String s = arrayText.getString(j);
                items.add(s);
            }
        }



    }

    public void clickNext(View view) {
        RegisterGosu.detail.clear();

        SparseBooleanArray checkedItemPosition = listView.getCheckedItemPositions();

        for(int i=0 ; i<checkedItemPosition.size();i++){
            int pos = checkedItemPosition.keyAt(i);

            if(checkedItemPosition.valueAt(i)){
                RegisterGosu.detail.add(listView.getItemAtPosition(pos).toString());
            }
        }
        if(RegisterGosu.detail.size()>0) {
            startActivity(new Intent(this, GosuJoin4Activity.class));

            RegisterGosu.serviceDetail = "{\"serviceDetail\":[\"";
            for(int i=0 ; i<RegisterGosu.detail.size() ; i++){
                RegisterGosu.serviceDetail +=RegisterGosu.detail.get(i);
                if(i<(RegisterGosu.detail.size()-1)){
                    RegisterGosu.serviceDetail +=",";
                }
            }
            RegisterGosu.serviceDetail += "\"]}";
//            Log.e("serviceDetail",RegisterGosu.serviceDetail);
            finish();
        }else Toast.makeText(this, "서비스를 선택해주세요.", Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(this, GosuJoin2Activity.class);
        startActivity(intent);
        finish();
    }

}