package com.example.foodyrealtime.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodyrealtime.Adapter.AdapterPhoto;
import com.example.foodyrealtime.Controller.ODauController;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ODauFragment extends Fragment {
    ODauController oDauController;
    RecyclerView recyclerODau;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;

    ViewPager viewPager;
    CircleIndicator circleIndicator;
    private AdapterPhoto photoAdapter;
    private List<Bitmap> mListPhoto;
    private Timer mTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_o_dau, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerODau = view.findViewById(R.id.recycleODau);
        progressBar = view.findViewById(R.id.progress_bar_ODAu);
        nestedScrollView = view.findViewById(R.id.nestScrollViewODau);
        viewPager = view.findViewById(R.id.viewpager);
//        circleIndicator = view.findViewById(R.id.circleindicator);

        mListPhoto = getListPhoto();
        photoAdapter = new AdapterPhoto(getContext(), mListPhoto);
        viewPager.setAdapter(photoAdapter);

//        circleIndicator.setViewPager(viewPager);
//        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    @Override
    public void onStart() {
        super.onStart();
        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitdue", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longtitude", "0")));
        oDauController = new ODauController(getContext());
        oDauController.getDanhSachQuanAnController(getContext(), nestedScrollView, recyclerODau, progressBar, vitrihientai);
    }

    private List<Bitmap> getListPhoto() {
        final List<Bitmap> bitmaps = new ArrayList<>();
        List<Uri> linkHinh = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            StorageReference storageReferenceHinhAnhSlideShow = FirebaseStorage.getInstance().getReference()
                    .child("slideshow").child("img" + i + ".jpg");
            final long ONE_MEGABYTE = 1024 * 1024;
            storageReferenceHinhAnhSlideShow.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmaps.add(bitmap);
                    if (bitmaps.size() == 4) {
                        photoAdapter = new AdapterPhoto(getContext(), bitmaps);
                        viewPager.setAdapter(photoAdapter);
                        autoSlideImages();
                    }
                }
            });
        }
        return bitmaps;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void autoSlideImages() {
        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager == null) {
            return;
        }

        //innit timer
        if (mTimer == null) {
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }
}