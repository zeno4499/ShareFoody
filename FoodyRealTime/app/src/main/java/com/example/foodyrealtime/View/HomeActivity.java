package com.example.foodyrealtime.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.foodyrealtime.Adapter.AdapterViewPagerHomeApp;
import com.example.foodyrealtime.R;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    ViewPager viewPagerHomeApp;
    RadioButton rd_odau;
    RadioButton rd_angi;
    RadioGroup radioGroup_oDau_anGi;
    ImageView themQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPagerHomeApp = findViewById(R.id.viewPagerHomeApp);
        rd_angi = findViewById(R.id.angi);
        rd_odau = findViewById(R.id.odau);
        radioGroup_oDau_anGi = findViewById(R.id.radioGroup_anGi_oDau);
        themQuanAn = findViewById(R.id.themQuanAn);

        AdapterViewPagerHomeApp adapterViewPagerHomeApp = new AdapterViewPagerHomeApp(getSupportFragmentManager());
        viewPagerHomeApp.setAdapter(adapterViewPagerHomeApp);
        radioGroup_oDau_anGi.setOnCheckedChangeListener(this);
        viewPagerHomeApp.addOnPageChangeListener(this);
        themQuanAn.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.themQuanAn: {
                Intent iThemQuanAn = new Intent(this, ThemQuanAnActivity.class);
                startActivity(iThemQuanAn);
                break;
            }
        }
    }
}