package com.esunsoft2020.donggo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ClickServiceActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv, tvLine1, tvLine2, tvLine3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_service);

        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        
        tvLine1 = findViewById(R.id.tv3);
        tvLine2 = findViewById(R.id.tv4);
        tvLine3 = findViewById(R.id.tv6);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 첫번째 파라미터 : 이미지, 두번째 파라미터 : 서비스 이름
        Bundle bundle = getIntent().getExtras();
        Glide.with(this).load(bundle.getStringArray("service")[0]).into(iv);
        tv.setText(bundle.getStringArray("service")[1]);
        
        tvLine1.setText(bundle.getStringArray("service")[1]+" 관심이 있는데 시작이 어려웠나요?");
        tvLine2.setText(bundle.getStringArray("service")[1]+" 고수를 소개받고 싶다면 요청서를 작성해보세요.");
        tvLine3.setText("동고를 통해 지금 바로 "+bundle.getStringArray("service")[1]+" 시작하세요!");
    }

    public void clickClear(View view) {
        onBackPressed();
    }

    public void clickStart(View view) {
        //요청서 작성 activity
        new AlertDialog.Builder(this).setMessage("준비중입니다.").setPositiveButton("ok",null).show();
    }
}