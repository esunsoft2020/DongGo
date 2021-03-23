package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class SelectAreaActivity extends AppCompatActivity {

    SelectAreaAdapter adapter;
    RecyclerView recyclerView;
    String[] area;
    String[][] detailArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);

        recyclerView = findViewById(R.id.recycler);
        area = getResources().getStringArray(R.array.area);
        detailArea = new String[][]{
                getResources().getStringArray(R.array.area1),
                getResources().getStringArray(R.array.area2),
                getResources().getStringArray(R.array.area3),
                getResources().getStringArray(R.array.area4),
                getResources().getStringArray(R.array.area5),
                getResources().getStringArray(R.array.area6),
                getResources().getStringArray(R.array.area7),
                getResources().getStringArray(R.array.area8),
                getResources().getStringArray(R.array.area9),
                getResources().getStringArray(R.array.area10),
                getResources().getStringArray(R.array.area11),
                getResources().getStringArray(R.array.area12),
                getResources().getStringArray(R.array.area13),
                getResources().getStringArray(R.array.area14),
                getResources().getStringArray(R.array.area15),
                getResources().getStringArray(R.array.area16),
                getResources().getStringArray(R.array.area17),
                getResources().getStringArray(R.array.area18)};
        adapter = new SelectAreaAdapter(this,area,detailArea);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();








    }

    public void clickClear(View view) {
        onBackPressed();
    }
}