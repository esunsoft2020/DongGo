package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class GosuJoin6Activity extends AppCompatActivity {

    TextView tvM,tvF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join6);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvM = findViewById(R.id.tvM);
        tvF = findViewById(R.id.tvF);

        tvM.setSelected(true);

    }

    @Override
    protected void onResume() {
        super.onResume();




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
        Intent intent = new Intent(this,GosuJoin5Activity.class);
        startActivity(intent);
        finish();
    }

    public void clickComplete(View view) {
        Intent intent = new Intent(this,GosuActivity.class);
        startActivity(intent);
        finish();

    }

    public void clickMF(View view) {
        switch (view.getId()){
            case R.id.tvM:
                tvM.setSelected(true);
                tvF.setSelected(false);
                break;
            case R.id.tvF:
                tvM.setSelected(false);
                tvF.setSelected(true);
                break;
        }
    }
}