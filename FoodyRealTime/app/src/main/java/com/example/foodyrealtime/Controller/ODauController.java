package com.example.foodyrealtime.Controller;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterRecyclerODau;
import com.example.foodyrealtime.Controller.Interface.ODauInterface;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;

import java.util.ArrayList;
import java.util.List;

public class ODauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerODau adapterRecyclerODau;

    public ODauController(Context context) {
        this.context = context;
        this.quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(final RecyclerView recyclerViewODau, final ProgressBar progressBar) {
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewODau.setLayoutManager(layoutManager);
        adapterRecyclerODau = new AdapterRecyclerODau(quanAnModelList,
                R.layout.custom_layout_recyclerview_odau);
        recyclerViewODau.setAdapter(adapterRecyclerODau);

        ODauInterface oDauInterface = new ODauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecyclerODau.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        };
        quanAnModel.getDanhSachQuanAn(oDauInterface);
    }
}
