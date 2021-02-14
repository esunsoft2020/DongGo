package com.esunsoft2020.donggo;

public class Tab2RecyclerItem {
    String imgUrl;
    String name;
    String detail;
    float ratingBar;
    String reviewCnt;
    String workCnt;

    public Tab2RecyclerItem() {
    }

    public Tab2RecyclerItem(String imgUrl, String name, String detail, float ratingBar, String reviewCnt, String workCnt) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.detail = detail;
        this.ratingBar = ratingBar;
        this.reviewCnt = reviewCnt;
        this.workCnt = workCnt;
    }
}
