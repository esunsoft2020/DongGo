package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kakao.sdk.common.util.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Retrofit;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //Kakao keyHash
//        String keyHash = Utility.INSTANCE.getKeyHash(this);
//        Log.i("keyHash",keyHash);

        //FCM
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d("TOKEN", token);
                    }
                });



    }

    @Override
    protected void onResume() {
        super.onResume();
        G.where = "intro";
        loadData();
    }

    //마지막 사용 시 로그인 상태 불러오기
    void loadData(){
        PreferenceHelper preferenceHelper = new PreferenceHelper(this);
        preferenceHelper.getDatas();

        if(G.loginState) {
            if(G.name!=null) Toast.makeText(this, getResources().getString(R.string.intro_text)+" "+G.name+"님!", Toast.LENGTH_SHORT).show();

            if(G.isGosu) startActivity(new Intent(this,GosuActivity.class));
            else startActivity(new Intent(this,MainActivity.class));
            Log.e("getData",G.email+":"+G.name+":"+G.phone+":"+G.isEmailLogin+":"+G.loginState+":"+G.isGoogleLogin+":"+G.isGosu);
            finish();

        }else {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    //로그아웃 시 실행화면
    public void clickBtn1(View view) {
        G.loginState=false;
        startActivity(new Intent(this,MainActivity.class));
//        finish();
    }

    //고객 로그인 실행화면
    public void clickBtn2(View view) {
        G.loginState = true;
        startActivity(new Intent(this,MainActivity.class));
//        finish();
    }

    //고수 로그인 실행화면
    public void clickBtn3(View view) {
        G.loginState=true;
        startActivity(new Intent(this,GosuActivity.class));
//        finish();
    }


    @Override
    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("종료하시겠습니까?");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {

                //저장하고 종료
//                SharedPreferences pref = getSharedPreferences("userData",MODE_PRIVATE);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("name",G.name);
//                editor.putString("email",G.email);
//                editor.putString("phone",G.phone);
//                editor.putString("image",G.profileImgUrl);
//                editor.putString("pw",G.pw);
//                editor.putBoolean("isEmailLogin",G.isEmailLogin);
//                editor.putBoolean("isKakaoLogin",G.iskakaoLogin);
//                editor.putBoolean("isFacebookLogin",G.isGoogleLogin);
//                editor.putBoolean("isGosu",G.isGosu);
//
//                editor.commit();
//                finish();
//            }
//        });
//        builder.setNegativeButton("No",null);
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }
}