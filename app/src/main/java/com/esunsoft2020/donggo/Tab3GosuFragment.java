package com.esunsoft2020.donggo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Tab3GosuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3gosu,container,false);
    }

    TextView filter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filter = view.findViewById(R.id.tv_filter);

        ImageView iv;
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/chat.png").into(iv=view.findViewById(R.id.iv));

    }

    @Override
    public void onResume() {
        super.onResume();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setMessage("준비중입니다.").setPositiveButton("OK",null).show();
            }
        });
    }
}
