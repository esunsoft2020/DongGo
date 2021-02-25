package com.esunsoft2020.donggo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView browser;

    class MyJavaScriptInterface{
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processData(String doro,String jibeon){
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            bundle.putString("doro",doro);
            bundle.putString("jibun",jibeon);

            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        browser = (WebView)findViewById(R.id.web_view);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new MyJavaScriptInterface(),"Android");

        browser.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                browser.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        browser.loadUrl("http://donggo.dothome.co.kr/daum.html");
    }
}