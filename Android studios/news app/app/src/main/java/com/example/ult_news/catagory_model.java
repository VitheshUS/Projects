package com.example.ult_news;

public class catagory_model {

    private String c_name;
    private String img_url;

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public catagory_model(String c_name, String img_url) {
        this.c_name = c_name;
        this.img_url = img_url;
    }
}
