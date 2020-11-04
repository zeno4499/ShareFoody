package com.example.foodyrealtime.Model;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;

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
    List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList;
    List<Bitmap> bitmapsList;

    public List<Bitmap> getBitmapsList() {
        return bitmapsList;
    }

    public void setBitmapsList(List<Bitmap> bitmapsList) {
        this.bitmapsList = bitmapsList;
    }

    public List<ChiNhanhQuanAnModel> getChiNhanhQuanAnModelList() {
        return chiNhanhQuanAnModelList;
    }

    public void setChiNhanhQuanAnModelList(List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList) {
        this.chiNhanhQuanAnModelList = chiNhanhQuanAnModelList;
    }

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

    private DataSnapshot dataRoot;

    public void getDanhSachQuanAn(final ODauInterface oDauInterface, final Location viTriHienTai, final int viTriItemTiepTheo, final int itemDaCo) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataRoot = snapshot;
                LayDanhSachQuanAn(snapshot, oDauInterface, viTriHienTai, viTriItemTiepTheo, itemDaCo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        if (dataRoot != null) {
            LayDanhSachQuanAn(dataRoot, oDauInterface, viTriHienTai, viTriItemTiepTheo, itemDaCo);
        } else {
            databaseReference.addListenerForSingleValueEvent(valueEventListener);
        }
    }

    private void LayDanhSachQuanAn(DataSnapshot dataSnapshot, ODauInterface oDauInterface, Location viTriHienTai, int itemtieptheo, int itemdaco) {
        DataSnapshot dataSnapshotQuanAn = dataSnapshot.child("quanans");

        //lấy danh sách quán ăn
        int i = 0;
        for (DataSnapshot value : dataSnapshotQuanAn.getChildren()) {
            if (i == itemtieptheo) {
                break;
            }
            if (i < itemdaco) {
                i++;
                continue;
            }
            i++;
            QuanAnModel quanAnModel = value.getValue(QuanAnModel.class);
            quanAnModel.setMaquanan(value.getKey());
            //lấy danh sách  hình ảnh  quán ăn theo mã
            DataSnapshot dataSnapshotHinhAnh = dataSnapshot.child("hinhanhquanans").child(value.getKey());

            List<String> hinhAnhList = new ArrayList<>();
            for (DataSnapshot valueHinhQuanAn : dataSnapshotHinhAnh.getChildren()) {
                hinhAnhList.add(valueHinhQuanAn.getValue(String.class));
            }
            quanAnModel.setHinhanhquanan(hinhAnhList);
            //lấy danh sách  bình luận của quán ăn
            DataSnapshot snapshotBinhLuan = dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());
            List<BinhLuanModel> binhLuanModels = new ArrayList<>();
            for (DataSnapshot valueBinhLuan : snapshotBinhLuan.getChildren()) {
                BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                binhLuanModel.setMabinhluan(valueBinhLuan.getKey());
                ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens")
                        .child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                binhLuanModel.setThanhVienModel(thanhVienModel);

                List<String> hinhanhBinhLuanList = new ArrayList<>();
                DataSnapshot snapshotNodeHinhAnhBL = dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                for (DataSnapshot valueHinhBinhLuan : snapshotNodeHinhAnhBL.getChildren()) {
                    hinhanhBinhLuanList.add(valueHinhBinhLuan.getValue(String.class));
                }
                binhLuanModel.setHinhanhBinhLuanList(hinhanhBinhLuanList);
                binhLuanModels.add(binhLuanModel);
            }
            quanAnModel.setBinhLuanModelList(binhLuanModels);

            //lay chi nhanh quan an
            DataSnapshot snapshotChiNhanhQuanAn = dataSnapshot.child("chinhanhquanans").child(quanAnModel.getMaquanan());
            List<ChiNhanhQuanAnModel> chiNhanhQuanAnModels = new ArrayList<>();
            for (DataSnapshot valueChiNhanhQuanAn : snapshotChiNhanhQuanAn.getChildren()) {
                ChiNhanhQuanAnModel chiNhanhQuanAnModel = valueChiNhanhQuanAn.getValue(ChiNhanhQuanAnModel.class);
                Location viTriQuanAn = new Location("");
                viTriQuanAn.setLatitude(chiNhanhQuanAnModel.getLatitude());
                viTriQuanAn.setLongitude(chiNhanhQuanAnModel.getLongtitude());

                double khoangCach = viTriHienTai.distanceTo(viTriQuanAn) / 1000;
                chiNhanhQuanAnModel.setKhoangcach(khoangCach);

                chiNhanhQuanAnModels.add(chiNhanhQuanAnModel);
            }
            quanAnModel.setChiNhanhQuanAnModelList(chiNhanhQuanAnModels);
            oDauInterface.getDanhSachQuanAnModel(quanAnModel);
        }
    }
}
