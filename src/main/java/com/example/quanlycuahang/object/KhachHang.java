package com.example.quanlycuahang.object;


import com.example.quanlycuahang.api.ApiDem;
import com.example.quanlycuahang.api.DemSoLan;

public class KhachHang implements ApiDem {
    public int id;
    public int solanCat=solancat();
    public String ten;
    public String ngaySinh;
    public String sdt;
    public String diaChi;
    public boolean gioiTinh;
    public String mota;

    private int solancat(){
        int i= 0;
        return i;
    }

    @Override
    public void batDauLay() {

    }

    @Override
    public void ketThuc(String data, String tableName) {
        String s= ""+id;
        if(s.length()>0){
            new DemSoLan(s, this).execute();
        }
    }

    @Override
    public void biLoi() {

    }
}
