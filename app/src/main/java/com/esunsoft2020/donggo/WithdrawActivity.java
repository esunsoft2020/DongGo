package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WithdrawActivity extends AppCompatActivity {

    EditText etExcuse;
    RadioGroup radioGroup;
    String selectedExcuse, excuse;

    RelativeLayout withdrawLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        etExcuse = findViewById(R.id.et_excuse);
        radioGroup = findViewById(R.id.radio);
        withdrawLayout = findViewById(R.id.layout_withdraw);

    }

    @Override
    protected void onResume() {
        super.onResume();

        withdrawLayout.setClickable(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                selectedExcuse = radioButton.getText().toString();
            }
        });

        etExcuse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s!=null){
                    withdrawLayout.setBackgroundResource(R.color.brandColor);
                    withdrawLayout.setClickable(true);
                }else{
                    withdrawLayout.setBackgroundResource(R.color.text_light_gray);
                    withdrawLayout.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //계정 삭제 작업
    public void clickWithdraw(View view) {
        excuse = etExcuse.getText().toString();

        if(excuse==null) {
            Toast.makeText(this, "탈퇴 이유를 작성해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO : withdraw DB에 게시글이 2개씩 등록됨....
        insertWithdrawExcuse();

    }

    //계정 탈퇴 사유 등록 작업
    public void insertWithdrawExcuse(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.insertExcuse(G.email,selectedExcuse,excuse);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json=response.body();
                Log.i("insertWithdrawExcuse",json);
                if(json.equals("success")) executeWithdrawMe();
                else if(json.equals("false")) insertWithdrawExcuse();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("excuse",t.getMessage());
            }
        });
    }

    //계정 탈퇴 작업
    public void executeWithdrawMe(){
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.deleteAccount(G.email,G.name);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.i("withdraw",response.body());
                Toast.makeText(WithdrawActivity.this, "계정 탈퇴되었습니다.", Toast.LENGTH_SHORT).show();
                G.init();
                PreferenceHelper preferenceHelper = new PreferenceHelper(WithdrawActivity.this);
                preferenceHelper.init();
                startActivity(new Intent(WithdrawActivity.this,IntroActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("withdraw",t.getMessage());
            }
        });

    }



    public void clickClear(View view) {
        onBackPressed();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,AccountActivity.class));
        finish();
    }

}