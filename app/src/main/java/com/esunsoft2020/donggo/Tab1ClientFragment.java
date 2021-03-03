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

public class Tab1ClientFragment extends Fragment {

    RelativeLayout searchLayout;
    TextView[] all = new TextView[2];
    ImageView[] branchs = new ImageView[8];
    TextView[] titles = new TextView[8];

    RecyclerView service1,service2,service3;

    Service1Adapter adapter1;
    ArrayList<ThreeStringItem> items1 = new ArrayList<>();

    Service2Adapter adapter2,adapter3;
    ArrayList<TwoStringItem> items2 = new ArrayList<>();
    ArrayList<TwoStringItem> items3 = new ArrayList<>();
    LinearLayout gosuJoinLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1client,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchLayout = view.findViewById(R.id.search);

        for(int i=0; i<all.length;i++){
            all[i] = view.findViewById(R.id.all1+i);
        }

        for(int i=0; i<branchs.length; i++){
            branchs[i] = view.findViewById(R.id.branch1+i);
        }

        for(int i =0 ; i<titles.length;i++){
            titles[i] = view.findViewById(R.id.tv10+i);
        }
        
        service1 = view.findViewById(R.id.service1);
        adapter1 = new Service1Adapter(getActivity(),items1);
        service1.setAdapter(adapter1);

        service2 = view.findViewById(R.id.service2);
        adapter2 = new Service2Adapter(getActivity(),items2);
        service2.setAdapter(adapter2);


        service3 = view.findViewById(R.id.service3);
        adapter3 = new Service2Adapter(getActivity(),items3);
        service3.setAdapter(adapter3);

        gosuJoinLayout = view.findViewById(R.id.gosu_join_layout);
        if(G.loginState && G.isGosu) gosuJoinLayout.setVisibility(View.GONE);
        else gosuJoinLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();

        items1.clear();
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service2/study.jpg","영어 과외","69,345명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/exercise.jpg","퍼스널트레이닝(PT)","13,980명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/house.jpg","집 인테리어","14,879명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/bathroom.jpg","욕실/화장실 리모델링","10,818명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/vocal.jpg","보컬 레슨","14,897명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/fan.jpg","에어컨 설치 및 수리","3,604명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/sink.jpg","싱크대 교체","5,962명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/math.jpg","수학 과외","52,167명 고수 활동중"));
        items1.add(new ThreeStringItem("http://donggo.dothome.co.kr/icon/service1/lights.jpg","조명 인테리어","6,526명 고수 활동중"));


        items2.clear();
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service2/cafe.jpg","상업공간 인테리어"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service2/study.jpg","영어 과외"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service2/marketing.jpg","블로그 마케팅"));
        items2.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service2/box.jpg","원룸/소형 이사"));

        items3.clear();
        items3.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service3/construct.jpg","외풍차단/틈막이 시공"));
        items3.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service3/whisky.jpg","위스키 레슨"));
        items3.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service3/choco.jpg","초콜릿 레슨"));
        items3.add(new TwoStringItem("http://donggo.dothome.co.kr/icon/service3/food.jpg","명절/제사 음식 대행"));


        Glide.with(this).load("http://donggo.dothome.co.kr/icon/lesson.png").into(branchs[0]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/home.png").into(branchs[1]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/event.png").into(branchs[2]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/business.png").into(branchs[3]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/design.png").into(branchs[4]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/health.png").into(branchs[5]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/alba.png").into(branchs[6]);
        Glide.with(this).load("http://donggo.dothome.co.kr/icon/else.png").into(branchs[7]);

        for (int i=0 ; i<branchs.length;i++){
            branchs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIcon(v);
                }
            });
        }

        for(int i=0 ; i<all.length ; i++){
            all[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickAll(v);
                }
            });
        }

        for(int i=0 ; i<titles.length ; i++){
            titles[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIcon(v);
                }
            });
        }

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });

        gosuJoinLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGosuJoin();
            }
        });







    }

    void clickGosuJoin(){
        if(G.loginState) {
            startActivity(new Intent(getActivity(),GosuJoinActivity.class));
            getActivity().finish();
        }else {
            startActivity(new Intent(getActivity(),JoinActivity.class));
            getActivity().finish();
        }
    }

    void clickIcon(View v){

        //TODO : 다른 액티비티 생성하기(슬라이딩 뷰가 들어간 액티비티)
        Intent intent = new Intent(getActivity(),AllServiceActivity.class);
        intent.putExtra("service",v.getTag().toString());
        Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    void clickAll(View v){

        Intent intent = new Intent(getActivity(),AllServiceActivity.class);

        Bundle bundle = new Bundle();
        if(v.getTag()==null){

            bundle.putString("service",((TextView)v).getText().toString());
            bundle.putInt("s",2);
        }else{

            bundle.putString("service",v.getTag().toString());
            bundle.putInt("s",3);
        }
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
