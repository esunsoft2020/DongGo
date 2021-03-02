package com.esunsoft2020.donggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab2ClientFragment extends Fragment {

    TextView tvArea,tvService,clickReview;
    RecyclerView recyclerView;
    Tab2RecyclerAdapter adapter;
    ArrayList<Tab2RecyclerItem> items =new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2client,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvArea = view.findViewById(R.id.tv_area);
        tvService = view.findViewById(R.id.tv_service);
        clickReview = view.findViewById(R.id.clickReview);

        recyclerView = view.findViewById(R.id.recycler);
        adapter = new Tab2RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        items.clear();

        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/wash.jpg","집을 빨래하다(동고 1위)","동고 압도적인 1위! 눈으로 보시고 판단해 주세요",4.7f,"(654개)","2709회 고용"));
        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/hebang.jpg","해방(해충으로부터 해방)","[해방]해충방역 서비스 동고 1위 프로필 참고!",5.0f,"(488개)","1833회 고용"));
        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/clean.jpg","베스트스크린","다른 고객님들 후기 꼭 읽어주세요~맘카페 추천!!",4.8f,"(436개)","1621회 고용"));
        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/esa.jpg","김기도","안녕하세요 동고 이사 리뷰 1등 용달빠르기도 대표 김기도입니다.",3.0f,"(123개)","4567회 고용"));
        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/designdonggo.jpg","디자인 동고 1위","동고디자인1위, 한국소비자 만족1위/로고,명함 및 홍보인쇄물 제작전문 / 1:1상담 디자이너 20명 / 빠른 응대",1.0f,"(234개)","5678회 고용"));
        items.add(new Tab2RecyclerItem("http://donggo.dothome.co.kr/icon/imsi/d9.jpg","디자인 동고 1위","동고디자인1위, 한국소비자 만족1위/로고,명함 및 홍보인쇄물 제작전문 / 1:1상담 디자이너 20명 / 빠른 응대",1.0f,"(234개)","5678회 고용"));



        clickReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });

        //지역 선택시 액티비티
        tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SelectAreaActivity.class));
            }
        });

        tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });
    }
}
