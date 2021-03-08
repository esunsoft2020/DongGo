package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountChangePwActivity extends AppCompatActivity {

    RelativeLayout completeLayout;
    TextInputEditText inputCurrent, inputNew, inputCheckNew;
    TextInputLayout layout1,layout2,layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_pw);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        completeLayout = findViewById(R.id.btn_complete);

        inputCurrent = findViewById(R.id.et_current_pw);
        inputNew = findViewById(R.id.et_new_pw);
        inputCheckNew = findViewById(R.id.et_check_new_pw);

        layout1 = findViewById(R.id.et_layout1);
        layout2 = findViewById(R.id.et_layout2);
        layout3 = findViewById(R.id.et_layout3);




    }

    @Override
    protected void onResume() {
        super.onResume();

        completeLayout.setClickable(false);

        inputCurrent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout1.setHelperText(getResources().getText(R.string.account_text9));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        inputNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                layout2.setHelperText(getResources().getText(R.string.account_text11));
                if(!G.isValidPw(s.toString())) inputNew.setError("비밀번호 형식이 잘못되었습니다. 영문+숫자 8자리 이상 입력해주세요.");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCheckNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                layout3.setHelperText(getResources().getText(R.string.account_text13));
                if((s.toString()).equals(inputNew.getText().toString())){
                    completeLayout.setClickable(true);
                    completeLayout.setBackgroundResource(R.color.brandColor);
                }else{
                    inputCheckNew.setError("비밀번호가 일치하지 않습니다.");
                    completeLayout.setBackgroundResource(R.color.text_light_gray);
                    completeLayout.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void clickClear(View view) {
        onBackPressed();
    }

    public void clickComplete(View view) {

        if (inputNew.getError()!=null) return;
        if(inputCurrent.equals(null)) inputCurrent.setError("기존 비밀번호를 입력해주세요.");
        else {
            //비밀번호 따로 저장하지 않기
            String pw = inputCheckNew.getText().toString();

            Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
            RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
//            Toast.makeText(this, G.email+" : "+inputCurrent.getText().toString()+ " : "+inputCheckNew.getText().toString(), Toast.LENGTH_SHORT).show();
            Call<String> call = registerInterface.getUserPw(G.email,inputCurrent.getText().toString(),inputCheckNew.getText().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(AccountChangePwActivity.this, "변경 완료", Toast.LENGTH_SHORT).show();
                    Log.e("tag",response.body());
                    onBackPressed();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(AccountChangePwActivity.this, "실패", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}