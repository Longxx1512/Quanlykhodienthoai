package com.example.quanlykho.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.quanlykho.Database.DBHelper;
import com.example.quanlykho.R;
import com.example.quanlykho.Table.SanPham;

import java.util.List;

public class SPAdapter extends ArrayAdapter<SanPham> {

    private Context context;
    private int resource;
    private List<SanPham> list;
    private DBHelper dbHelper;

    private List<byte[]> productImages;

    public SPAdapter(@NonNull Context context, int resource, @NonNull List<SanPham> objects, List<byte[]> productImages, DBHelper dbHelper) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
        this.productImages = productImages;
        this.dbHelper = dbHelper;
    }

    static class ViewHolder {
        private TextView tvname, tvprice, tvquantity;
        private ImageView imgHinhDaiDien;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = convertView.findViewById(R.id.tv_name);
            viewHolder.tvprice = convertView.findViewById(R.id.tv_price);
            viewHolder.tvquantity = convertView.findViewById(R.id.tv_quantity);
            viewHolder.imgHinhDaiDien = convertView.findViewById(R.id.imgHinhDaiDien);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = list.get(position);
        viewHolder.tvname.setText(sanPham.getName());
        viewHolder.tvprice.setText(String.valueOf(sanPham.getPrice()));

        int updatedQuantity = dbHelper.getRemainingQuantity(sanPham.getName());
        sanPham.setQuantity(updatedQuantity);
        viewHolder.tvquantity.setText(String.valueOf(updatedQuantity));

        // Kiểm tra xem productImages có giá trị và kích thước phù hợp
        if (productImages != null && position < productImages.size()) {
            byte[] imageByteArray = productImages.get(position);
            if (imageByteArray != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
                viewHolder.imgHinhDaiDien.setImageBitmap(bitmap);
            } else {
                // Sử dụng hình ảnh mặc định từ tài nguyên drawable
                Drawable defaultDrawable = ContextCompat.getDrawable(context, R.drawable.dt);
                viewHolder.imgHinhDaiDien.setImageDrawable(defaultDrawable);
            }
        } else {
            // Xử lý trường hợp productImages là null hoặc position vượt quá giới hạn
        }

        return convertView;
    }
}
