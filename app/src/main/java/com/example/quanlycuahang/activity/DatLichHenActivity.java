package com.example.quanlycuahang.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;

public class DatLichHenActivity extends AppCompatActivity {
    KhachHang khachHang;
    LichSu lichSu;
    ThoCatToc thoCatToc;
    TextView txvTenKH;
    Spinner spnThoCatToc;
    EditText edtNgayHen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich);
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
    }
    private void anhXa(){
        txvTenKH = findViewById(R.id.txvTenKH);
        spnThoCatToc = findViewById(R.id.spnThoCatToc);
        edtNgayHen = findViewById(R.id.edtNgayHen);
    }
    private void setUp(){
        txvTenKH.setText(khachHang.ten);
        spnThoCatToc.setAdapter(new ArrayAdapter<ThoCatToc>(this, R.layout.item_text_thocattoc,Data.getData().arrThoCatToc));
        //edtNgayHen.setText(khachHang.ngaySinh);
    }
    private void setClick(){}


    public void chonNgayHen(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date= dayOfMonth + "/" +(monthOfYear+1) + "/"+ year;
                edtNgayHen.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
