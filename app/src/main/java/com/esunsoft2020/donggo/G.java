package com.esunsoft2020.donggo;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class G {

    //TODO:회원가입시 이용 멤버추가 필요
    public static String name ;
    public static String email ;
    public static String pw = "****";
    public static String phone;
    public static String profileImgUrl;

    public static boolean isEmailLogin = false;
    public static boolean iskakaoLogin = false;
    public static boolean isGoogleLogin = false;
    public static boolean isGosu =false;

    public static boolean loginState = false;

    public static void init(){
        G.loginState = false;
        G.name = null;
        G.email = null;
        G.pw =null;
        G.phone = null;
        G.profileImgUrl = null;
        G.isEmailLogin = false;
        G.iskakaoLogin = false;
        G.isGoogleLogin = false;
        G.isGosu = false;
    }

    public static void init(String name,String emil,String pw, String phone, String profileImgUrl,boolean isEmailLogin,boolean iskakaoLogin,boolean isGoogleLogin,boolean isGosu){
        G.loginState = true;
        G.name = name;
        G.email =emil;
        G.pw =pw;
        G.phone = phone;
        G.profileImgUrl = profileImgUrl;
        G.isEmailLogin = isEmailLogin;
        G.iskakaoLogin = iskakaoLogin;
        G.isGoogleLogin = isGoogleLogin;
        G.isGosu = isGosu;
    }



    public static String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);

        return temp;

    }



    //이메일 검증 정규식
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex); Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }


    //비밀번호 검증 정규식 https://beagle-dev.tistory.com/114, https://cofs.tistory.com/358 참고
    public static boolean isValidPw(String pw){
        boolean err = false;
        //최소 8 자, 대문자 하나 이상, 소문자 하나 및 숫자 하나
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
//        String regex = "^(?=.*\\\\d)(?=.*[~`!@#$%\\\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
        Pattern p = Pattern.compile(regex); Matcher m = p.matcher(pw);
        if(m.matches()){
            err = true;
        }
        return err;
    }

    //전화번호 포맷 변경 클래스
    public static String changePhoneFormat(String num){
        if(num ==null){
            return "";
        }
        if(num.length() ==8){
            return num.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        }else if (num.length() == 12) {
            return num.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return num.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }

    //1 or 0 or null 로 온 String 을 boolean 으로 변경
    public static boolean changeBooleanFormat(String tinyint){
        boolean changeString =false;
        if(tinyint.equals("1")) changeString = true;
        else if(tinyint.equals("0") || tinyint.equals("null")) changeString = false;

        return changeString;
    }

    //boolean 을 String 으로 변경
    public static String changeStringFormat(boolean boo){
        String changeBoolean = null;
        if(boo==true) changeBoolean = 1+"";
        else changeBoolean=0+"";
        return changeBoolean;
    }







}
