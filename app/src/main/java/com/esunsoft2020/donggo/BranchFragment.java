package com.esunsoft2020.donggo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class BranchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_branch,container,false);
    }

    ListView listView;
    String[] listServices;
    ArrayAdapter<String> adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listview);


    }
    @Override
    public void onResume() {
        super.onResume();
        //tab title string[]
        String[] services = ((BranchActivity)getActivity()).services;
        int position = ((BranchActivity)getActivity()).pager.getCurrentItem();

        //현재 tab title
        loadData(services[position]);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(getActivity()).setMessage(listServices[position]+" 준비중입니다.").setPositiveButton("OK",null).show();
            }
        });
    }

    void loadData(String service){

        switch (service) {
            case "학업":
                listServices = getResources().getStringArray(R.array.study1);
                break;
            case "외국어":
                listServices = getResources().getStringArray(R.array.language1);
                break;
            case "외국어시험":
                listServices = getResources().getStringArray(R.array.foreign_test1);
                break;
            case "공예":
                listServices = getResources().getStringArray(R.array.crafts1);
                break;
            case "미술":
                listServices = getResources().getStringArray(R.array.art1);
                break;
            case "음악이론/보컬":
                listServices = getResources().getStringArray(R.array.music1);
                break;


            case "이사":
                listServices = getResources().getStringArray(R.array.esa2);
                break;
            case "청소 업체":
                listServices = getResources().getStringArray(R.array.clean2);
                break;
            case "인테리어":
                listServices = getResources().getStringArray(R.array.interior2);
                break;
            case "야외 시공":
                listServices = getResources().getStringArray(R.array.outside2);
                break;
            case "부동산":
                listServices = getResources().getStringArray(R.array.home_buy2);
                break;
            case "철거/정리":
                listServices = getResources().getStringArray(R.array.pull_down2);
                break;
            case "펫/반려동물":
                listServices = getResources().getStringArray(R.array.pet2);
                break;
            case "문/창문":
                listServices = getResources().getStringArray(R.array.door2);
                break;


            case "웨딩":
                listServices = getResources().getStringArray(R.array.wedding3);
                break;
            case "촬영 및 편집":
                listServices = getResources().getStringArray(R.array.camera3);
                break;
            case "뮤직/엔터테인먼트":
                listServices = getResources().getStringArray(R.array.music3);
                break;
            case "음식":
                listServices = getResources().getStringArray(R.array.food3);
                break;
            case "뷰티/미용":
                listServices = getResources().getStringArray(R.array.beauty3);
                break;
            case "기획 및 장식":
                listServices = getResources().getStringArray(R.array.plan3);
                break;


            case "번역":
                listServices = getResources().getStringArray(R.array.writer4);
                break;
            case "통역":
                listServices = getResources().getStringArray(R.array.speaker4);
                break;
            case "문서":
                listServices = getResources().getStringArray(R.array.paper4);
                break;
            case "인쇄":
                listServices = getResources().getStringArray(R.array.print4);
                break;
            case "마케팅":
                listServices = getResources().getStringArray(R.array.marketing4);
                break;


            case "디자인 외주":
                listServices = getResources().getStringArray(R.array.design5);
                break;
            case "개발 외주":
                listServices = getResources().getStringArray(R.array.develop5);
                break;


            case "심리":
                listServices = getResources().getStringArray(R.array.mental6);
                break;
            case "미용":
                listServices = getResources().getStringArray(R.array.miyong6);
                break;
            case "건강":
                listServices = getResources().getStringArray(R.array.health6);
                break;


            case "서빙.주방 알바":
                listServices = getResources().getStringArray(R.array.alba7_1);
                break;
            case "매장관리.판매 알바":
                listServices = getResources().getStringArray(R.array.alba7_2);
                break;
            case "서비스.행사 알바":
                listServices = getResources().getStringArray(R.array.alba7_3);
                break;
            case "문화.여가.생활 알바":
                listServices = getResources().getStringArray(R.array.alba7_4);
                break;
            case "방송.미디어 알바":
                listServices = getResources().getStringArray(R.array.alba7_5);
                break;


            case "여행":
                listServices = getResources().getStringArray(R.array.else8_1);
                break;
            case "공예제작":
                listServices = getResources().getStringArray(R.array.else8_2);
                break;
            case "의류/잡화":
                listServices = getResources().getStringArray(R.array.else8_3);
                break;
            case "자동차":
                listServices = getResources().getStringArray(R.array.else8_4);
                break;
            case "대여/대관":
                listServices = getResources().getStringArray(R.array.else8_5);
                break;
            case "금융":
                listServices = getResources().getStringArray(R.array.else8_6);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + service);
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listServices);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
