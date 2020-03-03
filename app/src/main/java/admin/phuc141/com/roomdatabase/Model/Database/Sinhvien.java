package admin.phuc141.com.roomdatabase.Model.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sinhvien")
public class Sinhvien {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String Ten;
    private Integer NamSinh;
    private String QueQuan;

    public Sinhvien() {
    }

    public Sinhvien(String ten, Integer namSinh, String queQuan) {
        Ten = ten;
        NamSinh = namSinh;
        QueQuan = queQuan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public Integer getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(Integer namSinh) {
        NamSinh = namSinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }
}
