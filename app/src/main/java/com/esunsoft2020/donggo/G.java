package com.esunsoft2020.donggo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class G {

    public static String name=null;
    public static String email=null;
    public static String pw = "*****";     //따로 저장x
    public static String phone=null;
    public static String profileImgUrl=null;

    public static boolean isEmailLogin = false;
    public static boolean iskakaoLogin = false;
    public static boolean isGoogleLogin = false;
    public static boolean isGosu =false;

    public static boolean loginState = false;
    public static boolean prepareGosu = false;
    public static LatLng gosuLatLng =null;

    public static String where = "intro";

    public static void init(){
        G.loginState = false;
        G.name = null;
        G.email = null;
        G.phone = null;
        G.profileImgUrl = null;
        G.isEmailLogin = false;
        G.iskakaoLogin = false;
        G.isGoogleLogin = false;
        G.isGosu = false;
    }

    public static void init(boolean loginState,String name,String emil, String phone, String profileImgUrl,boolean isEmailLogin,boolean iskakaoLogin,boolean isGoogleLogin,boolean isGosu){
        G.loginState = loginState;
        G.name = name;
        G.email =emil;
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
    public static boolean tinyint2Boolean(String tinyint){
        boolean changeTinyint =false;
        if(tinyint.equals("1")) changeTinyint = true;
        else if(tinyint.equals("0") || tinyint.equals("null")) changeTinyint = false;
        return changeTinyint;
    }

    //boolean 을 String 으로 변경
    public static String boolean2String(boolean boo){
        String changeBoolean = null;
        if(boo==true) changeBoolean = 1+"";
        else changeBoolean=0+"";
        return changeBoolean;
    }

    //전화번호 중 특수문자 제거
    public static String phoneReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, "");
        return str;
    }

    //Custom Toast
    public static void CustomToast(Context context, String msg){
        TextView tvToastMsg = new TextView(context);
        tvToastMsg.setText(msg);
        tvToastMsg.setBackgroundResource(R.color.text_gray);
        tvToastMsg.setTextColor(context.getResources().getColor(R.color.white));
        tvToastMsg.setTextSize(16);
        tvToastMsg.setHeight(80);
        tvToastMsg.setWidth(800);
        tvToastMsg.setGravity(Gravity.CENTER);


        final Toast toastMsg = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toastMsg.setView(tvToastMsg);

        toastMsg.show();
    }


}
