package com.example.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhanVien;

import java.util.List;

public class NhanVienAdapter  extends ArrayAdapter<NhanVien> {
    private Context context;
    private int resource;
    private List<NhanVien> list;
    public NhanVienAdapter(@NonNull Context context, int resource, @NonNull List<NhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }
    static  class ViewHolder {
        private TextView tvname,tvduty;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = convertView.findViewById(R.id.tv_name);
            viewHolder.tvduty =convertView.findViewById(R.id.tv_duty);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhanVien nhanVien = list.get(position);
        viewHolder.tvname.setText(nhanVien.getName());
        viewHolder.tvduty.setText(nhanVien.getDuty());
        return convertView;
    }

}
