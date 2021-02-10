package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class GosuJoin5Activity extends AppCompatActivity {

    TextView dis1,dis2,dis3,dis4,dis5,dis6,dis7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join5);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dis1 = findViewById(R.id.dis1);
        dis2 = findViewById(R.id.dis2);
        dis3 = findViewById(R.id.dis3);
        dis4 = findViewById(R.id.dis4);
        dis5 = findViewById(R.id.dis5);
        dis6 = findViewById(R.id.dis6);
        dis7 = findViewById(R.id.dis7);

        dis1.setSelected(true);

    }

    @Override
    protected void onResume() {
        super.onResume();




    }


    public void clickDistance(View view) {
        switch (view.getId()){
            case R.id.dis1:
                dis1.setSelected(true);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis2:
                dis1.setSelected(false);
                dis2.setSelected(true);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis3:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(true);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis4:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(true);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis5:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(true);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis6:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(true);
                dis7.setSelected(false);
                break;
            case R.id.dis7:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(true);
                break;
        }


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
        Intent intent = new Intent(this,GosuJoin4Activity.class);
        startActivity(intent);
        finish();
    }

    public void clickAddressChange(View view) {
        onBackPressed();
    }

    public void clickNext(View view) {
        Intent intent = new Intent(this,GosuJoin6Activity.class);
        startActivity(intent);
        finish();
    }
}