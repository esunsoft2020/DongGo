package com.esunsoft2020.donggo;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.PhantomReference;

public class PreferenceHelper {
    private final String INTRO = "intro";
    private final String NAME = "name";
    private final String EMAIL = "email";
    private final String PHONE = "phone";
    private final String Img = "name";
    private final String PROFILEIMGURL = "profileImgUrl";
    private final String ISEMAILLOGIN = "isEmailLogin";
    private final String ISKAKAOLOGIN = "isKakaoLogin";
    private final String ISGOOGLELOGIN = "iGoogleLogin";
    private final String ISGOSU = "isGosu";

    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
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
        editor.putBoolean("login",false);
        editor.commit();
    }

    public void putIsLogin(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginOrOut);
        edit.commit();
    }

    public boolean getIsLogin(){
        return app_prefs.getBoolean("login",false);
    }

    public void getDatas() {

        G.name = app_prefs.getString(NAME,null);
        G.email = app_prefs.getString(EMAIL,null);
        G.phone = app_prefs.getString(PHONE,null);
        G.profileImgUrl = app_prefs.getString(PROFILEIMGURL,null);
        G.isEmailLogin = app_prefs.getBoolean(ISEMAILLOGIN,false);
        G.iskakaoLogin = app_prefs.getBoolean(ISKAKAOLOGIN,false);
        G.isGoogleLogin = app_prefs.getBoolean(ISGOOGLELOGIN,false);
        G.isGosu = app_prefs.getBoolean(ISGOOGLELOGIN,false);

    }

    public void putName(String loginOrOut){
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.putString(NAME, loginOrOut);
        editor.commit();
    }

    public void putEmail(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, loginOrOut);
        edit.commit();
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
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ISEMAILLOGIN, loginOrOut);
        edit.commit();
    }
    public void putISKAKAOLOGIN(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ISKAKAOLOGIN, loginOrOut);
        edit.commit();
    }
    public void putISGOOGLELOGIN(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ISGOOGLELOGIN, loginOrOut);
        edit.commit();
    }
    public void putISGOSU(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ISGOSU, loginOrOut);
        edit.commit();
    }


}
