package com.esunsoft2020.donggo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

public class Tab4GosuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab4gosu,container,false);
    }

    CircleImageView civ;
    ImageView upload,iv1;

    ViewPager pager;
    ArrayList<TwoStringItem> items = new ArrayList<>();
    PageTab4GosuAdapter adapter;

    ImageView[] ivs = new ImageView[8];

    TextView preview, changeName,changeAddress,changeOneLine,changeRadius, gosuService;
    RelativeLayout APLayout, requestReviewLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        civ=view.findViewById(R.id.civ);
        iv1 = view.findViewById(R.id.iv1);
        upload = view.findViewById(R.id.upload);

        pager = view.findViewById(R.id.pager);
        adapter = new PageTab4GosuAdapter(getActivity(),items);
        pager.setAdapter(adapter);

        for(int i=0 ; i<ivs.length;i++){
            ivs[i] = view.findViewById(R.id.iv_1+i);
        }

        preview = view.findViewById(R.id.preview);
        APLayout = view.findViewById(R.id.activity_parse_layout);

        changeName = view.findViewById(R.id.change_name);
        changeAddress = view.findViewById(R.id.change_address);
        changeOneLine = view.findViewById(R.id.change_one_line);
        changeRadius = view.findViewById(R.id.change_radius);

        requestReviewLayout = view.findViewById(R.id.btn_request_review);
        gosuService = view.findViewById(R.id.service);

    }

    @Override
    public void onResume() {
        super.onResume();

        for(ImageView iv : ivs){
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIvs(v);
                }
            });
        }

        items.clear();
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text7_1),getResources().getString(R.string.tab4_gosu_text7_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text8_1),getResources().getString(R.string.tab4_gosu_text8_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text9_1),getResources().getString(R.string.tab4_gosu_text9_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text10_1),getResources().getString(R.string.tab4_gosu_text10_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text11_1),getResources().getString(R.string.tab4_gosu_text11_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text12_1),getResources().getString(R.string.tab4_gosu_text12_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text13_1),getResources().getString(R.string.tab4_gosu_text13_2)));
        items.add(new TwoStringItem(getResources().getString(R.string.tab4_gosu_text14_1),getResources().getString(R.string.tab4_gosu_text14_2)));

        adapter.notifyDataSetChanged();

        //개인 사진 iv(변경가능)
        if(G.profileImgUrl!=null)Glide.with(this).load(G.profileImgUrl).into(civ);
        else Glide.with(this).load("http://donggo.dothome.co.kr/icon/mypage.png").into(civ);

        //변경x
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/shot.png").into(upload);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/chart.png").into(iv1);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMediaUpload();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        APLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("활동 분석 준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+"준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+"준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+"준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeOneLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+"준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        requestReviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        gosuService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode==RESULT_OK ){
            Uri uri = data.getData();
            if(uri!=null) {
                Glide.with(this).load(uri).into(civ);
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
                        if(response.body().equals("success")) Toast.makeText(getActivity(), "사진변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        else {
                            Log.e("image",response.body());
                            Toast.makeText(getActivity(), "실패 : 사진 변경을 다시 해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("image",t.getMessage());
                        Toast.makeText(getActivity(), "네트워크가 불안정합니다.", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }


    }


    void setMediaUpload(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }

    void clickIvs(View v){

        int index;

        if(v.getTag().equals("iv1")){
            pager.setCurrentItem(0,true);
        }else if(v.getTag().equals("iv2")){
            pager.setCurrentItem(1,true);
        }else if(v.getTag().equals("iv3")){
            pager.setCurrentItem(2,true);
        }else if(v.getTag().equals("iv4")){
            pager.setCurrentItem(3,true);
        }else if(v.getTag().equals("iv5")){
            pager.setCurrentItem(4,true);
        }else if(v.getTag().equals("iv6")){
            pager.setCurrentItem(5,true);
        }else if(v.getTag().equals("iv7")){
            pager.setCurrentItem(6,true);
        }else if(v.getTag().equals("iv8")){
            pager.setCurrentItem(7,true);
        }
    }

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(getActivity(), uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
}
