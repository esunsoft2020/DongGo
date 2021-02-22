package com.esunsoft2020.donggo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
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

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode==RESULT_OK ){
            Uri uri = data.getData();
            if(uri!=null) {
                G.profileImgUrl = getRealPathFromUri(uri);
            }
        }else {
            Bundle bundle = data.getExtras();
            Bitmap bm = (Bitmap)bundle.get("data");
            G.profileImgUrl = G.BitMapToString(bm);
        }

        Glide.with(this).load(G.profileImgUrl).into(civ);
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
        Call<String> call = registerInterface.getUserProfileImgUrl(G.email,G.profileImgUrl);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getActivity(), "변경 완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(), "변경 실패", Toast.LENGTH_SHORT).show();
            }
        });

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
