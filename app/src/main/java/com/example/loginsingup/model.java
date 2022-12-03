package com.example.loginsingup;

import java.io.Serializable;

public class model implements Serializable {

    String masp,tensp,gia,surl;

    model(){

    }

    public model(String masp, String tensp, String gia, String surl) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.surl = surl;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
