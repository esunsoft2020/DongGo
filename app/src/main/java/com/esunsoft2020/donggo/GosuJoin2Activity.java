package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class GosuJoin2Activity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
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
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,items);
        listView.setAdapter(adapter);

        next = findViewById(R.id.next_layout);
    }


    @Override
    protected void onResume() {
        super.onResume();
        next.setClickable(false);
        next.setBackgroundResource(R.color.text_light_gray);

        //리스트뷰 초기화
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
        String branch = RegisterGosu.gosuBranch;
        TypedArray arrayText;
        switch (branch) {
            case "레슨":
                arrayText = getResources().obtainTypedArray(R.array.lesson);
                break;
            case "홈/리빙":
                arrayText = getResources().obtainTypedArray(R.array.home);
                break;
            case "이벤트":
                arrayText = getResources().obtainTypedArray(R.array.event);
                break;
            case "비즈니스":
                arrayText = getResources().obtainTypedArray(R.array.business);
                break;
            case "디자인/개발":
                arrayText = getResources().obtainTypedArray(R.array.design);
                break;
            case "건강/미용":
                arrayText = getResources().obtainTypedArray(R.array.health);
                break;
            case "알바":
                arrayText = getResources().obtainTypedArray(R.array.alba);
                break;
            case "기타":
                arrayText = getResources().obtainTypedArray(R.array.else_e);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + branch);
        }
        for(int i=0 ; i< arrayText.length() ; i++){
            String s = arrayText.getString(i);
//            boolean b = false;
//            String1Boolean1Item item = new String1Boolean1Item(s,b);
            items.add(s);
        }
     }




    public void clickNext(View view) {
        RegisterGosu.service.clear();

        SparseBooleanArray checkedItemPosition = listView.getCheckedItemPositions();
        for(int i=0 ; i<checkedItemPosition.size();i++){
            int pos = checkedItemPosition.keyAt(i);
            if(checkedItemPosition.valueAt(i)){
                RegisterGosu.service.add(listView.getItemAtPosition(pos).toString());
            }
        }
        if(RegisterGosu.service.size()>0) {
            startActivity(new Intent(this, GosuJoin3Activity.class));

            RegisterGosu.gosuService = "{\"serviceDetail\":\"";
            for(int i=0 ; i<RegisterGosu.service.size() ; i++){
                RegisterGosu.gosuService +=RegisterGosu.service.get(i);
                if(i<(RegisterGosu.service.size()-1)){
                    RegisterGosu.gosuService +=",";
                }
            }
            RegisterGosu.gosuService += "\"}";
//            Log.e("gosuService",RegisterGosu.gosuService);
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
        G.where = "gosu";
        RegisterGosu.gosuBranch=null;
        startActivity(new Intent(this,GosuJoinActivity.class));
        finish();
    }
}