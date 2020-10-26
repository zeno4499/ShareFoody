package com.example.foodyrealtime.View;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodyrealtime.R;

public class SlashScreenActivity extends AppCompatActivity {

    TextView txtVersion;
    TextView txtNameCty;
    TextView txtLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);
        txtVersion = findViewById(R.id.txtVersion);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtVersion.setText(getString(R.string.name_version) + " " + packageInfo.versionName);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent iLogin = new Intent(SlashScreenActivity.this, LoginActivity.class);
                    startActivity(iLogin);
                    finish();//khong cho back ve trang splash
                }
            }, 2000);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


}
