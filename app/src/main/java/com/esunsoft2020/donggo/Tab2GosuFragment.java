package com.esunsoft2020.donggo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

public class Tab2GosuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2gosu,container,false);
    }

    ImageView iv,ivIcon1,ivIcon2,ivIcon3;
    Button clickBtn;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv = view.findViewById(R.id.iv);
        ivIcon1 = view.findViewById(R.id.iv_icon1);
        ivIcon2 = view.findViewById(R.id.iv_icon2);
        ivIcon3 = view.findViewById(R.id.iv_icon3);
        clickBtn = view.findViewById(R.id.clickBtn);


    }

    @Override
    public void onResume() {
        super.onResume();

        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/tab2.png").into(iv);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/tab2_lock.png").into(ivIcon1);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/tab2_list.png").into(ivIcon2);
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/tab2_money.png").into(ivIcon3);

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
