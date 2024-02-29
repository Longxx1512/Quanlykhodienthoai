package com.example.quanlykho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quanlykho.R;
import com.example.quanlykho.Table.NhaCungCap;

import java.util.List;

public class NCCAdapter extends ArrayAdapter<NhaCungCap> {

    private  Context context;
    private int resource;
    private List<NhaCungCap> list;

    public NCCAdapter(@NonNull Context context, int resource, @NonNull List<NhaCungCap> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    static  class ViewHolder {
        private TextView tvname,tvaddress;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvname = convertView.findViewById(R.id.tv_name);
            viewHolder.tvaddress =convertView.findViewById(R.id.tv_diachi);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NhaCungCap nhaCungCap = list.get(position);
        viewHolder.tvname.setText(nhaCungCap.getName());
        viewHolder.tvaddress.setText(nhaCungCap.getAddress());
        return convertView;
    }

}
