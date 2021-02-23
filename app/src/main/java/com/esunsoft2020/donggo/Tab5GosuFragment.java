package com.esunsoft2020.donggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Tab5GosuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab5gosu,container,false);
    }

    Switch switchClient;

    ImageView ivStore,ivUse, ivCons,ivSearch,ivAuto, ivProfile;
    RelativeLayout layoutProfile;

    RecyclerView recyclerView1, recyclerView2;
    SettingRecyclerAdapter adapter1,adapter2;

    ArrayList<TwoStringItem> items1 = new ArrayList<>();
    ArrayList<TwoStringItem> items2 = new ArrayList<>();

    TextView tvName,tvEmail;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchClient = view.findViewById(R.id.switch_client);

        layoutProfile = view.findViewById(R.id.layout1);
        ivProfile = view.findViewById(R.id.iv);

        ivStore = view.findViewById(R.id.iv_store);
        ivUse = view.findViewById(R.id.iv_use);

        ivCons = view.findViewById(R.id.iv_cons);
        ivSearch = view.findViewById(R.id.iv_search);
        ivAuto = view.findViewById(R.id.iv_auto);

        recyclerView1 = view.findViewById(R.id.recycler1);
        adapter1 = new SettingRecyclerAdapter(getActivity(),items1);
        recyclerView1.setAdapter(adapter1);

        recyclerView2 = view.findViewById(R.id.recycler2);
        adapter2 = new SettingRecyclerAdapter(getActivity(),items2);
        recyclerView2.setAdapter(adapter2);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);


    }

    @Override
    public void onResume() {
        super.onResume();

        tvName.setText(G.name);
        tvEmail.setText(G.email);

        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountActivity.class);
                intent.putExtra("where","Gosu");
                startActivity(intent);
                getActivity().finish();

            }
        });

        items2.clear();
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/alarm.png","알림"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/deletememory.png","메모리 삭제"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/noti.png","공지사항"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/book.png","고수 가이드"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/guide.png","동고 안내"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/version.png","앱 버전"));

        items1.clear();
        items1.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/shield.png",getResources().getString(R.string.tab5_gosu_text7)));
        items1.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/guide.png",getResources().getString(R.string.tab5_gosu_text8)));

        Picasso.get().load("http://donggo.dothome.co.kr/icon/setting/store.png").into(ivStore);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/setting/use.png").into(ivUse);

        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/setting/chat.png").into(ivCons);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/setting/search.png").into(ivSearch);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/setting/auto.png").into(ivAuto);

        if(G.profileImgUrl!=null)Glide.with(this).load(G.profileImgUrl).into(ivProfile);
        else Glide.with(this).load("http://donggo.dothome.co.kr/icon/account/pic.png").into(ivProfile);


        //고객으로 전환
        switchClient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    startActivity(new Intent(getActivity(),MainActivity.class));
                    getActivity().finish();
                }
            }
        });

        ivStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSetting(v);
            }
        });
        ivUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSetting(v);
            }
        });
        ivCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSetting(v);
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSetting(v);
            }
        });
        ivAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSetting(v);
            }
        });

    }
    void clickSetting(View v){

        switch (v.getTag().toString()){
            default:
                Toast.makeText(getActivity(), v.getTag().toString()+"  준비중입니다.", Toast.LENGTH_SHORT).show();

        }
    }

}
