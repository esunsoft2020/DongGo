package com.esunsoft2020.donggo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountActivity extends AppCompatActivity {

    CircleImageView ivProfile;
    ImageView ivCamera;

    RecyclerView recyclerView;
    AccountRecyclerAdapter adapter;
    ArrayList<TwoStringItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        ivProfile = findViewById(R.id.iv_profile);
        ivCamera = findViewById(R.id.iv_camera);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recycler);
        adapter = new AccountRecyclerAdapter(this,items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

        items.clear();
        items.add(new TwoStringItem("이름",G.name));
        items.add(new TwoStringItem("이메일",G.email));
        items.add(new TwoStringItem("비밀번호","*****"));
        items.add(new TwoStringItem("휴대전화 번호",G.changePhoneFormat(G.phone)));


        if(G.profileImgUrl!=null)Glide.with(this).load(G.profileImgUrl).into(ivProfile);
        else Glide.with(this).load("http://donggo.dothome.co.kr/icon/account/pic.png").into(ivProfile);

        Glide.with(this).load("http://donggo.dothome.co.kr/icon/account/camera.png").into(ivCamera);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountActivity.this, "사진바꾸기", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void clickLogout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void clickWithdraw(View view) {
        Toast.makeText(this, "계정 탈퇴!", Toast.LENGTH_SHORT).show();
    }
}