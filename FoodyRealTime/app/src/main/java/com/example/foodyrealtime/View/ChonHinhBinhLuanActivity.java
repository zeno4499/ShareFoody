package com.example.foodyrealtime.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterChonHinhBinhLuan;
import com.example.foodyrealtime.Model.ChonHinhBinhLuanModel;
import com.example.foodyrealtime.R;

import java.util.ArrayList;
import java.util.List;

public class ChonHinhBinhLuanActivity extends AppCompatActivity implements View.OnClickListener {
    List<ChonHinhBinhLuanModel> listDuongDan;
    List<String> listHinhDuocChon;
    RecyclerView recyclerViewChonHinhBinhLuan;
    AdapterChonHinhBinhLuan adapterChonHinhBinhLuan;
    TextView txtXong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_hinh_binh_luan);
        listDuongDan = new ArrayList<>();
        listHinhDuocChon = new ArrayList<>();
        recyclerViewChonHinhBinhLuan = findViewById(R.id.recyclerChonHinhBinhLuan);
        txtXong = findViewById(R.id.txtXong);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        adapterChonHinhBinhLuan = new AdapterChonHinhBinhLuan(this,
                R.layout.custom_layout_chonhinhbinhluan, listDuongDan);
        recyclerViewChonHinhBinhLuan.setLayoutManager(layoutManager);
        recyclerViewChonHinhBinhLuan.setAdapter(adapterChonHinhBinhLuan);

        int checkReadExStoreage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (checkReadExStoreage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            getTatCaCacHinhTrongTheNho();
        }
        txtXong.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getTatCaCacHinhTrongTheNho();
            }
        }
    }

    public void getTatCaCacHinhTrongTheNho() {
        String[] projection = {MediaStore.Images.Media.DATA};
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String duongDan = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            ChonHinhBinhLuanModel chonjHinhBinhLuanModel = new ChonHinhBinhLuanModel(duongDan, false);

            listDuongDan.add(chonjHinhBinhLuanModel);
            adapterChonHinhBinhLuan.notifyDataSetChanged();
            cursor.moveToNext();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtXong: {
                for (ChonHinhBinhLuanModel value : listDuongDan) {
                    if (value.isCheck()) {
                        listHinhDuocChon.add(value.getDuongDan());
                    }
                }

                Intent data = new Intent();
                data.putStringArrayListExtra("listHinhDuocChon", (ArrayList<String>) listHinhDuocChon);
                setResult(RESULT_OK, data);
                finish();
                break;
            }
        }
    }
}