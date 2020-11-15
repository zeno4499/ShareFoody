package com.example.foodyrealtime.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.foodyrealtime.R;

import java.util.List;

public class AdapterPhoto extends PagerAdapter {
    private Context context;
    private List<Bitmap> photoList;

    public AdapterPhoto(Context context, List<Bitmap> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.custom_layout_item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        Bitmap photo = photoList.get(position);
        if (photo != null) {
            //load anh len image view
            Glide.with(context).load(photo).into(imgPhoto);
        }
        //add new to view group
        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove view
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (photoList != null) {
            return photoList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
