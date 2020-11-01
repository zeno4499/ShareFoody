package com.example.foodyrealtime.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foodyrealtime.View.Fragments.AnGiFragment;
import com.example.foodyrealtime.View.Fragments.ODauFragment;

public class AdapterViewPagerHomeApp extends FragmentPagerAdapter {
    AnGiFragment anGiFragment;
    ODauFragment oDauFragment;

    public AdapterViewPagerHomeApp(@NonNull FragmentManager fm) {
        super(fm);
        anGiFragment = new AnGiFragment();
        oDauFragment = new ODauFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return oDauFragment;

            }
            case 1: {
                return anGiFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;//2 item
    }
}
