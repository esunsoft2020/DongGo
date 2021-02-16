package com.esunsoft2020.donggo;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this,"6975656ad5c26ba3a34f6e976e227941");
    }
}
