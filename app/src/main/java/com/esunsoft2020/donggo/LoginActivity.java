package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    ImageView iv,kakaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv = findViewById(R.id.iv);
        kakaoLogin = findViewById(R.id.btn_kakao_login);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Glide.with(this).load("http://donggo.dothome.co.kr/icon/kakao_login/ko/kakao_login_medium_wide.png").into(kakaoLogin);
        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카카오 계정으로 로그인

                LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                        if(throwable != null){
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("login",true);
                            G.iskakaoLogin = true;
                            intent.putExtras(bundle);

                            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                @Override
                                public Unit invoke(User user, Throwable throwable) {

                                    G.name = user.getKakaoAccount().getProfile().getNickname();
                                    G.profileImgUrl = user.getKakaoAccount().getProfile().getProfileImageUrl();
                                    if(user.getKakaoAccount().getEmail()!=null) G.email = user.getKakaoAccount().getEmail();

                                    return null;
                                }
                            });

                            startActivity(intent);
                            finish();
                        }



                        return null;
                    }
                });


            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void clickSearchPw(View view) {
    }

    public void clickJoin(View view) {
        Intent intent = new Intent(this,JoinActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("login",false);
        startActivity(intent);
        finish();
    }
}