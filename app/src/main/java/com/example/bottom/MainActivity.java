package com.example.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import dataBase.HistoryDatabase;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mBottomNavigationView;

    Button btnExchange,so0,so1,so2,so3,so4,so5,so6,so7,so8,so9,xoa,daucham,btnConvert;
    TextView txtOutput,txtInput;
    Spinner spin;
    Double re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allWidget();

        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        mBottomNavigationView.setSelectedItemId(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.home:{
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.setting:{
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.history:{
                        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }
                    case R.id.switchnight:{
                        Intent intent = new Intent(MainActivity.this, nightMode.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        break;
                    }

                    case R.id.exit:{
                        new AlertDialog.Builder(MainActivity.this)
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



        so0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { txtInput.setText(txtInput.getText().toString()+"0"); }
        });

        so1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"1");
            }
        });

        so2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"2");
            }
        });

        so3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"3");
            }
        });

        so4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"4");
            }
        });

        so5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"5");
            }
        });

        so6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"6");
            }
        });

        so7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"7");
            }
        });

        so8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"8");
            }
        });

        so9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+"9");
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText("");
                txtOutput.setText("");
            }
        });

        daucham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtInput.setText(txtInput.getText().toString()+".");
            }
        });


        String[] currency = {"VND","USD","EUR","KRW","JPY","TWD","KHR"};
        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,currency);
        spin.setAdapter(adapter);

        btnExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dulieu = txtInput.getText().toString();
                re = Double.parseDouble(dulieu);
                String chuoi="";
                final int moneyArrVND[] = {500000,200000,100000,50000,20000,10000,5000,2000,1000};
                int checkVND[] = {0,0,0,0,0,0,0,0,0};
                final int moneyUSD[] = {100,50,20,10,5,2,1,50,25,10,5,1};
                int checkUSD[] = {0,0,0,0,0,0,0,0,0,0,0,0};
                final int moneyKRW[] = {50000,10000,5000,1000,500,100,50,10,5,1};
                int checkKRW[] = {0,0,0,0,0,0,0,0,0,0};
                final int moneyJP[] = {10000,5000,2000,1000,500,100,50,10,5,1};
                int checkJP[] = {0,0,0,0,0,0,0,0,0,0};
                final int moneyTW[] = {2000,1000,500,200,100,50,20,10,5,1};
                int checkTW[] = {0,0,0,0,0,0,0,0,0,0};
                final int moneyCAM[] = {100000,50000,20000,10000,5000,2000,1000,500,100,50,20,10,5,2,1};
                int checkCAM[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                final int moneyEUR[] = {500,200,100,50,20,10,5,2,1,50,20,10,5,2,1};
                int checkEUR[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                if(spin.getSelectedItem().toString() == "VND")
                {
                    for(int i=0;i<9;i++)
                    {
                        while( re >= moneyArrVND[i])
                        {
                            checkVND[i]+=1;
                            re -= moneyArrVND[i];
                        }
                    }

                    boolean checked = false;
                    Locale localeVN = new Locale("vi", "VN");
                    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                    for(int i=0;i<9;i++)
                    {
                        if(checked == false && checkVND[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+=currencyVN.format(moneyArrVND[i])+": "+checkVND[i]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "USD")
                {
                    for(int i=0;i<7;i++)
                    {
                        while( re >= moneyUSD[i])
                        {
                            checkUSD[i]+=1;
                            re -= moneyUSD[i];
                        }
                    }
                    re = re * 100.0001;
                    for(int j=7;j<12;j++)
                    {
                        while(re >= moneyUSD[j])
                        {
                            checkUSD[j]+=1;
                            re-=moneyUSD[j];
                        }
                    }

                    boolean checked = false;
                    for(int i=0;i<7;i++)
                    {
                        if(checked == false && checkUSD[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+="$ "+moneyUSD[i]+" : "+checkUSD[i]+"\n";
                        }
                    }
                    for(int j=7;j<12;j++)
                    {
                        if(checked == false && checkUSD[j]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi += "¢ "+moneyUSD[j]+" : "+checkUSD[j]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "KRW")
                {
                    for(int i=0;i<10;i++)
                    {
                        while( re >= moneyKRW[i])
                        {
                            checkKRW[i]+=1;
                            re -= moneyKRW[i];
                        }
                    }

                    Locale localeKR = new Locale("kr", "KR");
                    NumberFormat currencyKR = NumberFormat.getCurrencyInstance(localeKR);
                    boolean checked = false;
                    for(int i=0;i<10;i++)
                    {
                        if(checked == false && checkKRW[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+=currencyKR.format(moneyKRW[i])+": "+checkKRW[i]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "JPY")
                {
                    for(int i=0;i<10;i++)
                    {
                        while( re >= moneyJP[i])
                        {
                            checkJP[i]+=1;
                            re -= moneyJP[i];
                        }
                    }
                    boolean checked = false;
                    for(int i=0;i<10;i++)
                    {
                        if(checked == false && checkJP[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+="¥ "+moneyJP[i]+": "+checkJP[i]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "TWD")
                {
                    for(int i=0;i<10;i++)
                    {
                        while( re >= moneyTW[i])
                        {
                            checkTW[i]+=1;
                            re -= moneyTW[i];
                        }
                    }

                    boolean checked = false;
                    for(int i=0;i<10;i++)
                    {
                        if(checked == false && checkTW[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+="NT$ "+moneyTW[i]+" : "+checkTW[i]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "KHR")
                {
                    for(int i=0;i<15;i++)
                    {
                        while( re >= moneyCAM[i])
                        {
                            checkCAM[i]+=1;
                            re -= moneyCAM[i];
                        }
                    }

                    boolean checked = false;
                    for(int i=0;i<15;i++)
                    {
                        if(checked == false && checkCAM[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+=moneyCAM[i]+" Riels: "+checkCAM[i]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
                else if(spin.getSelectedItem().toString() == "EUR")
                {
                    for(int i=0;i<9;i++)
                    {
                        while( re >= moneyEUR[i])
                        {
                            checkEUR[i]+=1;
                            re -= moneyEUR[i];
                        }
                    }
                    re = re * 100.0001;
                    for(int j=9;j<15;j++)
                    {
                        while(re >= moneyEUR[j])
                        {
                            checkEUR[j]+=1;
                            re-=moneyEUR[j];
                        }
                    }

                    boolean checked = false;
                    for(int i=0;i<9;i++)
                    {
                        if(checked == false && checkEUR[i]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi+="€ "+moneyEUR[i]+" : "+checkEUR[i]+"\n";
                        }
                    }
                    for(int j=9;j<15;j++)
                    {
                        if(checked == false && checkEUR[j]==0)
                        {
                            continue;
                        }
                        else
                        {
                            checked = true;
                            chuoi += "¢ "+moneyEUR[j]+" : "+checkEUR[j]+"\n";
                        }
                    }
                    addMoney();
                    Toast.makeText(getApplicationContext(),"Exchange Success !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(chuoi);
                }
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double totalConvertAmount;
                Double amount = Double.parseDouble(txtInput.getText().toString());
                if(spin.getSelectedItem().toString() == "VND")
                {
                    
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(txtInput.getText().toString() +" VND");
                }
                if(spin.getSelectedItem().toString() == "USD")
                {

                    totalConvertAmount = amount * 0.00004;
                    String total = String.format("%.2f",totalConvertAmount);
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();

                    txtOutput.setText("$ "+total);
                }
                if(spin.getSelectedItem().toString() == "EUR")
                {

                    totalConvertAmount = amount * 0.000041;
                    String total = String.format("%.2f",totalConvertAmount);
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();

                    txtOutput.setText("€ "+total);
                }
                if(spin.getSelectedItem().toString() == "KRW")
                {
                    totalConvertAmount = amount * 0.05752;
                    String total = String.format("%.2f",totalConvertAmount);
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();

                    txtOutput.setText("₩ "+total);
                }
                if(spin.getSelectedItem().toString() == "JPY")
                {
                    totalConvertAmount = amount * 0.00594;
                    String total = String.format("%.2f",totalConvertAmount);
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();

                    txtOutput.setText("¥ "+total);
                }
                if(spin.getSelectedItem().toString() == "TWD")
                {
                    totalConvertAmount = amount * 0.0013;
                    String total = String.format("%.2f",totalConvertAmount);
                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();

                    txtOutput.setText("NT$ "+total);
                }
                if(spin.getSelectedItem().toString() == "KHR")
                {
                    totalConvertAmount = amount * 0.01647;
                    String total = String.format("%.2f",totalConvertAmount);

                    Toast.makeText(getApplicationContext(),"Convert Completed !",Toast.LENGTH_SHORT).show();
                    txtOutput.setText(total+" Riels");
                }
            }
        });

    }
    private void addMoney() {
        String strMoney = spin.getSelectedItem().toString();
        String strPrice = txtInput.getText().toString().trim();

        if (TextUtils.isEmpty(strMoney)||TextUtils.isEmpty(strPrice)){
            return;
        }
        List<History> list = HistoryDatabase.getInstance(this).mHistoryDAO().getListMoney();
        if (list!=null||list.isEmpty()) {
            History history = new History(strMoney, strPrice);
            HistoryDatabase.getInstance(this).mHistoryDAO().insertMoney(history);
            Toast.makeText(this, "Added to History", Toast.LENGTH_SHORT).show();
        }else{
            History history = new History(strMoney, strPrice);
        }
    }

    public void allWidget(){

        txtInput = (TextView)findViewById(R.id.txtTitle);
        btnExchange = (Button)findViewById(R.id.buttonExchange);
        btnConvert = (Button) findViewById(R.id.buttonConvert);
        txtOutput = (TextView) findViewById(R.id.textView);
        so0 = (Button)findViewById(R.id.so0);
        so1 = (Button)findViewById(R.id.so1);
        so2 = (Button)findViewById(R.id.so2);
        so3 = (Button)findViewById(R.id.so3);
        so4 = (Button)findViewById(R.id.so4);
        so5 = (Button)findViewById(R.id.so5);
        so6 = (Button)findViewById(R.id.so6);
        so7 = (Button)findViewById(R.id.so7);
        so8 = (Button)findViewById(R.id.so8);
        so9 = (Button)findViewById(R.id.so9);
        xoa = (Button)findViewById(R.id.C);
        daucham = (Button)findViewById(R.id.daucham);
        spin = (Spinner)findViewById(R.id.spinner);
        txtOutput.setMovementMethod(new ScrollingMovementMethod());
    }
}