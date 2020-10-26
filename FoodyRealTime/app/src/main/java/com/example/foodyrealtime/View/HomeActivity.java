package com.example.foodyrealtime.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;

import com.example.foodyrealtime.Adapter.AdapterViewPagerHomeApp;
import com.example.foodyrealtime.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager viewPagerHomeApp;
    RadioButton rd_odau;
    RadioButton rd_angi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPagerHomeApp = findViewById(R.id.viewPagerHomeApp);
        rd_angi = findViewById(R.id.angi);
        rd_odau = findViewById(R.id.odau);
        AdapterViewPagerHomeApp adapterViewPagerHomeApp = new AdapterViewPagerHomeApp(getSupportFragmentManager());
        viewPagerHomeApp.setAdapter(adapterViewPagerHomeApp);
        viewPagerHomeApp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0: {
                rd_odau.setChecked(true);
                break;
            }
            case 1: {
                rd_angi.setChecked(true);
                break;
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}