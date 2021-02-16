package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class GosuJoin2Activity extends AppCompatActivity {

    ListView listView;
    JoinListViewAdapter adapter;
    ArrayList<String> items = new ArrayList<>();

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
    }


    @Override
    protected void onResume() {
        super.onResume();

        items.clear();
        items.add("학업");
        items.add("외국어");
        items.add("외국어시험");
        items.add("공예");
        items.add("미술");
        items.add("음악이론/보컬");




    }


    public void clickNext(View view) {
        Intent intent = new Intent(this,GosuJoin3Activity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this,GosuJoinActivity.class);
        startActivity(intent);
        finish();
    }

}