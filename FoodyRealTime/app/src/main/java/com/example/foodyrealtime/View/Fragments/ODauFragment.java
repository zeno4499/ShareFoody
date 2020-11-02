package com.example.foodyrealtime.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_o_dau, container, false);
        recyclerODau = view.findViewById(R.id.recycleODau);
        progressBar = view.findViewById(R.id.progress_bar_ODAu);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        oDauController = new ODauController(getContext());
        oDauController.getDanhSachQuanAnController(recyclerODau, progressBar);
    }
}