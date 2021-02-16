package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kakao.sdk.common.util.Utility;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Kakao keyHash
        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("keyHash",keyHash);



    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    //마지막 사용 시 로그인 상태 불러오기
    void loadData(){
        Toast.makeText(this, getResources().getString(R.string.intro_text)+" "+G.name+"님!", Toast.LENGTH_SHORT).show();
    }

    //로그아웃 시 실행화면
    public void clickBtn1(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
//        finish();
    }

    //고객 로그인 실행화면
    public void clickBtn2(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("login",true);
        startActivity(intent);
//        finish();
    }

    //고수 로그인 실행화면
    public void clickBtn3(View view) {
        Intent intent = new Intent(this, GosuActivity.class);
        startActivity(intent);
//        finish();
    }
}