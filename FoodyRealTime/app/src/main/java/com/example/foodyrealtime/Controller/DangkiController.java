package com.example.foodyrealtime.Controller;

import com.example.foodyrealtime.Model.ThanhVienModel;

public class DangkiController {
    ThanhVienModel thanhvienModel;
    public DangkiController() {
        thanhvienModel = new ThanhVienModel();
    }
    public  void themthanhvienController(ThanhVienModel thanhvienModel, String uid){
        thanhvienModel.themthongtinthanhvien(thanhvienModel,uid);
    }
}
