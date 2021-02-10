package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class AllServiceActivity extends AppCompatActivity {

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        title = findViewById(R.id.title);

    }

    @Override
    protected void onResume() {
        super.onResume();
        title.setText(getIntent().getStringExtra("service"));
        //TODO : 가져온 텍스트마다 다르게 화면에 띄우기



        Toast.makeText(this, "준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void clickClear(View view) {
        onBackPressed();
    }
}