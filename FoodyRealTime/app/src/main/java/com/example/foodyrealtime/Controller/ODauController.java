package com.example.foodyrealtime.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterRecyclerODau;
import com.example.foodyrealtime.Controller.Interface.ODauInterface;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ODauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerODau adapterRecyclerODau;
    int itemDaCo = 3;

    public ODauController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(Context context, NestedScrollView nestedScrollView, final RecyclerView recyclerViewODau, final ProgressBar progressBar, final Location viTriHienTai) {

        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewODau.setLayoutManager(layoutManager);
        adapterRecyclerODau = new AdapterRecyclerODau(context, quanAnModelList,
                R.layout.custom_layout_recyclerview_odau);
        recyclerViewODau.setAdapter(adapterRecyclerODau);

        progressBar.setVisibility(View.VISIBLE);
        final ODauInterface oDauInterface = new ODauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                final List<Bitmap> bitmaps = new ArrayList<>();
                for (String linkHinh : quanAnModel.getHinhanhquanan()) {

                    StorageReference storageReferenceHinhAn = FirebaseStorage.getInstance().getReference()
                            .child("hinhanh").child(linkHinh);

                    final long ONE_MEGABYTE = 1024 * 1024;
                    storageReferenceHinhAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapsList(bitmaps);

                            if (quanAnModel.getBitmapsList().size() == quanAnModel.getHinhanhquanan().size()) {
                                quanAnModelList.add(quanAnModel);
                                adapterRecyclerODau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        };
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (v.getChildAt(v.getChildCount() - 1) != null) {
                    if (scrollY >= v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight()) {
                        itemDaCo += 3;
                        quanAnModel.getDanhSachQuanAn(oDauInterface, viTriHienTai, itemDaCo, itemDaCo - 3);
                    }
                }
            }
        });

        quanAnModel.getDanhSachQuanAn(oDauInterface, viTriHienTai, itemDaCo, 0);
    }
}
