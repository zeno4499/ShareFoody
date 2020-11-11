package com.example.foodyrealtime.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Adapter.AdapterThucDon;
import com.example.foodyrealtime.Controller.Interface.ThucDonInterface;
import com.example.foodyrealtime.Model.ThucDonModel;

import java.util.List;

public class ThucDonController {
    ThucDonModel thucDonModel;

    public ThucDonController() {
        thucDonModel = new ThucDonModel();
    }

    public void getDanhSachThucDonQuanAnTheoMa(final Context context, String manquanan, final RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ThucDonInterface thucDonInterface = new ThucDonInterface() {
            @Override
            public void getThucDonThanhCong(List<ThucDonModel> thucDonModelList) {
                AdapterThucDon adapterThucDon = new AdapterThucDon(context, thucDonModelList);
                recyclerView.setAdapter(adapterThucDon);
                adapterThucDon.notifyDataSetChanged();
            }
        };
        thucDonModel.getDanhSachThucDonQuanAnTheoMa(manquanan, thucDonInterface);
    }
}
