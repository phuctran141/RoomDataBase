package admin.phuc141.com.roomdatabase;

import admin.phuc141.com.roomdatabase.Model.Database.Bisiness.SinhvieAdapter;
import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.ViewModel.MainViewModel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    MainViewModel mainViewModel;
    List<Sinhvien> arraysinhvien;
    SinhvieAdapter msinhvieAdapter;
    RecyclerView mRecyclerview;
    List<Sinhvien> msinhvien;
    int REQUEST_CODE_INSERT =123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        msinhvien= new ArrayList<>();
        mainViewModel = new MainViewModel();
        getLifecycle().addObserver(mainViewModel);
        mainViewModel.getAllSinhvien(this);
        mainViewModel.getAllSinhvienSuccess().observe(this, new Observer<List<Sinhvien>>() {
            @Override
            public void onChanged(List<Sinhvien> sinhviens) {
                msinhvien=sinhviens;
               // Log.d("BBB",msinhvien[0]);
            }
        });

        msinhvieAdapter = new SinhvieAdapter(this,msinhvien);
        mRecyclerview.setAdapter(msinhvieAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    //  Log.d("BBB", msinhvien.size()+"");
        setListener();
    }

    private void setListener() {
       // mainViewModel.getAllSinhvienSuccess().observe(this,);
      //  mainViewModel.insertSinhvien(this, new Sinhvien[]{new Sinhvien("Nguyễn Văn A", 1990, "Quận 1")});

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.item_insert){
            Intent intent = new Intent(MainActivity.this,InsertActivity.class);
            startActivityForResult(intent,REQUEST_CODE_INSERT);
            Toast.makeText(this, "đã click insert", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_INSERT && resultCode==RESULT_OK && data!=null){
            String ten = data.getStringExtra("ten");
            Integer namsinh = Integer.parseInt(data.getStringExtra("namsinh"));
            String quequan= data.getStringExtra("quequan");
            Sinhvien sinhvien =new Sinhvien(ten,namsinh,quequan);
            msinhvien.add(sinhvien);
            mainViewModel.insertSinhvien(this, sinhvien);
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
