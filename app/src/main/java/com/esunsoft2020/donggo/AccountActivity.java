package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.user.UserApiClient;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if(checkSelfPermission(permissions[0])== PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions,0);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0 && grantResults[0]==PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "사진 기능에 제한이 있을 수 있습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "사진 업로드 가능", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        items.clear();
        items.add(new TwoStringItem("이름",G.name));
        items.add(new TwoStringItem("이메일",G.email));
        items.add(new TwoStringItem("비밀번호",G.pw));
        items.add(new TwoStringItem("휴대전화 번호",G.changePhoneFormat(G.phone)));

        adapter.notifyDataSetChanged();

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

    void changeImage(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode==RESULT_OK ){
            Uri uri = data.getData();
            if(uri!=null) {
                Glide.with(this).load(uri).into(ivProfile);
                G.profileImgUrl = getRealPathFromUri(uri);

                Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
                RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
                File file = new File(G.profileImgUrl);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("img", file.getName(), requestBody);

                Call<String> call = registerInterface.uploadImage(G.email,part);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body().equals("success")) Toast.makeText(AccountActivity.this, "사진변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        else {
                            Log.e("image",response.body());
                            Toast.makeText(AccountActivity.this, "실패 : 사진 변경을 다시해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("tag",t.getMessage());
                        Toast.makeText(AccountActivity.this, "네트워크가 불안정합니다.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }


    //로그아웃
    public void clickLogout(View view) {
        if(G.iskakaoLogin) {
            UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                @Override
                public Unit invoke(Throwable throwable) {
                    if (throwable != null)
                        Toast.makeText(AccountActivity.this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
                    else{
                        Toast.makeText(AccountActivity.this, "Kakao 로그아웃", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });
        }

        if(!G.iskakaoLogin) Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,IntroActivity.class));

        //초기화
        PreferenceHelper pref = new PreferenceHelper(this);
        G.init();
        pref.init();
        Log.e("tagtag",G.where+"");
        G.where = "intro";
        finish();
    }
    Dialog customDialog;  //커스텀
    //계정 탈퇴
    public void clickWithdraw(View view) {
        customDialog = new Dialog(AccountActivity.this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.withdraw_dialog);

        showCustomDialog();

    }

    public void showCustomDialog(){
        customDialog.show();
        Button clickNo = customDialog.findViewById(R.id.no);
        clickNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        TextView clickYes = customDialog.findViewById(R.id.yes);
        clickYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this,WithdrawActivity.class));
                customDialog.dismiss();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        switch (G.where){
            case "client":
                startActivity(new Intent(this,MainActivity.class));
                break;
            case "Gosu":
                startActivity(new Intent(this,GosuActivity.class));
                break;
        }
        G.where="account";
        finish();
    }

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
}