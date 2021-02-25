package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    ImageView iv, kakaoLogin;
    EditText etEmail,etPw;
    Button emailLoginBtn;
    PreferenceHelper preferenceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv = findViewById(R.id.iv);
        kakaoLogin = findViewById(R.id.btn_kakao_login);
        etEmail = findViewById(R.id.et_email);
        etPw = findViewById(R.id.et_pw);
        emailLoginBtn = findViewById(R.id.email_login_btn);

        preferenceHelper = new PreferenceHelper(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        emailLoginBtn.setClickable(false);
        emailLoginBtn.setBackgroundColor(getResources().getColor(R.color.text_gray));




        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null && etPw!=null){
                    emailLoginBtn.setClickable(true);
                    emailLoginBtn.setBackgroundColor(getResources().getColor(R.color.brandColor));
                }else{
                    emailLoginBtn.setClickable(false);
                    emailLoginBtn.setBackgroundColor(getResources().getColor(R.color.text_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null && etPw!=null){
                    emailLoginBtn.setClickable(true);
                    emailLoginBtn.setBackgroundColor(getResources().getColor(R.color.brandColor));
                }else{
                    emailLoginBtn.setClickable(false);
                    emailLoginBtn.setBackgroundColor(getResources().getColor(R.color.text_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(v.getId()==R.id.et_pw && actionId== EditorInfo.IME_ACTION_DONE){
                    clickEmailLogin(v);
                    return true;
                }else return false;
            }
        });

        Glide.with(this).load("http://donggo.dothome.co.kr/icon/kakao_login/ko/kakao_login_medium_wide.png").into(kakaoLogin);
        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //카카오 계정으로 로그인

                LoginClient.getInstance().loginWithKakaoAccount(LoginActivity.this, new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                        if (throwable != null) {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("login", true);
                            G.iskakaoLogin = true;
                            intent.putExtras(bundle);

                            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                @Override
                                public Unit invoke(User user, Throwable throwable) {

                                    G.name = user.getKakaoAccount().getProfile().getNickname();
                                    G.profileImgUrl = user.getKakaoAccount().getProfile().getProfileImageUrl();
                                    if (user.getKakaoAccount().getEmail() != null)
                                        G.email = user.getKakaoAccount().getEmail();

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
        Toast.makeText(this, "비밀번호 찾기 준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void clickJoin(View view) {
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("login", false);
        startActivity(intent);
        finish();
    }

    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth;

    public void clickGoogleLogin(View view) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser!=null){
            G.email = currentuser.getEmail();
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "준비중입니다.", Toast.LENGTH_SHORT).show();
        }

    }

    boolean successLogin;
    
    public void clickEmailLogin(View view) {
        successLogin =false;
        loginUser();
    }

    //로그인
    private void loginUser(){
        final String email = etEmail.getText().toString();
        final String pw = etPw.getText().toString();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();

        LoginInterface api = retrofit.create(LoginInterface.class);
        Call<String> call = api.getUserLogin(email, pw);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());

                    successLogin = true;
                    String jsonResponse = response.body();
                    parseLoginData(jsonResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t)
            {
                Log.e("Tag", "에러 = " + t.getMessage());
            }
        });
    }

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                saveInfo(response);
                if(G.isGosu) startActivity(new Intent(this,GosuActivity.class));
                else startActivity(new Intent(this,MainActivity.class));
                finish();

//                Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void saveInfo(String response) {
        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    String name = dataobj.getString("name");
                    String email = dataobj.getString("email");
                    String phone = dataobj.getString("phone");
                    String profileImgUrl = dataobj.getString("profileImgUrl");
                    boolean isEmailLogin = G.tinyint2Boolean(dataobj.getString("isEmailLogin"));
                    boolean isKakaoLogin = G.tinyint2Boolean(dataobj.getString("isKakaoLogin"));
                    boolean isGoogleLogin = G.tinyint2Boolean(dataobj.getString("isGoogleLogin"));
                    boolean isGosu = G.tinyint2Boolean(dataobj.getString("isGosu"));

                    G.loginState = true;
                    G.email = email;
                    G.name = name;
                    G.phone = phone;
                    if(profileImgUrl!=null) G.profileImgUrl = profileImgUrl;
                    else G.profileImgUrl = null;

                    G.isEmailLogin = isEmailLogin;
                    G.iskakaoLogin = isKakaoLogin;
                    G.isGoogleLogin = isGoogleLogin;
                    G.isGosu = isGosu;

                    preferenceHelper.putDatas();


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}