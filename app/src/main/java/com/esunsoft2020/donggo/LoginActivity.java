package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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
    SignInButton googleLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv = findViewById(R.id.iv);
        kakaoLogin = findViewById(R.id.btn_kakao_login);
        etEmail = findViewById(R.id.et_email);
        etPw = findViewById(R.id.et_pw);
        emailLoginBtn = findViewById(R.id.email_login_btn);
        googleLoginBtn = findViewById(R.id.google_login);
        firebaseAuth = FirebaseAuth.getInstance();

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
                new AlertDialog.Builder(LoginActivity.this).setMessage("카카오 계정 로그인을 권장하지 않습니다.\n카카오 계정으로 로그인하려면 OK를 눌러주세요.").setPositiveButton("NO",null)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clickKakaoLogin();
                            }
                        }).show();
            }});

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Google 로그인을 앱에 통합
        // GoogleSignInOptions 개체를 구성할 때 requestIdToken을 호출
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        googleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGoogleLogin();
            }
        });


    }//onResume method..

    public void clickSearchPw(View view) {
        Toast.makeText(this, "비밀번호 찾기 준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void clickJoin(View view) {
        startActivity(new Intent(this,JoinActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        G.loginState=false;
        startActivity(new Intent(this,MainActivity.class));
        finish();
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
//                    Log.e("onSuccess", response.body());

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

            }else{
                Toast.makeText(this, "이메일 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void saveInfo(String response) {
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

                    PreferenceHelper helper = new PreferenceHelper(LoginActivity.this);
                    helper.putDatas();

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //TODO : 가입, 확인 과정 필요
    //카카오 계정으로 로그인
    public void clickKakaoLogin() {

        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                if(throwable!=null) Toast.makeText(LoginActivity.this, "카카오를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
                else if(oAuthToken !=null){
                    Log.e("kakao","카카오 불러오기 성공");

                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {

                            if(user!=null){
                                long id = user.getId();

                                G.name = user.getKakaoAccount().getProfile().getNickname();
                                G.profileImgUrl = user.getKakaoAccount().getProfile().getProfileImageUrl();
                                G.email = user.getKakaoAccount().getEmail();
                                if(G.email.equals(null)) Toast.makeText(LoginActivity.this, "이메일 계정이 없습니다.", Toast.LENGTH_SHORT).show();
                                G.isEmailLogin = false;
                                G.loginState = true;
                                G.iskakaoLogin = true;

                                startActivity(new Intent(LoginActivity.this,IntroActivity.class));
                                PreferenceHelper helper = new PreferenceHelper(LoginActivity.this);
                                helper.putDatas();

                                finish();

                            }else
                                Toast.makeText(LoginActivity.this, "사용자 정보 요청 실패", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                    });
                }
                return null;
            }
        });
    }


    // 구글로그인 result 상수
    private static final int RC_SIGN_IN = 900;
    // 구글api클라이언트
    private GoogleSignInClient googleSignInClient;
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;
    public void clickGoogleLogin() {
        startActivityForResult(new Intent(googleSignInClient.getSignInIntent()), RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN ) {
           GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
           if(result.isSuccess()){
               GoogleSignInAccount account = result.getSignInAccount();

               Log.e("Google",account.getEmail()+":"+account.getDisplayName()+":"+account.getPhotoUrl());
               firebaseAuthWithGoogle(account);
           }else Snackbar.make(this,googleLoginBtn,"Google Sign In failed",Snackbar.LENGTH_SHORT);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(LoginActivity.this, "구글 로그인 성공", Toast.LENGTH_SHORT).show();
                        } else {
                            // 로그인 실패
                            Toast.makeText(LoginActivity.this, "오류", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}