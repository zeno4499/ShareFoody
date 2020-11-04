package com.example.foodyrealtime.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Controller.ODauController;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;

import java.util.List;

public class ODauFragment extends Fragment {
    ODauController oDauController;
    RecyclerView recyclerODau;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_o_dau, container, false);
        recyclerODau = view.findViewById(R.id.recycleODau);
        progressBar = view.findViewById(R.id.progress_bar_ODAu);
        nestedScrollView = view.findViewById(R.id.nestScrollViewODau);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longtitude", "0")));
        oDauController = new ODauController(getContext());
        oDauController.getDanhSachQuanAnController(nestedScrollView, recyclerODau, progressBar, vitrihientai);
    }
}