package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GosuJoinActivity extends AppCompatActivity {


    ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/resson.png").into(iv1);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/home.png").into(iv2);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/event.png").into(iv3);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/business.png").into(iv4);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/design.png").into(iv5);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/health.png").into(iv6);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/alba.png").into(iv7);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/else.png").into(iv8);

    }

    public void clickClear(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(getIntent().getStringExtra("where").equals("Client")){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
            finish();

        }else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void clickBranch(View view) {
        Intent intent = new Intent(this,GosuJoin2Activity.class);
        startActivity(intent);
        finish();
    }
}