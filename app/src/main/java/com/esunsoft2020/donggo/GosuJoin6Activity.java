package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.HelperReference;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
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
    RelativeLayout complete;
    String mf;

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

        complete = findViewById(R.id.complete);

    }

    @Override
    protected void onResume() {
        super.onResume();
        send.setClickable(false);
        send.setBackgroundColor(getResources().getColor(R.color.text_gray));
        confirm.setClickable(false);
        confirm.setBackgroundColor(getResources().getColor(R.color.text_gray));
        complete.setClickable(false);
        complete.setBackgroundResource(R.color.text_light_gray);

        phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null) {
                    send.setClickable(true);
                    send.setBackground(getResources().getDrawable(R.drawable.et));
                    complete.setClickable(true);
                    complete.setBackgroundResource(R.color.brandColor);
                } else {
                    send.setClickable(false);
                    send.setBackgroundColor(getResources().getColor(R.color.text_gray));
                    complete.setClickable(false);
                    complete.setBackgroundResource(R.color.text_light_gray);
                }
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
                if(s!=null) {
                    confirm.setClickable(false);
                    confirm.setBackground(getResources().getDrawable(R.drawable.et));
                } else{
                    confirm.setClickable(true);
                    confirm.setBackgroundColor(getResources().getColor(R.color.text_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



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
        startActivity(new Intent(this,GosuJoin4Activity.class));
        finish();
    }


    public void clickMF(View view) {
        switch (view.getId()){
            case R.id.tvM:
                tvM.setSelected(true);
                tvF.setSelected(false);
                mf = "남";
                break;
            case R.id.tvF:
                tvM.setSelected(false);
                tvF.setSelected(true);
                mf = "여";
                break;
        }
    }

//    FirebaseAuth mAuth;
//    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    //전화번호 인증
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void clickSend(View view) {
        String phone = G.phoneReplace(phoneNum.getText().toString()).replace("N","");
        Log.e("phone",phone+"");

//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                Toast.makeText(GosuJoin6Activity.this, "전송 성공!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//                Toast.makeText(GosuJoin6Activity.this, "전송 실패", Toast.LENGTH_SHORT).show();
//
//            }
//        };
        //TODO : Firebase 로 전화번호 인증 작업 추가하기 or https://zladnrms.tistory.com/60 자바스크립트 이용

        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("전송 준비 중입니다.").setPositiveButton("OK",null).show();

        confirm.setClickable(true);
        confirm.setBackground(getResources().getDrawable(R.drawable.et));
        confirmNum.setBackgroundTintList(null);
        RegisterGosu.phone = phone;

    }

    boolean phoneConfirm = false;
    //인증번호 체크
    public void clickPhoneConfirm(View view) {
        if(phoneConfirm) {
            Snackbar.make(this,view,"이미 인증되었습니다.",Snackbar.LENGTH_SHORT).show();
            return;
        }

        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("인증 준비 중입니다.\n번호 고정은 OK를 눌러주세요.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                phoneNum.setClickable(false);
                phoneNum.setFocusable(false);
                phoneNum.setBackgroundResource(R.color.light_light_gray);

                confirmNum.setClickable(false);
                confirmNum.setFocusable(false);
                confirmNum.setBackgroundResource(R.color.light_light_gray);
                phoneConfirm = true;
            }
        }).setNegativeButton("NO",null).show();
    }

    //고수로 가입완료
    public void clickComplete(View view) {
        RegisterGosu.mf = mf;
        G.phone = RegisterGosu.phone;
        G.isGosu = true;

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserisGosu(G.email, G.boolean2String(G.isGosu));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (json.equals("success")) {
                    registerGosu();
                    Log.e("isGosu", json);
                } else Log.e("isGosu", json);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("isGosu", t.getMessage());
            }
        });
    }

    //DB 에 고수 정보 등록
    public void registerGosu(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserRegistGosuInfo(G.email,RegisterGosu.gosuBranch,RegisterGosu.gosuService,RegisterGosu.serviceDetail,RegisterGosu.address,RegisterGosu.radius,RegisterGosu.mf,RegisterGosu.phone);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String j = response.body();
                if (j.equals("success")){
                    Log.e("registerGosu",j);
                    PreferenceHelper helper = new PreferenceHelper(GosuJoin6Activity.this);
                    helper.putDatas();
                    finish();
                }else Log.e("registerGosu",j);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("registerGosu",t.getMessage());
            }
        });

    }


}