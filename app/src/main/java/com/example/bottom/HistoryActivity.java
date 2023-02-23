package com.example.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import dataBase.HistoryDatabase;

public class HistoryActivity extends AppCompatActivity {
    BottomNavigationView mBottomNavigationView;
    private RecyclerView rcv;
    private HistoryAdapter mHistoryAdapter;
    private List<History> mHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rcv = findViewById(R.id.rcv_view);



        mHistoryAdapter = new HistoryAdapter(new HistoryAdapter.IClickItemHistory(){
            @Override
            public void deleteHistory(History history) {
                clickDeleteHistory(history);
            }

        });



        mHistoryList = new ArrayList<>();
        mHistoryAdapter.setData(mHistoryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(mHistoryAdapter);
        loadData();


        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.home:{
                        Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.setting:{
                        Intent intent = new Intent(HistoryActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.history:{
                      return true;
                    }
                    case R.id.switchnight:{
                        Intent intent = new Intent(HistoryActivity.this, nightMode.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.exit:{
                        new AlertDialog.Builder(HistoryActivity.this)
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

                return true;
            }
        });

    }
    public void loadData(){
        mHistoryList = HistoryDatabase.getInstance(this).mHistoryDAO().getListMoney();
        mHistoryAdapter.setData(mHistoryList);
    }

    private void clickDeleteHistory(History history) {
        new AlertDialog.Builder(this).setTitle("Comfirm delete History")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HistoryDatabase.getInstance(HistoryActivity.this).mHistoryDAO().deleteHistory(history);
                        Toast.makeText(HistoryActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}