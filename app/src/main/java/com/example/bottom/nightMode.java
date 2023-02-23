package com.example.bottom;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class nightMode extends AppCompatActivity {
    Switch mSwitch;
    boolean nightMode;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    BottomNavigationView mBottomNavigationView;

    private final String CHANNEL_ID = "Night mode is on";
    private final int NOTIFICATION_ID =001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nightmode);

        mSwitch = findViewById(R.id.switch1);
        mSharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = mSharedPreferences.getBoolean("night",false);
        if (nightMode){
            mSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    mEditor = mSharedPreferences.edit();
                    mEditor.putBoolean("night",false);

                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    mEditor = mSharedPreferences.edit();
                    mEditor.putBoolean("night",true);


                }
                mEditor.apply();
            }
        });


        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.home:{
                        Intent intent = new Intent(nightMode.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.setting:{
                        Intent intent = new Intent(nightMode.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.history:{
                        Intent intent = new Intent(nightMode.this, HistoryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.switchnight:{
                        Intent intent = new Intent(nightMode.this, nightMode.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }

                    case R.id.exit:{
                        new AlertDialog.Builder(nightMode.this)
                                .setTitle(R.string.exit_caption)
                                .setMessage(R.string.exit_content)
                                .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finishAffinity();

                                    }
                                })
                                .setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                }).show();
                        break;
                    }
                }

                return  true;
            }
        });
    }

}
