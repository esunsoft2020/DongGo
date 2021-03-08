package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ClickServiceActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_service);

        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        Glide.with(this).load(bundle.getStringArray("service")[0]).into(iv);
        tv.setText(bundle.getStringArray("service")[1]);
    }

    public void clickClear(View view) {
        onBackPressed();
    }
}