package com.example.foodyrealtime.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.ApdaterBinhLuan;
import com.example.foodyrealtime.Controller.ChiTietQuanController;
import com.example.foodyrealtime.Controller.ThucDonController;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity implements View.OnClickListener {
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
        btnBinhLuan.setOnClickListener(this);

        thucDonController = new ThucDonController();
        HienThiChiTietQuanAn();

        //set title toolbar về rỗng
        toolbar.setTitle("");
        //set tool bar thành action bar
        setSupportActionBar(toolbar);
        //mở mũi tên back trên tool bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // sự kiện back
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void HienThiChiTietQuanAn() {

        thucDonController.getDanhSachThucDonQuanAnTheoMa(this, quanAnModel.getMaquanan(), recyclerThucDon);
    }

    @Override
    protected void onStart() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        super.onStart();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String gioHientai = dateFormat.format(calendar.getTime());
        String gioMocua = quanAnModel.getGiomocua();
        String gioDongcua = quanAnModel.getGiodongcua();

        try {
            Date dateHientai = dateFormat.parse(gioHientai);
            Date dateMocua = dateFormat.parse(gioMocua);
            Date dateDongcua = dateFormat.parse(gioDongcua);
            if (dateHientai.after(dateMocua) && dateHientai.before(dateDongcua)) {
                //đang mở cửa
                txtTrangThaiHoatDong.setText(getString(R.string.dangmocua));

            } else {
                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtTieuDeToolbar.setText(quanAnModel.getTenquanan());
        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua() + "-" + quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + "");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");
        StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
        //down hình ảnh
        final long ONE_MEGABYTE = 1024 * 1024;
        String storageReferenceHinhAn;
        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imHinhAnhQuanAn.setImageBitmap(bitmap);
            }
        });
        //load danh sách bình luận quán ăn
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan = new ApdaterBinhLuan(this, R.layout.custom_layout_binhluan, quanAnModel.getBinhLuanModelList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnBinhLuan: {
                Intent iBinhLuan = new Intent(this, BinhLuanActivity.class);
                iBinhLuan.putExtra("maquanan", quanAnModel.getMaquanan());
                iBinhLuan.putExtra("tenquan", quanAnModel.getTenquanan());
                iBinhLuan.putExtra("diachi", quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
                startActivity(iBinhLuan);
                break;
            }
        }
    }
}