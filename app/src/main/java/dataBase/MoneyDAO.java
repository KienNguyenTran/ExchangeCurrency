package dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bottom.Money;

import java.util.List;

@Dao
public interface MoneyDAO {
    @Insert
    void insertMoney(Money money);

    @Query("select * from money_table")
    List<Money> getListMoney();

    @Query("select * from money_table where money= :money and price= :price")
    List<Money> checkData(String money,String price);

    @Query("select * from money_table where money= :currency")
    public List<Money> checkCurrency(String currency);

    @Update
    void updateMoney(Money money);

    @Delete
    void deleteMoney(Money money);

}
