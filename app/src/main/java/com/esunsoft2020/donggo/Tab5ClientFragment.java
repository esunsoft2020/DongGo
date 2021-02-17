package com.esunsoft2020.donggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Tab5ClientFragment extends Fragment {

    RelativeLayout layout,profileLayout;
    LinearLayout layoutDeal;
    ImageView ivDeal, ivProfile;

    RecyclerView recyclerView;
    SettingRecyclerAdapter adapter;
    ArrayList<TwoStringItem> items = new ArrayList<>();

    TextView tvName,tvEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab5client,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout = view.findViewById(R.id.layout);
        profileLayout = view.findViewById(R.id.layout1);
        layoutDeal = view.findViewById(R.id.layout_deal);
        ivDeal = view.findViewById(R.id.iv_deal);
        ivProfile = view.findViewById(R.id.iv);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new SettingRecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);

    }

    @Override
    public void onResume() {
        super.onResume();

        tvName.setText(G.name);
        tvEmail.setText(G.email);

        //설정
        items.clear();
        items.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/alarm.png","알림"));
        items.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/deletememory.png","메모리 삭제"));
        items.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/noti.png","공지사항"));
        items.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/guide.png","동고안내"));
        items.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/setting/version.png","앱 버전"));


        if(G.profileImgUrl!=null)Glide.with(this).load(G.profileImgUrl).into(ivProfile);
        else Glide.with(this).load("http://donggo.dothome.co.kr/icon/account/pic.png").into(ivProfile);

        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/setting/shield.png").into(ivDeal);

        layoutDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "화면 준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GosuJoinActivity.class);
                intent.putExtra("where","Client");
                startActivity(intent);
                getActivity().finish();
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AccountActivity.class);
                startActivity(intent);
            }
        });

    }
}
