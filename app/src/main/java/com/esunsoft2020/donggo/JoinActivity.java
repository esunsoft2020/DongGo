package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JoinActivity extends AppCompatActivity {

    RelativeLayout layoutClient, layoutGosu, joinLayout;
    ImageView ivC,ivG;

    Boolean isGosu = false;

    TextInputLayout etNameLayout,etEmailLayout,etPwLayout,etCPwLayout;
    TextInputEditText etName,etEmail,etPw,etCPw;
    CheckBox checkBox;

    PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etNameLayout = findViewById(R.id.et_name_layout);
        etEmailLayout = findViewById(R.id.et_email_layout);
        etPwLayout = findViewById(R.id.et_pw_layout);
        etCPwLayout = findViewById(R.id.et_check_pw_layout);

        checkBox = findViewById(R.id.box);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPw = findViewById(R.id.et_pw);
        etCPw = findViewById(R.id.et_check_pw);

        joinLayout = findViewById(R.id.join_layout);
        layoutClient = findViewById(R.id.layoutClient);
        layoutGosu = findViewById(R.id.layoutGosu);
        ivC = findViewById(R.id.iv);
        ivG = findViewById(R.id.iv1);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ivC.setSelected(true);

        preferenceHelper = new PreferenceHelper(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        joinLayout.setClickable(false);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s==null) etNameLayout.setError("이름을 입력해주세요.");
                else etNameLayout.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!G.isValidEmail(s.toString())) etEmailLayout.setError("이메일 형식에 맞춰 작성해주세요.");
                else etEmailLayout.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        etPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!G.isValidPw(s.toString())) etPwLayout.setError("영문+숫자로 조합해주세요.");
                else etPwLayout.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        etCPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!etCPw.getText().toString().equals(etPw.getText().toString())) etCPwLayout.setError("비밀번호가 같지 않습니다.");
                else etCPwLayout.setError(null);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //필수 동의사항
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    joinLayout.setClickable(true);
                    joinLayout.setBackgroundResource(R.color.brandColor);
                }else{
                    joinLayout.setClickable(false);
                    joinLayout.setBackgroundResource(R.color.light_light_gray);
                }
            }
        });

        //고객, 고수 선택
        layoutClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivC.setSelected(true);
                ivG.setSelected(false);
                isGosu = false;
            }
        });
        layoutGosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivC.setSelected(false);
                ivG.setSelected(true);
                isGosu=true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //회원가입 완료시 (고객+고수)
    public void clickJoinComplete(View view) {


        if(etNameLayout.getError()!=null) return;
        if(etEmailLayout.getError()!=null) return;
        if(etPwLayout.getError()!=null) return;
        if(etCPwLayout.getError()!=null) return;

        registerMe();

        if(completeRegister){


            G.init(true,etName.getText().toString(),etEmail.getText().toString(),null,null,true,false,false,false);
            finish();

            if(isGosu){
                Intent intent = new Intent(this, GosuJoinActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("login",true);
                startActivity(intent);
            }

        }


    }

    private void registerMe() {
        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String pw = etCPw.getText().toString();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RegisterInterface.REGIST_URL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);
        Call<String> call = api.getUserRegist(name, email, pw);
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Log.e("onSuccess", response.body());

                    String jsonResponse = response.body();
                    try
                    {
                        parseRegData(jsonResponse);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t)
            {
                Log.e("Tag", "에러 = " + t.getMessage());
            }
        });
    }

    boolean completeRegister;

    private void parseRegData(String response) throws JSONException {
        completeRegister = false;

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true"))
        {
            saveInfo(response);
            Toast.makeText(JoinActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
            completeRegister = true;

        } else {
            Toast.makeText(JoinActivity.this, "중복된 이메일이 있습니다.", Toast.LENGTH_SHORT).show();
            completeRegister = false;
        }
    }

    private void saveInfo(String response)
    {
        preferenceHelper.putIsLogin(true);
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true"))
            {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++)
                {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putEmail(dataobj.getString("email"));
                    preferenceHelper.putIsLogin(G.tinyint2Boolean("isEmailLogin"));
                    preferenceHelper.putName(dataobj.getString("name"));
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }





    //뒤로가기
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
        G.init();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}