package com.example.bottom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "history_table")
public class History implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String money;
    private String price;


    public History(String money, String price) {

        this.money = money;
        this.price = price;

    }

    @Override
    public String toString() {
        return getMoney();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
