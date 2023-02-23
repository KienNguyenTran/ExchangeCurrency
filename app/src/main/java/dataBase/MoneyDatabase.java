package dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bottom.Money;


@Database(entities = {Money.class},version = 1)
public  abstract class MoneyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "money.db";
    private static MoneyDatabase instance;


    public static synchronized MoneyDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MoneyDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract MoneyDAO mMoneyDAO();

}
