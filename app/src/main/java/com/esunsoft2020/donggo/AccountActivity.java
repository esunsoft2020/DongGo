package com.esunsoft2020.donggo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.user.UserApiClient;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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
                changeImage();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode ==RESULT_OK){
            Uri uri = data.getData();
            if(uri!=null) {
                G.profileImgUrl = uri.toString();
            }
        }else {
            Bundle bundle = data.getExtras();
            Bitmap bm = (Bitmap)bundle.get("data");
            G.profileImgUrl = bm.toString();
        }

        Glide.with(this).load(G.profileImgUrl).into(ivProfile);

    }

    void changeImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    public void clickLogout(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("logout","logout");
        startActivity(intent);
        finish();

        if(G.iskakaoLogin) {
            UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                @Override
                public Unit invoke(Throwable throwable) {
                    if (throwable != null)
                        Toast.makeText(AccountActivity.this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AccountActivity.this, "Kakao 로그아웃", Toast.LENGTH_SHORT).show();
                    return null;
                }
            });
        }

        //초기화
        G.init(null,null,null,null,null,false,false,false, false);
        if(!G.iskakaoLogin) Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show();
    }

    public void clickWithdraw(View view) {
        Toast.makeText(this, "계정 탈퇴!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = null;

        switch (getIntent().getStringExtra("where")){
            case "client":
                intent = new Intent(this,MainActivity.class);
                intent.putExtra("login",true);
                intent.putExtra("where","account");
                break;
            case "Gosu":
                intent = new Intent(this,GosuActivity.class);
                intent.putExtra("where","account");
                break;
        }
        startActivity(intent);
        finish();
    }
}