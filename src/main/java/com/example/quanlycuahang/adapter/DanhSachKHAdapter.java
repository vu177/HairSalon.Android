package com.example.quanlycuahang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlycuahang.R;
import com.example.quanlycuahang.api.ApiDem;
import com.example.quanlycuahang.api.DemSoLan;
import com.example.quanlycuahang.object.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKHAdapter extends ArrayAdapter<KhachHang> {
    Context myCt;
    public ArrayList<KhachHang> myArr;
    public DanhSachKHAdapter( Context context, int resource, List<KhachHang> objects) {
        super(context, resource, objects);
        this.myCt = context;
        this.myArr = new ArrayList<>(objects);
    }

    public void updateKH(ArrayList<KhachHang> arr){
        this.myArr = arr;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.myArr.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_kh,null);
        }
        if(myArr.size()>0){
            TextView txvTenKH = convertView.findViewById(R.id.txvTenKH);
            TextView txvGtKH = convertView.findViewById(R.id.txvGtKH);
            TextView txvNgaySinhKH = convertView.findViewById(R.id.txvNgaySinhKH);
            TextView txvSDTKH = convertView.findViewById(R.id.txvSDTKH);
            TextView txvSolancatKH = convertView.findViewById(R.id.txvSolancatKH);

            KhachHang kh = myArr.get(position);

            txvSDTKH.setText(kh.sdt);
            txvTenKH.setText(kh.ten);
            if(kh.gioiTinh){
                txvGtKH.setText("NAM");
            }else{
                txvGtKH.setText("NU");
            }
            txvNgaySinhKH.setText(kh.ngaySinh);
            txvSDTKH.setText(kh.sdt);
            txvSolancatKH.setText(""+kh.solanCat+ " lan");
        }
        return convertView;
    }


}
