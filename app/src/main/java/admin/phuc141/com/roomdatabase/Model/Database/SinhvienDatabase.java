package admin.phuc141.com.roomdatabase.Model.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sinhvien.class},version = 1,exportSchema = false)
public abstract class SinhvienDatabase extends RoomDatabase {

    private static SinhvienDatabase sinhvienDatabase=null;

    //khai báo 1 hàm sinhvienDao
    public abstract SinhvienDao sinhvienDao();
    //tạo hàm khởi tạo SinhvienDatabase

    public static SinhvienDatabase getInstance (Context context){
        if (sinhvienDatabase==null){
            sinhvienDatabase = Room.databaseBuilder(
                    context,
                    SinhvienDatabase.class,
                    "Quanlysinhvien")
                    .build();
        }
        return sinhvienDatabase;
    }
}
