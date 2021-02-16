package com.esunsoft2020.donggo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class G {

    //TODO:회원가입시 이용 멤버추가 필요
    public static String name = "이름";
    public static String email = "Ireum@donggo.com";
    public static String pw = "****";
    public static String phone;

    public static String profileImgUrl;



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







}
