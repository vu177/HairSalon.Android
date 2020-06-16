package com.example.quanlycuahang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quanlycuahang.R;

public class ThemLichSuCatActivity extends AppCompatActivity {
    TextView txvTenKH;
    Spinner spnThoCatToc;
    GridView gdvAnhCuaKhach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_toc_cho_khach);
    }

    private void init(){}
    private void anhXa(){
        txvTenKH = findViewById(R.id.txvThemKH);
        spnThoCatToc = findViewById(R.id.spnThoCatToc);
        gdvAnhCuaKhach = findViewById(R.id.gdvAnhCuaKhach);
    }
    private void setUp(){}
    private void setClick(){}
}
