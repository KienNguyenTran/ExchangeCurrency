package com.example.bottom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

import dataBase.MoneyDatabase;

public class SettingActivity extends AppCompatActivity {
    BottomNavigationView mBottomNavigationView;

    private static final int REQUESTCODE = 10;

    private MoneyAdapter mMoneyAdapter;
    private List<Money> mMoneyList;


    EditText editmoney,editprice;
    Button btn_add;
    private RecyclerView rcvMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        editmoney = findViewById(R.id.editcurrency);
        editprice = findViewById(R.id.editprice);
        rcvMoney = findViewById(R.id.rcv_money);
        btn_add = findViewById(R.id.btn_add);


        mMoneyAdapter = new MoneyAdapter(new MoneyAdapter.ClickItemMoney() {
            @Override
            public void updateMoney(Money money) {
                clickUpdateMoney(money);
            }

            @Override
            public void deleteMoney(Money money) {
                clickDeleteMoney(money);
            }
        });
        mMoneyList = new ArrayList<>();

        mMoneyAdapter.setData(mMoneyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvMoney.setLayoutManager(linearLayoutManager);
        rcvMoney.setAdapter(mMoneyAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney();
            }
        });
        loadData();



        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.home:{
                        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.setting:{
                        Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.history:{
                        Intent intent = new Intent(SettingActivity.this, HistoryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.switchnight:{
                        Intent intent = new Intent(SettingActivity.this, nightMode.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.exit:{
                        new AlertDialog.Builder(SettingActivity.this)
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
    private void clickDeleteMoney(Money money) {
        new AlertDialog.Builder(this).setTitle("Comfirm delete Money")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MoneyDatabase.getInstance(SettingActivity.this).mMoneyDAO().deleteMoney(money);
                        Toast.makeText(SettingActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void clickUpdateMoney(Money money) {
        Intent intent = new Intent(SettingActivity.this,UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_money",money);
        intent.putExtras(bundle);
        startActivityForResult(intent,REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }

    private void addMoney() {
        String strMoney = editmoney.getText().toString().trim();
        String strPrice = editprice.getText().toString().trim();
        if (TextUtils.isEmpty(strMoney)||TextUtils.isEmpty(strPrice)){
            return;
        }
        Money money = new Money(strMoney,strPrice);
        if (isMoneyExist(money)){
            Toast.makeText(this, "Existed", Toast.LENGTH_SHORT).show();
            return;
        }
        MoneyDatabase.getInstance(this).mMoneyDAO().insertMoney(money);
        Toast.makeText(this, "Added Success", Toast.LENGTH_SHORT).show();
        editmoney.setText("");
        editprice.setText("");
        loadData();
    }

    public void loadData(){
        mMoneyList = MoneyDatabase.getInstance(this).mMoneyDAO().getListMoney();
        mMoneyAdapter.setData(mMoneyList);
    }

    public boolean isMoneyExist(Money money){
        List<Money> list = MoneyDatabase.getInstance(this).mMoneyDAO().checkData(money.getMoney(),money.getPrice());
        return list != null  && !list.isEmpty();
    }


}