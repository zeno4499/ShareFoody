package com.example.foodyrealtime.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterHienThiHinhBinhLuanDuocChon;
import com.example.foodyrealtime.R;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtTenQuanAn;
    TextView txtDiaChiQuanAn;
    Toolbar toolbar;
    ImageButton btnChonHinh;
    RecyclerView recyclerViewChonHinhBinhLuan;
    AdapterHienThiHinhBinhLuanDuocChon adapterHienThiHinhBinhLuanDuocChon;
    final int REQUEST_CHONHINHBINHLUAN = 1;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binh_luan);
//        String tenquan = getIntent().getStringExtra("tenquan");
//        String diaChi = getIntent().getStringExtra("diachi");
//
        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAnODau);
        toolbar = findViewById(R.id.toolbar);
        btnChonHinh = findViewById(R.id.btnChonHinh);
        recyclerViewChonHinhBinhLuan = findViewById(R.id.recyclerChonHinhBinhLuan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewChonHinhBinhLuan.setLayoutManager(layoutManager);
//
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//
//        txtTenQuanAn.setText(tenquan);
//        txtTenQuanAn.setText(diaChi);
        btnChonHinh.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnChonHinh: {
                Intent iChonHinhBinhLuan = new Intent(this, ChonHinhBinhLuanActivity.class);
                startActivityForResult(iChonHinhBinhLuan, REQUEST_CHONHINHBINHLUAN);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHONHINHBINHLUAN) {
            List<String> listHinhDuocChon = data.getStringArrayListExtra("listHinhDuocChon");
            adapterHienThiHinhBinhLuanDuocChon = new AdapterHienThiHinhBinhLuanDuocChon(this,
                    R.layout.custom_layout_hienthibinhluanduocchon, listHinhDuocChon);
            recyclerViewChonHinhBinhLuan.setAdapter(adapterHienThiHinhBinhLuanDuocChon);
            adapterHienThiHinhBinhLuanDuocChon.notifyDataSetChanged();
        }
    }

}
