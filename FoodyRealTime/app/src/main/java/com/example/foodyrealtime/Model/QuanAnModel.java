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

    public void getDanhSachQuanAn(final ODauInterface oDauInterface) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot dataSnapshotQuanAn = snapshot.child("quanans");

                for (DataSnapshot value : dataSnapshotQuanAn.getChildren()) {
                    QuanAnModel quanAnModel = value.getValue(QuanAnModel.class);
                    quanAnModel.setMaquanan(value.getKey());
                    DataSnapshot dataSnapshotHinhAnh = snapshot.child("hinhanhquanans").child(value.getKey());

                    List<String> hinhAnhList = new ArrayList<>();
                    for (DataSnapshot valueHinhQuanAn : dataSnapshotHinhAnh.getChildren()) {
                        hinhAnhList.add(valueHinhQuanAn.getValue(String.class));
                    }
                    quanAnModel.setHinhanhquanan(hinhAnhList);
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
