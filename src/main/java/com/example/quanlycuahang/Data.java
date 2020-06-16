package com.example.quanlycuahang;

import com.example.quanlycuahang.object.KhachHang;
import com.example.quanlycuahang.object.LichSu;
import com.example.quanlycuahang.object.ThoCatToc;

import java.util.ArrayList;

public class Data {
    static Data data;
    static{
        data = new Data();
    }
    public static Data getData(){
        return data;
    }
    public ArrayList<KhachHang> arrKH= new ArrayList<>();

    //-1 chuyển sang thêm, khác thì sửa
    public int idKHCanSua = -1;

    public ArrayList<ThoCatToc> arrThoCatToc = new ArrayList<>();
    public ArrayList<LichSu> arrLichSu = new ArrayList<>();
}
