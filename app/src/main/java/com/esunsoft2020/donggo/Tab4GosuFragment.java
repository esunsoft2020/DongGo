package com.esunsoft2020.donggo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Tab4GosuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab4gosu,container,false);
    }

    CircleImageView civ;
    ImageView iv,iv1;
    RelativeLayout mediaUpload;

    ViewPager pager;
    ArrayList<TwoStringItem> items = new ArrayList<>();
    PageTab4GosuAdapter adapter;

    ImageView[] ivs = new ImageView[8];

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv=view.findViewById(R.id.iv);
        civ=view.findViewById(R.id.civ);
        iv1 = view.findViewById(R.id.iv1);
        mediaUpload = view.findViewById(R.id.media_upload);

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

        //개인 사진 iv(변경할 수 있게해야함)
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/mypage.png").into(civ);

        //변경x
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/shot.png").into(iv);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/tab4.png").into(iv1);

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
}
