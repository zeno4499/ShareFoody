package com.example.foodyrealtime.Controller;

import com.example.foodyrealtime.Model.BinhLuanModel;

import java.util.List;

public class BinhLuanController {
    BinhLuanModel binhLuanModel;

    public BinhLuanController() {
        binhLuanModel = new BinhLuanModel();
    }

    public void ThemBinhLuan(String maquanan, BinhLuanModel binhLuanModel, final List<String> linkHinh) {
        binhLuanModel.themBinhLuan(maquanan, binhLuanModel, linkHinh);
    }

}
