package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kakao.sdk.common.util.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IntroActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Kakao keyHash
        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("keyHash",keyHash);

        pref = getSharedPreferences("userData",MODE_PRIVATE);




    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    //마지막 사용 시 로그인 상태 불러오기
    void loadData(){

        G.name = pref.getString("name",null);
        G.email = pref.getString("email",null);
        G.phone = pref.getString("phone",null);
        G.profileImgUrl = pref.getString("image",null);
        G.pw = pref.getString("pw",null);
        G.isEmailLogin = pref.getBoolean("isEmailLogin",false);
        G.iskakaoLogin = pref.getBoolean("isKakaoLogin",false);
        G.isFacebookLogin = pref.getBoolean("isFacebookLogin",false);
        G.isGosu = pref.getBoolean("isGosu",false);

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


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("종료하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //저장하고 종료
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name",G.name);
                editor.putString("email",G.email);
                editor.putString("phone",G.phone);
                editor.putString("image",G.profileImgUrl);
                editor.putString("pw",G.pw);
                editor.putBoolean("isEmailLogin",G.isEmailLogin);
                editor.putBoolean("isKakaoLogin",G.iskakaoLogin);
                editor.putBoolean("isFacebookLogin",G.isFacebookLogin);
                editor.putBoolean("isGosu",G.isGosu);

                editor.commit();

            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog dialog = builder.create();
        dialog.show();
        finish();
    }
}