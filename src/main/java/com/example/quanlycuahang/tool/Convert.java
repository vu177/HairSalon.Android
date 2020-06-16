package com.example.quanlycuahang.tool;

import com.example.quanlycuahang.object.KhachHang;
import com.example.quanlycuahang.object.ThoCatToc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Convert {
    public ArrayList<KhachHang> jsonToKH(String data){
        ArrayList<KhachHang> arr= new ArrayList<>();
        try{
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                JSONObject oj = array.getJSONObject(i);
                KhachHang kh = new KhachHang();
                kh.id = Integer.parseInt(oj.getString("id"));
                kh.ten = oj.getString("ten");
                kh.ngaySinh = oj.getString("ngaysinh");
                kh.sdt = oj.getString("sdt");
                kh.diaChi = oj.getString("diachi");
                kh.mota = oj.getString("mota");
                String gt = oj.getString("gioitinh");
                if(gt.equals("1")){
                    kh.gioiTinh=true;
                }else {
                    kh.gioiTinh=false;
                }
                arr.add(kh);
            }
        }catch (JSONException e){

        }
        return arr;
    }
    public ArrayList<ThoCatToc> jsonToThoCatToc(String data){
        ArrayList<ThoCatToc> arr= new ArrayList<>();
        try{
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                JSONObject oj = array.getJSONObject(i);
                ThoCatToc tho = new ThoCatToc();
                tho.id = Integer.parseInt(oj.getString("id"));
                tho.ten = oj.getString("ten");
                arr.add(tho);
            }
        }catch (JSONException e){

        }
        return arr;
    }
}
