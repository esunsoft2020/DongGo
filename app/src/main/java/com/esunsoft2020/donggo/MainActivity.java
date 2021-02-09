package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    Fragment[] fragments = new Fragment[5];
    FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bnv);

        manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();
        fragments[0] = new Tab1ClientFragment();
        tran.add(R.id.container, fragments[0]);
        tran.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        showFragment();


    }

    void showFragment(){
        Boolean login = getIntent().getBooleanExtra("login",false);

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
                    case R.id.bnv_home:
                        tran.show(fragments[0]);
                        break;
                    case R.id.bnv_search:
                        if(fragments[1]==null){
                            fragments[1]= new Tab2ClientFragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                        break;
                    case R.id.bnv_receive:
                        if(fragments[2]==null){

                            if(login) fragments[2] = new Tab3ClientFragment();
                            else fragments[2] = new LogoutFragment();

                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        break;
                    case R.id.bnv_chat:
                        if(fragments[3]==null){

                            if(login) fragments[3] = new Tab4ClientFragment();
                            else fragments[3] = new LogoutFragment();

                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        break;
                    case R.id.bnv_setting:
                        if(fragments[4]==null){
                            if(login) fragments[4] = new Tab5ClientFragment();
                            else fragments[4] = new LogoutFragment();

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

}