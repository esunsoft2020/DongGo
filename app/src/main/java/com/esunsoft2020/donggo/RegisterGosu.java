package com.esunsoft2020.donggo;

import java.util.ArrayList;

public class RegisterGosu {

    public static String gosuBranch;
    public static String gosuService;
    public static String serviceDetail;
    public static String address;
    public static String radius;
    public static String mf;
    public static String phone;

    public static ArrayList<String> service = new ArrayList<>();
    public static ArrayList<String> detail = new ArrayList<>();

    public static void init(){
        RegisterGosu.gosuBranch = null;
        RegisterGosu.gosuService = null;
        RegisterGosu.serviceDetail = null;
        RegisterGosu.address = null;
        RegisterGosu.radius = null;
        RegisterGosu.mf = null;
        RegisterGosu.phone = null;
    }

    public static void init(String gosuBranch,String gosuService, String serviceDetail, String address, String radius, String mf, String phone){
        RegisterGosu.gosuBranch = gosuBranch;
        RegisterGosu.gosuService = gosuService;
        RegisterGosu.serviceDetail = serviceDetail;
        RegisterGosu.address = address;
        RegisterGosu.radius = radius;
        RegisterGosu.mf = mf;
        RegisterGosu.phone = phone;
    }

}
