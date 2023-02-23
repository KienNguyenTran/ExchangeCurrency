package com.example.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dataBase.MoneyDatabase;


public class UpdateActivity extends AppCompatActivity {
    EditText editmoney,editprice;
    Button btn_update_money;
    BottomNavigationView mBottomNavigationView;

    private Money mMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editmoney = findViewById(R.id.editcurrency);
        editprice = findViewById(R.id.editprice);
        btn_update_money = findViewById(R.id.btn_update_money);

        mMoney = (Money) getIntent().getExtras().get("object_money");
        if (mMoney != null){
            editmoney.setText(mMoney.getMoney());
            editprice.setText(mMoney.getPrice());
        }
        btn_update_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMoney();
            }
        });
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.home:{
                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.setting:{
                        Intent intent = new Intent(UpdateActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.history:{
                        Intent intent = new Intent(UpdateActivity.this, HistoryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.switchnight:{
                        Intent intent = new Intent(UpdateActivity.this, nightMode.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }

                    case R.id.exit:{
                        new AlertDialog.Builder(UpdateActivity.this)
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

    private void updateMoney() {
        String strMoney = editmoney.getText().toString().trim();
        String strPrice = editprice.getText().toString().trim();
        if (TextUtils.isEmpty(strMoney)||TextUtils.isEmpty(strPrice)){
            return;
        }
        mMoney.setMoney(strMoney);
        mMoney.setPrice(strPrice);
        MoneyDatabase.getInstance(this).mMoneyDAO().updateMoney(mMoney);
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        Intent intentresult = new Intent();
        setResult(Activity.RESULT_OK,intentresult);
        finish();
    }
}