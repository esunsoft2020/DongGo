package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
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

public class JoinActivity extends AppCompatActivity {

    RelativeLayout layoutClient, layoutGosu, joinLayout;
    ImageView ivC,ivG;

    Boolean isGosu = false;
    EditText etName,etEmail,etPw,etCheckPw;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        checkBox = findViewById(R.id.box);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPw = findViewById(R.id.et_pw);
        etCheckPw = findViewById(R.id.et_pw_check);

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        joinLayout.setClickable(false);


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

//        if (etName ==null || etPw ==null ||etCheckPw==null || etEmail==null){
//            Toast.makeText(this, "빈 칸이 있습니다.", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (!(etPw.getText().toString()).equals(etCheckPw.getText().toString())) {
//            joinLayout.setClickable(false);
//            Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (G.isValidEmail(G.email)){
//            joinLayout.setClickable(false);
//            Toast.makeText(this, "이메일 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (G.isValidPw(G.pw)){
//            joinLayout.setClickable(false);
//            Toast.makeText(this, "비밀번호 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        }

        if(isGosu){
            Intent intent = new Intent(this, GosuJoinActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("login",true);
            startActivity(intent);
        }
        G.init(etName.getText().toString(),etEmail.getText().toString(),etCheckPw.getText().toString(),null,null,true,false,false,false);
        finish();

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
        G.init(null,null,null,null,null,false,false,false,false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}