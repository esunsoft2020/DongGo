package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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
        onBackPressed();
    }
}