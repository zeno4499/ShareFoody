package com.example.foodyrealtime.Controller;

import android.net.Uri;

import com.example.foodyrealtime.Model.BinhLuanModel;

import java.util.List;

public class BinhLuanController {
    BinhLuanModel binhLuanModel;

    public BinhLuanController() {
        binhLuanModel = new BinhLuanModel();
    }

    public void ThemBinhLuan(String maquanan, BinhLuanModel binhLuanModel, final List<Uri> linkHinh) {
        binhLuanModel.themBinhLuan(maquanan, binhLuanModel, linkHinh);
    }

}
