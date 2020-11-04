package com.example.foodyrealtime.Model;

import androidx.annotation.NonNull;

import com.example.foodyrealtime.Controller.Interface.ODauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel {
    boolean giaohang;
    String giodongcua;
    String giomocua;
    String tenquanan;
    String videogioithieu;
    String maquanan;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhLuanModelList;
    long luotthich;
    DatabaseReference databaseReference;

    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }

    public QuanAnModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public List<BinhLuanModel> getBinhLuanModelList() {
        return binhLuanModelList;
    }

    public void setBinhLuanModelList(List<BinhLuanModel> binhLuanModelList) {
        this.binhLuanModelList = binhLuanModelList;
    }

    public void getDanhSachQuanAn(final ODauInterface oDauInterface) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshotQuanAn = snapshot.child("quanans");

                //lấy danh sách quán ăn
                for (DataSnapshot value : dataSnapshotQuanAn.getChildren()) {
                    QuanAnModel quanAnModel = value.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(value.getKey());
                    //lấy danh sách  hình ảnh  quán ăn theo mã
                    DataSnapshot dataSnapshotHinhAnh = snapshot.child("hinhanhquanans").child(value.getKey());

                    List<String> hinhAnhList = new ArrayList<>();
                    for (DataSnapshot valueHinhQuanAn : dataSnapshotHinhAnh.getChildren()) {
                        hinhAnhList.add(valueHinhQuanAn.getValue(String.class));
                    }
                    quanAnModel.setHinhanhquanan(hinhAnhList);
                    //lấy danh sách  bình luận của quán ăn
                    DataSnapshot snapshotBinhLuan = snapshot.child("binhluans").child(quanAnModel.getMaquanan());
                    List<BinhLuanModel> binhLuanModels = new ArrayList<>();
                    for (DataSnapshot valueBinhLuan : snapshotBinhLuan.getChildren()) {
                        BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                        binhLuanModel.setMabinhluan(valueBinhLuan.getKey());
                        ThanhVienModel thanhVienModel = snapshot.child("thanhviens")
                                .child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                        binhLuanModel.setThanhVienModel(thanhVienModel);

                        List<String> hinhanhBinhLuanList = new ArrayList<>();
                        DataSnapshot snapshotNodeHinhAnhBL = snapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                        for (DataSnapshot valueHinhBinhLuan : snapshotNodeHinhAnhBL.getChildren()) {
                            hinhanhBinhLuanList.add(valueHinhBinhLuan.getValue(String.class));
                        }
                        binhLuanModel.setHinhanhBinhLuanList(hinhanhBinhLuanList);
                        binhLuanModels.add(binhLuanModel);
                    }
                    quanAnModel.setBinhLuanModelList(binhLuanModels);
                    oDauInterface.getDanhSachQuanAnModel(quanAnModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

}
