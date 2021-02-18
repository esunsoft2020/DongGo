package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GosuActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments = new Fragment[5];
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosu);
        manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();

        bnv = findViewById(R.id.bnv);

        fragments[0] = new Tab1GosuFragment();
        tran.add(R.id.container, fragments[0]);
        tran.commit();




    }

    @Override
    protected void onResume() {
        super.onResume();

        showFragment();


        if(getIntent().getStringExtra("logout")!=null) finish();



    }

    void showFragment(){

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction tran = manager.beginTransaction();
                if(fragments[0] != null) tran.hide(fragments[0]);
                if(fragments[1] != null) tran.hide(fragments[1]);
                if(fragments[2] != null) tran.hide(fragments[2]);
                if(fragments[3] != null) tran.hide(fragments[3]);
                if(fragments[4] != null) tran.hide(fragments[4]);

                switch (item.getItemId()){
                    case R.id.bnv_gosu_receive:
                        tran.show(fragments[0]);
                        break;
                    case R.id.bnv_gosu_list:
                        if(fragments[1]==null){
                            fragments[1]= new Tab2GosuFragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;
                    case R.id.bnv_gosu_chat:
                        if(fragments[2]==null){

                            fragments[2] = new Tab3GosuFragment();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;
                    case R.id.bnv_gosu_profile:
                        if(fragments[3]==null){

                            fragments[3] = new Tab4GosuFragment();
                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        break;
                    case R.id.bnv_gosu_set:
                        if(fragments[4]==null){
                            fragments[4] = new Tab5GosuFragment();
                            tran.add(R.id.container, fragments[4]);
                        }
                        tran.show(fragments[4]);
                        break;
                }
                tran.commit();

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("종료하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //저장하고 종료
                SharedPreferences pref = getSharedPreferences("userData",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name",G.name);
                editor.putString("email",G.email);
                editor.putString("phone",G.phone);
                editor.putString("image",G.profileImgUrl);
                editor.putString("pw",G.pw);
                editor.putBoolean("isEmailLogin",G.isEmailLogin);
                editor.putBoolean("isKakaoLogin",G.iskakaoLogin);
                editor.putBoolean("isFacebookLogin",G.isGoogleLogin);
                editor.putBoolean("isGosu",G.isGosu);

                editor.commit();

                finish();
            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}