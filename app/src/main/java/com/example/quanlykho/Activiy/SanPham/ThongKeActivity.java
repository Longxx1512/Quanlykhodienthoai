package com.example.quanlykho.Activiy.SanPham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlykho.Activiy.SanPhamActivity;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;

public class ThongKeActivity extends AppCompatActivity {

    private EditText spnknn,spnkin,spxknn,spxkin;
    private Button btnExit;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        anhxa();
        dbHelper = new DBHelper(this);
        spnknn.setText(dbHelper.laySanPhamNhapNhieuNhat());
        spnkin.setText(dbHelper.laySanPhamNhapItNhat());
        spxknn.setText(dbHelper.laySanPhamXuatNhieuNhat());
        spxkin.setText(dbHelper.laySanPhamXuatItNhat());
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongKeActivity.this, SanPhamActivity.class));
            }
        });
    }

    private void anhxa() {
        spnknn = findViewById(R.id.SPNKNN);
        spnkin = findViewById(R.id.SPNKIN);
        spxknn = findViewById(R.id.SPXKNN);
        spxkin = findViewById(R.id.SPXKIN);
        btnExit = findViewById(R.id.BTNEXIT);
    }
}