package admin.phuc141.com.roomdatabase.Model.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Observable;

@Dao
public interface SinhvienDao {

    @Query("SELECT * FROM sinhvien")
    Observable<List<Sinhvien>> getAllListObservable();
    @Insert()
    List<Long> insertSinhvien (Sinhvien ...sinhviens);
    @Delete
    public void delete(Sinhvien sinhvien);
    @Update
    public void update(Sinhvien sinhvien);

}
