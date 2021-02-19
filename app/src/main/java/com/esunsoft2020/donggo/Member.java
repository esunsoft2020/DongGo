package com.esunsoft2020.donggo;

public class Member {

    public String name;
    public String email;
    public String pw;
    public String phone;
    public String profileImgUrl;

    public String isEmailLogin;
    public boolean iskakaoLogin;
    public boolean isGoogleLogin;
    public boolean isGosu;

    public Member() {
    }

    public Member(String name, String email, String pw, String phone, String profileImgUrl, String isEmailLogin, boolean iskakaoLogin, boolean isGoogleLogin, boolean isGosu) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.phone = phone;
        this.profileImgUrl = profileImgUrl;
        this.isEmailLogin = isEmailLogin;
        this.iskakaoLogin = iskakaoLogin;
        this.isGoogleLogin = isGoogleLogin;
        this.isGosu = isGosu;
    }
}
