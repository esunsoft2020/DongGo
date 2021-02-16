package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        G.email = input.getText().toString();
        finish();
    }

    public void clickClear(View view) {
        onBackPressed();
    }
}