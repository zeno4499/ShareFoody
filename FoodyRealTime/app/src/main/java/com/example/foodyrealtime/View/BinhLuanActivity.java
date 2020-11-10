

package com.example.foodyrealtime.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterHienThiHinhBinhLuanDuocChon;
import com.example.foodyrealtime.Controller.BinhLuanController;
import com.example.foodyrealtime.Model.BinhLuanModel;
import com.example.foodyrealtime.R;

import java.util.ArrayList;
import java.util.List;

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtTenQuanAn;
    TextView txtDiaChiQuanAn;
    Toolbar toolbar;
    ImageButton btnChonHinh;
    TextView txtDangBinhLuan;
    EditText edTieuDeBinhLuan;
    EditText edNoiDungBinhLuan;
    RecyclerView recyclerViewChonHinhBinhLuan;
    AdapterHienThiHinhBinhLuanDuocChon adapterHienThiHinhBinhLuanDuocChon;

    final int REQUEST_CHONHINHBINHLUAN = 1;
    String maquanan;
    SharedPreferences sharedPreferences;
    BinhLuanController binhLuanController;
    List<String> listHinhDuocChon;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binh_luan);
        binhLuanController = new BinhLuanController();
        listHinhDuocChon = new ArrayList<>();

        maquanan = getIntent().getStringExtra("maquanan");
        String tenquan = getIntent().getStringExtra("tenquan");
        String diaChi = getIntent().getStringExtra("diachi");

        sharedPreferences = getSharedPreferences("luudangnhap", MODE_PRIVATE);
        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChiQuanAn = findViewById(R.id.txtDiaChiQuanAn);
        toolbar = findViewById(R.id.toolbar);
        btnChonHinh = findViewById(R.id.btnChonHinh);
        recyclerViewChonHinhBinhLuan = findViewById(R.id.recyclerChonHinhBinhLuan);
        txtDangBinhLuan = findViewById(R.id.txtDangBinhLuan);
        edTieuDeBinhLuan = findViewById(R.id.edTieuDeBinhLuan);
        edNoiDungBinhLuan = findViewById(R.id.edNoiDungBinhLuan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewChonHinhBinhLuan.setLayoutManager(layoutManager);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        txtTenQuanAn.setText(tenquan);
        txtDiaChiQuanAn.setText(diaChi);

        btnChonHinh.setOnClickListener(this);
        txtDangBinhLuan.setOnClickListener(this);

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
            case R.id.txtDangBinhLuan: {
                String tieuDe = edTieuDeBinhLuan.getText().toString();
                String noiDung = edNoiDungBinhLuan.getText().toString();
                //TODO: xoa defValue: để defaul này vì test chưa đăng nhập. Sẽ xóa sau
                String mauser = sharedPreferences.getString("mauser", "Dg4e9Phl0fSlChlvkOaEiijwc612");

                BinhLuanModel binhLuanModel = new BinhLuanModel();
                binhLuanModel.setTieude(tieuDe);
                binhLuanModel.setNoidung(noiDung);
                binhLuanModel.setChamdiem(0);
                binhLuanModel.setLuotthich(0);
                binhLuanModel.setMauser(mauser);

                //TODO: bo comment de add vao
                binhLuanController.ThemBinhLuan(maquanan, binhLuanModel, listHinhDuocChon);
                finish();
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHONHINHBINHLUAN) {
            listHinhDuocChon = data.getStringArrayListExtra("listHinhDuocChon");
            adapterHienThiHinhBinhLuanDuocChon = new AdapterHienThiHinhBinhLuanDuocChon(this,
                    R.layout.custom_layout_hienthibinhluanduocchon, listHinhDuocChon);
            recyclerViewChonHinhBinhLuan.setAdapter(adapterHienThiHinhBinhLuanDuocChon);
            adapterHienThiHinhBinhLuanDuocChon.notifyDataSetChanged();
        }
    }

}
