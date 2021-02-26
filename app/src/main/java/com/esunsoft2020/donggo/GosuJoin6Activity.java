package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GosuJoin6Activity extends AppCompatActivity {

    TextView tvM,tvF, send, confirm;

    EditText phoneNum, confirmNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join6);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvM = findViewById(R.id.tvM);
        tvF = findViewById(R.id.tvF);

        tvM.setSelected(true);

        phoneNum = findViewById(R.id.et_phone);
        confirmNum = findViewById(R.id.et_confirmNumber);

        send = findViewById(R.id.send_btn);
        confirm = findViewById(R.id.confirm_btn);

    }

    @Override
    protected void onResume() {
        super.onResume();
        chooseClickable(send,false);
        chooseClickable(confirm,false);

        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null) chooseClickable(send,true);
                else chooseClickable(send,false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        confirmNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null) chooseClickable(confirm,true);
                else chooseClickable(confirm,false);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



    }

    public void chooseClickable(TextView view, boolean tOf){
        if(!tOf) {
            view.setClickable(false);
            view.setBackgroundColor(getResources().getColor(R.color.text_gray));
        }else {
            view.setClickable(true);
            view.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

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
        Intent intent = new Intent(this,GosuJoin5Activity.class);
        startActivity(intent);
        finish();
    }


    public void clickMF(View view) {
        switch (view.getId()){
            case R.id.tvM:
                tvM.setSelected(true);
                tvF.setSelected(false);
                break;
            case R.id.tvF:
                tvM.setSelected(false);
                tvF.setSelected(true);
                break;
        }
    }


    //전화번호 인증
    public void clickSend(View view) {
        String phone = phoneNum.getText().toString();
        //TODO : Firebase 로 전화번호 인증 작업 추가하기
        Snackbar.make(this,view,"준비중입니다.",Snackbar.LENGTH_SHORT).show();
    }

    //인증번호 체크
    public void clickPhoneConfirm(View view) {
        Snackbar.make(this,view,"인증 준비 중입니다.",Snackbar.LENGTH_SHORT).show();
    }

    //고수로 가입완료
    public void clickComplete(View view) {
        Intent intent = new Intent(this,GosuActivity.class);
        startActivity(intent);

        G.isGosu=true;
        PreferenceHelper helper = new PreferenceHelper(this);
        helper.putIsLogin(G.isGosu);

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserisGosu(G.email,G.boolean2String(G.isGosu));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("success")) {
                    Toast.makeText(GosuJoin6Activity.this, "고수 가입 완료", Toast.LENGTH_SHORT).show();
                    registerGosu();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(GosuJoin6Activity.this, "가입 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //DB 에 고수 정보 등록
    void registerGosu(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserRegistGosuInfo(G.email,RegisterGosu.gosuBranch,RegisterGosu.gosuService,RegisterGosu.serviceDetail,RegisterGosu.address,RegisterGosu.radius,RegisterGosu.mf,RegisterGosu.phone);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("success")){
                    Toast.makeText(GosuJoin6Activity.this, "성공!", Toast.LENGTH_SHORT).show();
                }else registerGosu();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("registerGosu",t.getMessage());
            }
        });
    }


}