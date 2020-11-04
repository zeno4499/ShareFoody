package com.example.foodyrealtime.Model;

import java.util.List;

public class BinhLuanModel {
    double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel;
    String noidung;
    String tieude;
    String mauser;
    List<String> hinhanhBinhLuanList;
    String mabinhluan;

    public double getChamdiem() {   
        return chamdiem;
    }

    public void setChamdiem(double chamdiem) {
        this.chamdiem = chamdiem;
    }

    public String getMabinhluan() {
        return mabinhluan;
    }

    public void setMabinhluan(String mabinhluan) {
        this.mabinhluan = mabinhluan;
    }

    public List<String> getHinhanhBinhLuanList() {
        return hinhanhBinhLuanList;
    }

    public void setHinhanhBinhLuanList(List<String> hinhanhBinhLuanList) {
        this.hinhanhBinhLuanList = hinhanhBinhLuanList;
    }

    public BinhLuanModel() {
    }

    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }

    public BinhLuanModel(long chamdiem, long luotthich, ThanhVienModel thanhVienModel, String noidung, String tieude) {
        this.chamdiem = chamdiem;
        this.luotthich = luotthich;
        this.thanhVienModel = thanhVienModel;
        this.noidung = noidung;
        this.tieude = tieude;
    }


    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public ThanhVienModel getThanhVienModel() {
        return thanhVienModel;
    }

    public void setThanhVienModel(ThanhVienModel thanhVienModel) {
        this.thanhVienModel = thanhVienModel;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }
}
