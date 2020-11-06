package com.example.foodyrealtime.Model;

public class ChonHinhBinhLuanModel {

    String duongDan;
    boolean isCheck;

    public String getDuongDan() {
        return duongDan;
    }

    public ChonHinhBinhLuanModel(String duongDan, boolean isCheck) {
        this.duongDan = duongDan;
        this.isCheck = isCheck;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ChonHinhBinhLuanModel() {
    }
}
