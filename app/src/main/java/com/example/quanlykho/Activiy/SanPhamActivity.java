package com.example.quanlykho.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlykho.Activiy.SanPham.AddSanPhamActivity;
import com.example.quanlykho.Activiy.SanPham.ThongKeActivity;
import com.example.quanlykho.Activiy.SanPham.UpdateSanPhamActivity;
import com.example.quanlykho.Adapter.SPAdapter;
import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {

    private Button btnexit;
    private FloatingActionButton fab,fab1;
    private ListView listView;
    private DBHelper dbHelper;
    private List<SanPham> list;
    private SPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        dbHelper = new DBHelper(this);
        anhxa();
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SanPhamActivity.this, HomeActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SanPhamActivity.this, AddSanPhamActivity.class));
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SanPhamActivity.this, ThongKeActivity.class));
            }
        });
        list = dbHelper.INFOSP();
        // Gọi lại setAdapter() sau khi đã cập nhật số lượng
        updateProductQuantities();
        setAdapter();

        //Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham selectedSP = list.get(position);
                Intent intent = new Intent(SanPhamActivity.this, UpdateSanPhamActivity.class);
                intent.putExtra("idsanpham", selectedSP.getId());
                intent.putExtra("tensanpham", selectedSP.getName());
                intent.putExtra("motasanpham", selectedSP.getInfo());
                intent.putExtra("giasanpham", selectedSP.getPrice());
                intent.putExtra("slsanpham", selectedSP.getQuantity());
                intent.putExtra("nhasxsanpham", selectedSP.getManufacturers());
                intent.putExtra("namsxsanpham", selectedSP.getDate());

                // Thêm thông tin ảnh vào Intent
                intent.putExtra("imagesanpham", selectedSP.getImage());
                startActivity(intent);
            }
        });
    }
    private void setAdapter() {
        adapter = new SPAdapter(SanPhamActivity.this, R.layout.sp_item, list, getProductImages(), dbHelper);
        listView.setAdapter(adapter);
    }


    private List<byte[]> getProductImages() {
        List<byte[]> productImages = new ArrayList<>();
        for (SanPham sanPham : list) {
            // Thêm ảnh sản phẩm vào danh sách
            productImages.add(sanPham.getImage());
        }
        return productImages;
    }


    private void anhxa() {
        btnexit = findViewById(R.id.buttonExit);
        fab = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.lvSP);
        fab1 = findViewById(R.id.floatingActionButton1);
    }
    // Thêm phương thức cập nhật số lượng tồn kho
    private void updateProductQuantities() {
        for (int i = 0; i < list.size(); i++) {
            SanPham sanPham = list.get(i);
            int updatedQuantity = dbHelper.getRemainingQuantity(sanPham.getName());
            sanPham.setQuantity(updatedQuantity);

            // Cập nhật số lượng trong danh sách và hiển thị
            View view = listView.getChildAt(i - listView.getFirstVisiblePosition());
            if (view != null) {
                TextView tvquantity = view.findViewById(R.id.tv_quantity);
                tvquantity.setText(String.valueOf(updatedQuantity));
            }
        }
    }


}