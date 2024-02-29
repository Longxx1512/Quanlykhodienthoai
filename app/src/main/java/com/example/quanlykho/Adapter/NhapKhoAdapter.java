package com.example.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhapKho;


import java.util.List;

public class NhapKhoAdapter extends ArrayAdapter<NhapKho> {
    private Context context;
    private int resource;
    private List<NhapKho> list;

    public NhapKhoAdapter(@NonNull Context context, int resource, @NonNull List<NhapKho> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }
    static  class ViewHolder {
        private TextView tvname,tvdate,tvquantity,tvprice;
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
            viewHolder.tvprice =convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhapKho nhapKho = list.get(position);
        viewHolder.tvname.setText(nhapKho.getName());
        viewHolder.tvdate.setText(String.valueOf(nhapKho.getDate()));
        viewHolder.tvquantity.setText(String.valueOf(nhapKho.getQuantity()));
        viewHolder.tvprice.setText(String.valueOf(nhapKho.getPrice()));
        return convertView;
    }
}
