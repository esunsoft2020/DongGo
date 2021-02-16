package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AccountChangeNameActivity extends AppCompatActivity {

    TextInputEditText input;
    TextInputLayout inputLayout;

    RelativeLayout completeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_name);

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
                inputLayout.setHelperText(getResources().getText(R.string.account_text5));
                completeLayout.setClickable(true);
                completeLayout.setBackgroundResource(R.color.brandColor);

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
        G.name = input.getText().toString();
        finish();
    }
}