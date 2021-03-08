package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountChangePhoneActivity extends AppCompatActivity {

    TextInputEditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_phone);

        input = findViewById(R.id.et);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void clickClear(View view) {
        onBackPressed();
    }

    public void clickChange(View view) {
        Toast.makeText(this, "재설정 준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void clickComplete(View view) {
        G.phone = input.getText().toString();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserPhone(G.email,G.phone);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(AccountChangePhoneActivity.this, "번호가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });

    }
}