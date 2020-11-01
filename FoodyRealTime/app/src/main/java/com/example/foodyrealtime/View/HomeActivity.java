package com.example.foodyrealtime.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foodyrealtime.Adapter.AdapterViewPagerHomeApp;
import com.example.foodyrealtime.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    ViewPager viewPagerHomeApp;
    RadioButton rd_odau;
    RadioButton rd_angi;
    RadioGroup radioGroup_oDau_anGi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPagerHomeApp = findViewById(R.id.viewPagerHomeApp);
        rd_angi = findViewById(R.id.angi);
        rd_odau = findViewById(R.id.odau);
        radioGroup_oDau_anGi = findViewById(R.id.radioGroup_anGi_oDau);

        AdapterViewPagerHomeApp adapterViewPagerHomeApp = new AdapterViewPagerHomeApp(getSupportFragmentManager());
        viewPagerHomeApp.setAdapter(adapterViewPagerHomeApp);
        radioGroup_oDau_anGi.setOnCheckedChangeListener(this);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.angi: {
                viewPagerHomeApp.setCurrentItem(1);
                break;
            }
            case R.id.odau: {
                viewPagerHomeApp.setCurrentItem(0);
                break;
            }
        }
    }
}