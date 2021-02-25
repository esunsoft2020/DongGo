package com.esunsoft2020.donggo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

public class Tab4ClientFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab4client,container,false);
    }

    TextView tvFilter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iv;
        Glide.with(getActivity()).load("http://donggo.dothome.co.kr/icon/chat.png").into(iv=view.findViewById(R.id.iv));

        tvFilter = view.findViewById(R.id.tv_filter);

    }

    @Override
    public void onResume() {
        super.onResume();
        tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getActivity(),v,"개발중입니다.",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
