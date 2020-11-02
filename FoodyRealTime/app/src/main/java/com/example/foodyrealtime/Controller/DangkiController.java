package com.example.foodyrealtime.Controller;

import com.example.foodyrealtime.Model.ThanhvienModel;

public class DangkiController {
    ThanhvienModel thanhvienModel;
    public DangkiController() {
        thanhvienModel = new ThanhvienModel();
    }
    public  void themthanhvienController(ThanhvienModel thanhvienModel,String uid){
        thanhvienModel.themthongtinthanhvien(thanhvienModel,uid);
    }
}
