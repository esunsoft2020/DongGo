package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class GosuJoin4Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join4);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode ==RESULT_OK){
                    String addressDoro = data.getExtras().getString("doro");
                    String addressJibeon = data.getExtras().getString("jibun");
                    if(addressDoro!=null && addressJibeon!=null){
                        Bundle bundle = new Bundle();
                        bundle.putString("addressDoro",addressDoro);
                        bundle.putString("addressJibun",addressJibeon);
                        startActivity(new Intent(this,GosuJoin5Activity.class).putExtras(bundle));
                        finish();
                    }
                }
                break;
        }
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
        Intent intent = new Intent(this,GosuJoin3Activity.class);
        startActivity(intent);
        finish();
    }
    public void clickAddress(View view) {
        (GosuJoin4Activity.this).startActivityForResult(new Intent(GosuJoin4Activity.this, WebViewActivity.class),10);
    }
}