package com.esunsoft2020.donggo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
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
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    TextView tvName, tvLine, tvArea, tvRadius;

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

        tvName = view.findViewById(R.id.tv_name);
        tvLine = view.findViewById(R.id.tv_line);
        tvArea = view.findViewById(R.id.tv_area);
        tvRadius = view.findViewById(R.id.tv_dis);



    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
        tvName.setText(G.name);

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
        if(G.profileImgUrl==null) Glide.with(this).load("http://donggo.dothome.co.kr/icon/account/pic.png").into(civ);
        else Glide.with(this).load(G.profileImgUrl).into(civ);

        //변경x(고정이미지)
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
                new AlertDialog.Builder(getActivity()).setMessage(vv+" 준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+" 준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        changeRadius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vv = ((TextView)v).getText().toString();
                new AlertDialog.Builder(getActivity()).setMessage(vv+" 준비중입니다.").setPositiveButton("OK",null).show();
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


    //본인 고수 정보 가져오기
    void loadData(){
        LoginInterface loginInterface = RetrofitHelper.getRetrofitInstance().create(LoginInterface.class);
        Call<String> call = loginInterface.getGosuInfo(G.email,G.name);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    String jsonResponse = response.body();
                    parseLoginData(jsonResponse);
                }
                Log.e("Tag", "error = " +response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Tag", "에러 = " + t.getMessage());
                Snackbar.make(getActivity(),pager,"Gosu정보를 불러오지 못했습니다.",Snackbar.LENGTH_SHORT).setAction("다시 시도", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadData();
                    }
                }).show();
            }
        });

    }

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                saveInfo(response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void saveInfo(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    String gosuBranch = dataobj.getString("gosuBranch");
                    String gosuService = dataobj.getString("gosuService");
                    String serviceDetail = dataobj.getString("serviceDetail");
                    String address= dataobj.getString("address");
                    String radius= dataobj.getString("radius");
                    String mf= dataobj.getString("mf");

                    RegisterGosu.gosuBranch = gosuBranch;
                    RegisterGosu.gosuService = gosuService;
                    RegisterGosu.serviceDetail = serviceDetail;
                    RegisterGosu.address = address;
                    RegisterGosu.radius = radius;
                    RegisterGosu.mf = mf;

                    gosuBranch = gosuBranch.replace("{\"gosuService\":\"","").replace("\"}","");
                    serviceDetail = serviceDetail.replace("{\"serviceDetail\":\"","").replace("\"}","");

                    tvLine.setText(G.name+"고수의 "+gosuBranch+", "+serviceDetail + " 서비스");
                    tvArea.setText(RegisterGosu.address);
                    tvRadius.setText(RegisterGosu.radius);

                    mapPoint(RegisterGosu.address);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    double lat,lng;
    LatLng latLng;
    GoogleMap gMap;

    void mapPoint(String doro){
        //주소 -> 좌표로 변환 (지오코딩)
        Geocoder geocoder = new Geocoder(getActivity(), Locale.KOREA);
        try {
            List<Address> addresses = geocoder.getFromLocationName(doro, 1);

            //구글지도에 보여주기 위해 검색된 위도,경도 중 1개를 멤버변수로 대입
            lat = addresses.get(0).getLatitude();
            lng = addresses.get(0).getLongitude();
            latLng = new LatLng(lat, lng);

        } catch (IOException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);

        //비동기 방식(별도 Thread 방식)으로 구글 서버에서 맵의 데이터를 읽어오도록..
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //파라미터로 전달된 GoogleMap이 지도 객체임!
                gMap = googleMap;   //멤버변수에 대입하면 이 객체를 다른 메소드에소 사용 가능해서 권장
                //지도의 특정좌표로 이동 및 줌인
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));   //줌:1~25

                onAddMarker();

                UiSettings settings = gMap.getUiSettings();
                settings.setZoomControlsEnabled(true);
                //내 위치 탐색을 지도 라이브러리에서 제공해줌
                settings.setMyLocationButtonEnabled(true);
            }
        });
    }

    MarkerOptions marker;
    CircleOptions circleOptions;

    public void onAddMarker(){
        //마커 추가하기
        marker = new MarkerOptions().position(latLng).anchor(0.5f, 1.0f).title(RegisterGosu.address);
        circleOptions = new CircleOptions().center(latLng).strokeWidth(0.5f).fillColor(getResources().getColor(R.color.mapCircle));
        gMap.addMarker(marker);
        gMap.addCircle(circleOptions);
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
