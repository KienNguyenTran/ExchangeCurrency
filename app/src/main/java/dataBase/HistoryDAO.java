package dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bottom.History;

import java.util.List;

@Dao
public interface HistoryDAO {
    @Insert
    void insertMoney(History history);

    @Query("select * from  history_table")
    List<History> getListMoney();

    @Delete
    void deleteHistory(History history);



}
