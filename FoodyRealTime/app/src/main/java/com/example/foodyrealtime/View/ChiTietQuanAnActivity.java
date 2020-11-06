package com.example.foodyrealtime.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.ApdaterBinhLuan;
import com.example.foodyrealtime.Controller.ChiTietQuanController;
import com.example.foodyrealtime.Controller.ThucDonController;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class ChiTietQuanAnActivity extends AppCompatActivity {
    TextView txtTenQuanAn, txtDiaChi, txtThoiGianHoatDong, txtTrangThaiHoatDong, txtTongSoHinhAnh, txtTongSoBinhLuan, txtTongSoCheckIn, txtTongSoLuuLai, txtTieuDeToolbar, txtGioiHanGia, txtTenWifi, txtMatKhauWifi, txtNgayDangWifi;
    ImageView imHinhAnhQuanAn, imgPlayTrailer;
    Button btnBinhLuan;
    LinearLayout khungWifi;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan;
    ApdaterBinhLuan adapterBinhLuan;
    GoogleMap googleMap;
    MapFragment mapFragment;
    LinearLayout khungTienIch;
    View khungTinhNang;
    VideoView videoView;
    RecyclerView recyclerThucDon;

    ChiTietQuanController chiTietQuanController;
    ThucDonController thucDonController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_chitietquanan);
        quanAnModel = getIntent().getParcelableExtra("quanan");

        txtTenQuanAn = findViewById(R.id.txtTenQuanAn);
        txtDiaChi = findViewById(R.id.txtDiaChiQuanAn);
        txtThoiGianHoatDong = findViewById(R.id.txtThoiGianHoatDong);
        txtTrangThaiHoatDong = findViewById(R.id.txtTrangThaiHoatDong);
        txtTongSoBinhLuan = findViewById(R.id.tongSoBinhLuan);
        txtTongSoCheckIn = findViewById(R.id.tongSoCheckIn);
        txtTongSoHinhAnh = findViewById(R.id.tongSoHinhAnh);
        txtTongSoLuuLai = findViewById(R.id.tongSoLuuLai);
        imHinhAnhQuanAn = findViewById(R.id.imHinhQuanAn);
        txtTieuDeToolbar = findViewById(R.id.txtTieuDeToolbar);
        txtGioiHanGia = findViewById(R.id.txtGioiHanGia);
        toolbar = findViewById(R.id.toolbar);
        recyclerViewBinhLuan = findViewById(R.id.recyclerBinhLuanChiTietQuanAn);
        //mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        khungTienIch = findViewById(R.id.khungTienTich);
        txtTenWifi = findViewById(R.id.txtTenWifi);
        txtMatKhauWifi = findViewById(R.id.txtMatKhauWifi);
        khungWifi = findViewById(R.id.khungWifi);
        txtNgayDangWifi = findViewById(R.id.txtNgayDangWifi);
        khungTinhNang = findViewById(R.id.khungTinhNang);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        videoView = findViewById(R.id.videoTrailer);
//        imgPlayTrailer = findViewById(R.id.imgPLayTrailer);
        recyclerThucDon = findViewById(R.id.recyclerThucDon);
    }

    @Override
    protected void onStart() {
        super.onStart();

        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua() + "-" + quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + "");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
    }
}