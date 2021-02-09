package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void clickSearchPw(View view) {
    }

    public void clickJoin(View view) {
        Intent intent = new Intent(this,JoinActivity.class);
        startActivity(intent);
    }
}