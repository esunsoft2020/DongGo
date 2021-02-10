package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;

public class GosuJoin5Activity extends AppCompatActivity {

    TextView dis1,dis2,dis3,dis4,dis5,dis6,dis7;

    GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu_join5);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dis1 = findViewById(R.id.dis1);
        dis2 = findViewById(R.id.dis2);
        dis3 = findViewById(R.id.dis3);
        dis4 = findViewById(R.id.dis4);
        dis5 = findViewById(R.id.dis5);
        dis6 = findViewById(R.id.dis6);
        dis7 = findViewById(R.id.dis7);

        dis1.setSelected(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        //비동기 방식(별도 Thread 방식)으로 구글 서버에서 맵의 데이터를 읽어오도록..
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //파라미터로 전달된 GoogleMap이 지도 객체임!
                gMap = googleMap;   //멤버변수에 대입하면 이 객체를 다른 메소드에소 사용 가능해서 권장

                //지도의 특정좌표로 이동 및 줌인
                LatLng seoul = new LatLng(37.56282087, 127.035192);
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15));   //줌:1~25




                UiSettings settings = gMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                //내 위치 탐색을 지도 라이브러리에서 제공해줌
                settings.setMyLocationButtonEnabled(true);


            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();





    }


    public void clickDistance(View view) {
        switch (view.getId()){
            case R.id.dis1:
                dis1.setSelected(true);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis2:
                dis1.setSelected(false);
                dis2.setSelected(true);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis3:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(true);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis4:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(true);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis5:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(true);
                dis6.setSelected(false);
                dis7.setSelected(false);
                break;
            case R.id.dis6:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(true);
                dis7.setSelected(false);
                break;
            case R.id.dis7:
                dis1.setSelected(false);
                dis2.setSelected(false);
                dis3.setSelected(false);
                dis4.setSelected(false);
                dis5.setSelected(false);
                dis6.setSelected(false);
                dis7.setSelected(true);
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
        Intent intent = new Intent(this,GosuJoin4Activity.class);
        startActivity(intent);
        finish();
    }

    public void clickAddressChange(View view) {
        onBackPressed();
    }

    public void clickNext(View view) {
        Intent intent = new Intent(this,GosuJoin6Activity.class);
        startActivity(intent);
        finish();
    }
}