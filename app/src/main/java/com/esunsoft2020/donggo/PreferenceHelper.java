package com.esunsoft2020.donggo;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private final String INTRO = "intro";
    private final String EMAIL = "email";
    private final String ISEMAILLOGIN = "pw";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared", 0);
        this.context = context;
    }

    public void putIsLogin(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginOrOut);
        edit.apply();
    }

    public void putName(String loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, loginOrOut);
        edit.apply();
    }

    public String getName()
    {
        return app_prefs.getString(EMAIL, "");
    }

    public void putISEMAILLOGIN(boolean loginOrOut) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ISEMAILLOGIN, loginOrOut);
        edit.apply();
    }
}
