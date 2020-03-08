package admin.phuc141.com.roomdatabase;

import admin.phuc141.com.roomdatabase.ViewModel.MainViewModel;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Observer;

public class UpdateActivity extends AppCompatActivity {
    Toolbar toolbar;
    MainViewModel mainViewModel;
    EditText medtten, medtnamsinh, medtquequan;
    Button mEdit, mdelete;
    String mten, mnamsinh, mquequan, mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mapview();
        init();
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mten = medtten.getText().toString();
               mnamsinh = medtnamsinh.getText().toString();
               mquequan = medtquequan.getText().toString();
               if(mten.equals("")||mnamsinh.equals("")||mquequan.equals("")){
                   Toast.makeText(UpdateActivity.this, "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
               }
               else {
                   Intent intent = new Intent();
                   intent.putExtra("id", mid);
                   intent.putExtra("ten", mten);
                   intent.putExtra("namsinh", mnamsinh);
                   intent.putExtra("quequan", mquequan);
                   setResult(RESULT_OK, intent);
                   finish();
               }
            }
        });

        mdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medtten.setText("");
                medtnamsinh.setText("");
                medtquequan.setText("");

            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        Intent intent = getIntent();
        mid = intent.getStringExtra("idsinhvien");
        mten=intent.getStringExtra("ten");
        mnamsinh=intent.getStringExtra("namsinh");
        mquequan=intent.getStringExtra("quequan");
        medtten.setText(mten);
        medtnamsinh.setText(mnamsinh);
        medtquequan.setText(mquequan);
        getSupportActionBar().hide();
        setActionBar(toolbar);
        setTitle("UPDATE SINH VIÊN");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

    }


    private void mapview() {
        toolbar = findViewById(R.id.idudToolbar);
        medtten = findViewById(R.id.edtudTensv);
        medtnamsinh = findViewById(R.id.edtudNamsinh);
        medtquequan = findViewById(R.id.edtudQuequan);
        mEdit = findViewById(R.id.btnudThem);
        mdelete = findViewById(R.id.btnudXoa);
    }
}
