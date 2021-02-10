package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class JoinActivity extends AppCompatActivity {

    RelativeLayout layoutClient, layoutGosu;
    ImageView ivC,ivG;

    Boolean isGosu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        layoutClient = findViewById(R.id.layoutClient);
        layoutGosu = findViewById(R.id.layoutGosu);
        ivC = findViewById(R.id.iv);
        ivG = findViewById(R.id.iv1);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ivC.setSelected(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        layoutClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivC.setSelected(true);
                ivG.setSelected(false);
                isGosu = false;
            }
        });

        layoutGosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivC.setSelected(false);
                ivG.setSelected(true);
                isGosu=true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //회원가입 완료시 (고객+고수)
    public void clickJoinComplete(View view) {

        if(isGosu){
            Intent intent = new Intent(this, GosuJoinActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
        }
        finish();
    }



    //뒤로가기
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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}