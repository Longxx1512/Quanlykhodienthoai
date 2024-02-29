package com.example.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.example.quanlykho.R;
import com.example.quanlykho.Table.XuatKho;

import java.util.List;

public class XuatKhoAdapter extends ArrayAdapter<XuatKho> {
    private Context context;
    private int resource;
    private List<XuatKho> list;

    public XuatKhoAdapter(@NonNull Context context, int resource, @NonNull List<XuatKho> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }
    static  class ViewHolder {
        private TextView tvname,tvdate,tvquantity;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = convertView.findViewById(R.id.tv_name);
            viewHolder.tvdate =convertView.findViewById(R.id.tv_date);
            viewHolder.tvquantity =convertView.findViewById(R.id.tv_quantity);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        XuatKho xuatKho = list.get(position);
        viewHolder.tvname.setText(xuatKho.getName());
        viewHolder.tvdate.setText(String.valueOf(xuatKho.getDate()));
        viewHolder.tvquantity.setText(String.valueOf(xuatKho.getQuantity()));
        return convertView;
    }

}
