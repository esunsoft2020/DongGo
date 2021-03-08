package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountChangeEmailActivity extends AppCompatActivity {

    TextInputEditText input;
    TextInputLayout inputLayout;

    RelativeLayout completeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_email);

        input = findViewById(R.id.et);
        inputLayout = findViewById(R.id.et_layout);
        completeLayout = findViewById(R.id.btn_complete);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        completeLayout.setClickable(false);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(G.isValidEmail(s.toString())){
                    input.setError(null);
                    completeLayout.setClickable(true);
                    completeLayout.setBackgroundResource(R.color.brandColor);
                }else{
                    input.setError("이메일 형식에 맞게 작성하세요.");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void clickComplete(View view) {
        if(G.email.equals(input.getText().toString())) {
            Toast.makeText(this, "현재 사용하는 이메일과 같습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        changeEmail();
    }
    private void changeEmail(){
        final String newEmail = input.getText().toString();
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserEmail(G.email,newEmail);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful() && response.body()!=null){
                    Log.e("onSuccess",response.body());
                    String jsonResponse = response.body();
                    try {
                        parseRegData(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("error",t.getMessage());
            }
        });
    }

    boolean completeChangeEmail;

    private void parseRegData(String response) throws JSONException {
        completeChangeEmail= false;

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true"))
        {
            Toast.makeText(AccountChangeEmailActivity.this, "이메일 변경완료", Toast.LENGTH_SHORT).show();
            completeChangeEmail = true;
            G.email = input.getText().toString();
            finish();

        } else {
            Toast.makeText(AccountChangeEmailActivity.this, "중복된 이메일이 있습니다.", Toast.LENGTH_SHORT).show();
            completeChangeEmail = false;
        }
    }

    public void clickClear(View view) {
        onBackPressed();
    }
}