package com.example.foodyrealtime.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterRecyclerHinhBinhLuan;
import com.example.foodyrealtime.Model.BinhLuanModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HienThiChiTietBinhLuanActivity extends AppCompatActivity {
    CircleImageView circleImageView;
    TextView txtTieuDeBinhLuan, txtNoiDungBinhLuan, txtSoDiem;
    RecyclerView recyclerViewHinhBinhLuan;
    List<Bitmap> bitmapList;
    BinhLuanModel binhLuanModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_binhluan);
        circleImageView = findViewById(R.id.circleImageUser);
        txtTieuDeBinhLuan = findViewById(R.id.txtTieuDeBinhLuan);
        txtNoiDungBinhLuan = findViewById(R.id.txtNoiDungBinhLuan);
        txtSoDiem = findViewById(R.id.txtChamDiemBinhLuan);
        recyclerViewHinhBinhLuan = findViewById(R.id.recyclerHinhBinhLuan);

        bitmapList = new ArrayList<>();

        binhLuanModel = getIntent().getParcelableExtra("binhluanmodel");

        txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        txtSoDiem.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(circleImageView, binhLuanModel.getThanhVienModel().getHinhanh());

        for (String linkHinh : binhLuanModel.getHinhanhBinhLuanList()) {
            StorageReference storageReferenceHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkHinh);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageReferenceHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmapList.add(bitmap);
                    if (bitmapList.size() == binhLuanModel.getHinhanhBinhLuanList().size()) {
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(HienThiChiTietBinhLuanActivity.this, R.layout.custom_layout_hinhbinhluan, bitmapList, binhLuanModel, true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiChiTietBinhLuanActivity.this, 2);
                        recyclerViewHinhBinhLuan.setLayoutManager(layoutManager);
                        recyclerViewHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkHinh) {
        StorageReference storageReferenceHinhUser = FirebaseStorage.getInstance().getReference()
                .child("thanhvien")
                .child(linkHinh);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageReferenceHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }
    }

