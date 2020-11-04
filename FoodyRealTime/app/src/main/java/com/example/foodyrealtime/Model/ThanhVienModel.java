package com.example.foodyrealtime.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

<<<<<<< Updated upstream
public class ThanhVienModel {
    private DatabaseReference dataNodeThanhvien;
    private String hoten, hinhanh;

    public ThanhVienModel() {
=======
public class ThanhvienModel {
    private DatabaseReference dataNodeThanhvien;
    private String hoten,
            hinhanh;

    public ThanhvienModel() {
>>>>>>> Stashed changes
        dataNodeThanhvien = FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
    public void themthongtinthanhvien(ThanhvienModel thanhvienModel, String uid){

        dataNodeThanhvien.child(uid).setValue(thanhvienModel);

<<<<<<< Updated upstream
    public void themthongtinthanhvien(ThanhVienModel thanhvienModel, String uid) {
        dataNodeThanhvien.child(uid).setValue(thanhvienModel);
=======
>>>>>>> Stashed changes
    }
}
