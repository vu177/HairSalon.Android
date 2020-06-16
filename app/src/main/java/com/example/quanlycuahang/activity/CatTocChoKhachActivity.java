package com.example.quanlycuahang.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycuahang.Data;
import com.example.quanlycuahang.R;
import com.example.quanlycuahang.adapter.AnhKhachHangAdapter;
import com.example.quanlycuahang.api.ApiRunSQL;
import com.example.quanlycuahang.api.RunSQL;
import com.example.quanlycuahang.object.KhachHang;
import com.example.quanlycuahang.object.LichSu;
import com.example.quanlycuahang.object.ThoCatToc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CatTocChoKhachActivity extends AppCompatActivity implements ApiRunSQL {
    KhachHang khachHang;
    LichSu lichSu;
    ThoCatToc thoCatToc;
    TextView txvTenKH;
    Spinner spnThoCatToc;
    ArrayList<Bitmap> bitmaps;
    AnhKhachHangAdapter adapter;
    GridView gdvAnhCuaKhach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_toc_cho_khach);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init(){
            for(KhachHang k: Data.getData().arrKH) {
                if (k.id == Data.getData().idKHCanSua) {
                    khachHang = k;
                    break;
                }
            }

            bitmaps = new ArrayList<>();
            adapter = new AnhKhachHangAdapter(this, 0, bitmaps);
    }
    private void anhXa(){
        txvTenKH = findViewById(R.id.txvTenKH);
        spnThoCatToc = findViewById(R.id.spnThoCatToc);
        gdvAnhCuaKhach = findViewById(R.id.gdvAnhCuaKhach);
    }
    private void setUp(){
        txvTenKH.setText(khachHang.ten);
        spnThoCatToc.setAdapter(new ArrayAdapter<ThoCatToc>(this, R.layout.item_text_thocattoc,Data.getData().arrThoCatToc));
        gdvAnhCuaKhach.setAdapter(adapter);
    }
    private void setClick(){}

    public static int GET_ANH_TU_THU_VIEN = 999;
    public void themAnhTuThuVien(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GET_ANH_TU_THU_VIEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == GET_ANH_TU_THU_VIEN){
            if(data != null){
                Uri umgUri = data.getData();
                try{
                    InputStream inputStream = getContentResolver().openInputStream(umgUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmaps.add(bitmap);
                    adapter.setMyArr(bitmaps);
                }catch (FileNotFoundException e){

                }
            }
        }
    }

    public void themLichSuCat(View view) {
        String ten = txvTenKH.getText().toString();
        //int idkhach = khachHang.id;
        String tho = spnThoCatToc.getSelectedItem().toString();
        //int idtho = thoCatToc.id;
        String anh = gdvAnhCuaKhach.getAdapter().toString();
        String sql="INSERT INTO `lichsu` (`id`, `idkhach`, `idtho`, `ngay`, `anh`) VALUES (NULL, '" +
                "1" +
                "', '" +
                "2" +
                "', '" +
                "07/06/2020" +
                "', '" +
                "anh2" +
                "')";
        new RunSQL(sql, this).execute();

    }

    private void thongBao(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void batDauChay() {
        thongBao("Bắt đầu run sql");
    }

    @Override
    public void ketThuc() {
        thongBao("Đã kết thúc sql");
        Data.getData().arrLichSu.clear();
        onBackPressed();
        khachHang.solanCat ++;
    }

    @Override
    public void biLoi() {
        thongBao("Run sql lỗi");
    }
}
