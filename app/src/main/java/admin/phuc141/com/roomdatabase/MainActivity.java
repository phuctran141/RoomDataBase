package admin.phuc141.com.roomdatabase;

import admin.phuc141.com.roomdatabase.Model.Database.Bisiness.SinhvienAdapter;
import admin.phuc141.com.roomdatabase.Model.Database.Bisiness.ontiemlistener;
import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.Repository.MainRepository;
import admin.phuc141.com.roomdatabase.ViewModel.MainViewModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    MainViewModel mainViewModel;
    List<Sinhvien> arraysinhvien;
    SinhvienAdapter msinhvienAdapter;
    RecyclerView mRecyclerview;
    List<Sinhvien> msinhvien;
    int REQUEST_CODE_INSERT =123;
    int REQUEST_CODE_UPDATE =234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mapview();
        obserData();
        setListener();
        msinhvienAdapter.setOnItemClick(new ontiemlistener() {
            @Override
            public void onitemclick(Sinhvien sinhvien, int positon) {
//                Toast.makeText(MainActivity.this, "vị trí"+sinhvien.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("idsinhvien",sinhvien.getId().toString());
                intent.putExtra("namsinh",sinhvien.getNamSinh().toString());
                intent.putExtra("quequan",sinhvien.getQueQuan());
                intent.putExtra("ten",sinhvien.getTen());
                startActivityForResult(intent,REQUEST_CODE_UPDATE);
            }
        });
       msinhvienAdapter.setDeleteClick(new ontiemlistener() {
           @Override
           public void onitemclick(Sinhvien sinhvien, int positon) {

               mainViewModel.DeleteSinhvien(MainActivity.this,sinhvien);
               msinhvien.remove(positon);
               msinhvienAdapter.notifyDataSetChanged();
           }
       });
    }
    private void obserData() {
        mainViewModel.getAllSinhvienSuccess().observe(this, new Observer<List<Sinhvien>>() {
            @Override
            public void onChanged(List<Sinhvien> sinhviens) {
                msinhvien.clear();
                msinhvien.addAll(sinhviens);
                msinhvienAdapter.notifyDataSetChanged();
            }
        });
    }

    private void mapview() {
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(msinhvienAdapter);

    }


    private void init() {
        msinhvien= new ArrayList<>();
        mainViewModel = new MainViewModel();
        getLifecycle().addObserver(mainViewModel);
        msinhvienAdapter = new SinhvienAdapter(this,msinhvien);
    }

    private void setListener() {
        mainViewModel.getAllSinhvien(this);
       // mainViewModel.getAllSinhvienSuccess().observe(this,);
      //  mainViewModel.insertSinhvien(this, new Sinhvien[]{new Sinhvien("Nguyễn Văn A", 1990, "Quận 1")});

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.item_insert){
            Intent intent = new Intent(MainActivity.this,InsertActivity.class);
            startActivityForResult(intent,REQUEST_CODE_INSERT);
//            Toast.makeText(this, "đã click insert", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id==R.id.item_delete){
            mainViewModel.deleteAllSinhvien(MainActivity.this);
            msinhvien.clear();
            msinhvienAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "đã click delete all sinhvien", Toast.LENGTH_SHORT).show();
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_INSERT && resultCode==RESULT_OK  && data!=null){
            String ten = data.getStringExtra("ten");
            Integer namsinh = Integer.parseInt(data.getStringExtra("namsinh"));
            String quequan= data.getStringExtra("quequan");
            Sinhvien sinhvien =new Sinhvien(ten,namsinh,quequan);
            msinhvien.add(sinhvien);
            mainViewModel.insertSinhvien(this, sinhvien);
            Log.d("BBB", msinhvien.size()+"");
        }
        if (requestCode==REQUEST_CODE_UPDATE && resultCode==RESULT_OK && data!=null){
          int mid = Integer.parseInt(data.getStringExtra("id"));
            String ten = data.getStringExtra("ten");
            Integer namsinh = Integer.parseInt(data.getStringExtra("namsinh"));
            String quequan= data.getStringExtra("quequan");
            Log.d("BBB", ""+ten + " "+namsinh+" " +quequan);
            Sinhvien sinhvien =new Sinhvien(ten,namsinh,quequan);
            sinhvien.setId(mid);
            mainViewModel.UpdateAsycsinhvien(this, sinhvien);
 //           mainViewModel.updateRoomSv(MainActivity.this,sinhvien);
            msinhvien.add(sinhvien);
            Log.d("BBB", msinhvien.size()+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
