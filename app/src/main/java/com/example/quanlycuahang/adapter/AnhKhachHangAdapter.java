package com.example.quanlycuahang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlycuahang.R;

import java.util.ArrayList;
import java.util.List;

public class AnhKhachHangAdapter extends ArrayAdapter<Bitmap> {
    private Context myCt;
    private ArrayList<Bitmap> myArr;
    public AnhKhachHangAdapter(Context context, int resource,List<Bitmap> objects) {
        super(context, resource, objects);
        this.myCt = context;
        this.myArr = new ArrayList<>(objects);
    }

    @Override
    public int getCount() {
        return myArr.size();
    }

    public void setMyArr(ArrayList<Bitmap> arr){
        this.myArr = arr;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_anhcuakhach, null);
        }
        if(myArr.size()>0){
            ImageView imgAnhCuaKH = convertView.findViewById(R.id.imgAnhCuaKH);
            imgAnhCuaKH.setImageBitmap(myArr.get(position));
        }
        return convertView;
    }
}
