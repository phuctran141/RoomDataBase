package admin.phuc141.com.roomdatabase;

import admin.phuc141.com.roomdatabase.Model.Database.Sinhvien;
import admin.phuc141.com.roomdatabase.ViewModel.MainViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;

public class InsertActivity extends AppCompatActivity {
    Toolbar toolbar;
    MainViewModel mainViewModel;
    Button mbtnThem, mbtnXoa;
    EditText mEdtTen, medtNamsinh, medtQuequan;
    String mten, mquequan;
    String mNamsinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        mapview();
        init();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        mbtnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mten = mEdtTen.getText().toString();
                mquequan = medtQuequan.getText().toString();
                mNamsinh = medtNamsinh.getText().toString();
                if(mten.equals("")|| mquequan.equals("")|| mNamsinh.equals("")){

                    Toast.makeText(InsertActivity.this, "Chưa nhập thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent =new Intent();
                    intent.putExtra("ten",mten);
                    intent.putExtra("namsinh",mNamsinh);
                    intent.putExtra("quequan",mquequan);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
        mbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medtNamsinh.setText("");
                medtQuequan.setText("");
                mEdtTen.setText("");
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.idToolbar);
        getSupportActionBar().hide();
        setActionBar(toolbar);
        setTitle("THÊM SINH VIÊN");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        mainViewModel = new MainViewModel();
    }
    private void mapview() {
        mbtnThem =findViewById(R.id.btnThem);
        mbtnXoa= findViewById(R.id.btnXoa);
        mEdtTen= findViewById(R.id.edtTensv);
        medtNamsinh= findViewById(R.id.edtNamsinh);
        medtQuequan= findViewById(R.id.edtQuequan);
    }
}
