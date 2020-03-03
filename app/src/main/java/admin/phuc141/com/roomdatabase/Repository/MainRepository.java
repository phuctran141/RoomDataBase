package admin.phuc141.com.roomdatabase.Repository;

import android.content.Context;

import java.util.List;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.Model.Database.SinhvienDao;
import admin.phuc141.com.roomdatabase.Model.Database.SinhvienDatabase;
import io.reactivex.Observable;

public class MainRepository  {
    private static MainRepository mainRepository =null;
    public SinhvienDao sinhvienDao;
    private MainRepository(Context context){
        //KHởi tạo database
        SinhvienDatabase sinhvienDatabase = SinhvienDatabase.getInstance(context);
        sinhvienDao=sinhvienDatabase.sinhvienDao();

    }
    public static MainRepository getInstance(Context context){
        if(mainRepository==null){
            mainRepository = new MainRepository(context);
        }
        return mainRepository;
    }
    //tạo ra 1 rx call dữ liệu
    public Observable <List<Sinhvien>> getAllSinhvien(){
        return sinhvienDao.getAllListObservable();
    }
    //tạo hàm insertSinhvien trả về id sinh viên
    public List<Long> insertSinhvien(Sinhvien...sinhviens){
        return sinhvienDao.insertSinhvien(sinhviens);
    }
    public void deletesinhvien (Sinhvien sinhvien){
        sinhvienDao.delete(sinhvien);
    }
}
