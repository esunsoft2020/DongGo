package com.esunsoft2020.donggo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.PhantomReference;

public class PreferenceHelper {
    private final String LOGINSTATE = "login";
    private final String NAME = "name";
    private final String EMAIL = "email";
    private final String PHONE = "phone";
    private final String PROFILEIMGURL = "profileImgUrl";
    private final String ISEMAILLOGIN = "isEmailLogin";
    private final String ISKAKAOLOGIN = "isKakaoLogin";
    private final String ISGOOGLELOGIN = "iGoogleLogin";
    private final String ISGOSU = "isGosu";

    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
//        app_prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void init(){
        SharedPreferences.Editor editor = app_prefs.edit();

        editor.putString(NAME, null);
        editor.putString(EMAIL, null);
        editor.putString(PHONE, null);
        editor.putString(PROFILEIMGURL, null);
        editor.putBoolean(ISEMAILLOGIN, false);
        editor.putBoolean(ISKAKAOLOGIN, false);
        editor.putBoolean(ISGOOGLELOGIN, false);
        editor.putBoolean(ISGOSU, false);
        editor.putBoolean(LOGINSTATE,false);

        editor.commit();
    }

    public void putIsLogin(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(LOGINSTATE, loginOrOut);
        edit.commit();
    }

    public void getDatas() {

        G.loginState = app_prefs.getBoolean(LOGINSTATE,false);
        G.name = app_prefs.getString(NAME,null);
        G.email = app_prefs.getString(EMAIL,null);
        G.phone = app_prefs.getString(PHONE,null);
        G.profileImgUrl = app_prefs.getString(PROFILEIMGURL,null);
        G.isEmailLogin = app_prefs.getBoolean(ISEMAILLOGIN,false);
        G.iskakaoLogin = app_prefs.getBoolean(ISKAKAOLOGIN,false);
        G.isGoogleLogin = app_prefs.getBoolean(ISGOOGLELOGIN,false);
        G.isGosu = app_prefs.getBoolean(ISGOSU,false);
    }

    public void putDatas(){
        SharedPreferences.Editor editor = app_prefs.edit();

        editor.putBoolean(LOGINSTATE,G.loginState);
        editor.putString(NAME, G.name);
        editor.putString(EMAIL, G.email);
        editor.putString(PHONE, G.phone);
        editor.putString(PROFILEIMGURL, G.profileImgUrl);
        editor.putBoolean(ISEMAILLOGIN, G.isEmailLogin);
        editor.putBoolean(ISKAKAOLOGIN, G.iskakaoLogin);
        editor.putBoolean(ISGOOGLELOGIN, G.isGoogleLogin);
        editor.putBoolean(ISGOSU, G.isGosu);
        Log.e("Gosu",G.isGosu+"");
        editor.commit();
    }

    public void putName(String loginOrOut){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(NAME, loginOrOut);
        editor.commit();
    }

    public void putEmail(String loginOrOut) {
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(EMAIL, loginOrOut);
        editor.commit();
    }

    public void putPhone(String loginorOut){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(PHONE, loginorOut);
        editor.commit();
    }

    public void putPROFILEIMGURL(String loginorOut){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(PROFILEIMGURL, loginorOut);
        editor.commit();
    }
    public void putISEMAILLOGIN(boolean loginOrOut) {
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(ISEMAILLOGIN, loginOrOut);
        editor.commit();
    }
    public void putISKAKAOLOGIN(boolean loginOrOut) {
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(ISKAKAOLOGIN, loginOrOut);
        editor.commit();
    }
    public void putISGOOGLELOGIN(boolean loginOrOut) {
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(ISGOOGLELOGIN, loginOrOut);
        editor.commit();
    }
    public void putISGOSU(boolean loginOrOut) {
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putBoolean(ISGOSU, loginOrOut);
        editor.commit();
    }


}
