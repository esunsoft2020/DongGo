package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GosuJoinActivity extends AppCompatActivity {


    ImageView[] ivs = new ImageView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join);

        ivs[0] = findViewById(R.id.iv1);
        ivs[1] = findViewById(R.id.iv2);
        ivs[2] = findViewById(R.id.iv3);
        ivs[3] = findViewById(R.id.iv4);
        ivs[4] = findViewById(R.id.iv5);
        ivs[5] = findViewById(R.id.iv6);
        ivs[6] = findViewById(R.id.iv7);
        ivs[7] = findViewById(R.id.iv8);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Glide.with(this).load("http://donggo.dothome.co.kr/icon/lesson.png").into(ivs[0]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/home.png").into(ivs[1]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/event.png").into(ivs[2]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/business.png").into(ivs[3]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/design.png").into(ivs[4]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/health.png").into(ivs[5]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/alba.png").into(ivs[6]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/else.png").into(ivs[7]);

    }

    public void clickClear(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
            startActivity(new Intent(this,MainActivity.class));
            finish();
    }

    public void clickBranch(View view) {
        RegisterGosu.gosuBranch = view.getTag().toString();
        startActivity(new Intent(this,GosuJoin2Activity.class));
        finish();
    }
}