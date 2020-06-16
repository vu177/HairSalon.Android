package com.example.quanlycuahang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycuahang.Data;
import com.example.quanlycuahang.R;
import com.example.quanlycuahang.api.ApiRunSQL;
import com.example.quanlycuahang.api.RunSQL;
import com.example.quanlycuahang.object.KhachHang;

import java.util.Calendar;

public class ThemKHActivity extends AppCompatActivity implements ApiRunSQL {
    EditText edtTenKH, edtNgaySinhKH, edtSdtKH, edtDiaChiKH, edtMoTaKH;
    TextView txvNam, txvNu;
    boolean gt = true;
    KhachHang khachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themkh);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        if(Data.getData().idKHCanSua == -1){
            //chuyển qua từ nút thêm
            ((TextView)findViewById(R.id.txvThemOrSua)).setText("THÊM");
            khachHang = null;
        }else {
            ((TextView)findViewById(R.id.txvThemOrSua)).setText("SỬA");
            for(KhachHang k: Data.getData().arrKH){
                if(k.id == Data.getData().idKHCanSua){
                    khachHang = k;
                    break;
                }
            }
        }
    }

    private void anhXa(){
        edtTenKH = findViewById(R.id.edtTenKH);
        edtNgaySinhKH = findViewById(R.id.edtNgaySinhKH);
        edtSdtKH = findViewById(R.id.edtSdtKH);
        edtDiaChiKH = findViewById(R.id.edtDiaChiKH);
        edtMoTaKH = findViewById(R.id.edtMoTaKH);
        txvNam = findViewById(R.id.txvNam);
        txvNu = findViewById(R.id.txvNu);
    }
    private void setUp(){
        if(khachHang != null){
            edtTenKH.setText(khachHang.ten);
            edtNgaySinhKH.setText(khachHang.ngaySinh);
            edtSdtKH.setText(khachHang.sdt);
            edtDiaChiKH.setText(khachHang.diaChi);
            edtMoTaKH.setText(khachHang.mota);
            if(khachHang.gioiTinh){
                gt = true;
                txvNam.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.trang));
            }else {
                gt = false;
                txvNu.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.trang));
            }
        }
    }

    private void setClick(){
        txvNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gt = true;
                txvNam.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.trang));
            }
        });
        txvNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gt = false;
                txvNu.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKHActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKHActivity.this.getResources().getColor(R.color.trang));
            }
        });
    }

    private boolean checkNullInfor(EditText e){
        String s = ""+ e.getText();
        if(s.length()==0){
            return false;
        }else {
            return true;
        }
    }

    private void thongBao(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    public void themKH(View view) {
        if(!checkNullInfor(edtTenKH)){
            thongBao("Chưa nhập Tên Khách Hàng");
            return;
        }
        String ten = edtTenKH.getText().toString();

        if(!checkNullInfor(edtNgaySinhKH)){
            thongBao("Chưa nhập Ngày Sinh");
            return;
        }
        String ngaysinh = edtNgaySinhKH.getText().toString();

        if(!checkNullInfor(edtSdtKH)){
            thongBao("Bị Thiếu Số điện thoại");
            return;
        }
        String sdt = edtSdtKH.getText().toString();

        if(!checkNullInfor(edtDiaChiKH)){
            thongBao("Bị Thiếu Địa chỉ");
            return;
        }
        String diachi = edtDiaChiKH.getText().toString();

        if(!checkNullInfor(edtMoTaKH)){
            thongBao("Bị Thiếu mô tả thêm");
            return;
        }
        String mota = edtMoTaKH.getText().toString();

        String gt = "1";
        if(!this.gt){
            gt="0";
        }
        String sql;
        if(khachHang == null) {
            sql = "INSERT INTO `khachhang` (`id`, `ten`, `ngaysinh`, `sdt`, `diachi`, `gioitinh`, `mota`) VALUES (NULL, '" +
                    ten +
                    "', '" +
                    ngaysinh +
                    "', '" +
                    sdt +
                    "', '" +
                    diachi +
                    "', '" +
                    gt +
                    "', '" +
                    mota +
                    "')";
        }else {
            sql = "UPDATE `khachhang` SET `ten` = '" +
                    ten +
                    "', `ngaysinh` = '" +
                    ngaysinh +
                    "', `sdt` = '" +
                    sdt +
                    "', `diachi` = '" +
                    diachi +
                    "', `gioitinh` = '" +
                    gt +
                    "', `mota` = '" +
                    mota +
                    "' WHERE `khachhang`.`id` = "+ khachHang.id;
        }
        new RunSQL(sql, this).execute();
    }

    public void chonNgaySinh(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date= dayOfMonth + "/" +(monthOfYear+1) + "/"+ year;
                edtNgaySinhKH.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void batDauChay() {
        thongBao("Bắt đầu run sql");
    }

    @Override
    public void ketThuc() {
        thongBao("Đã kết thúc sql");
        Data.getData().arrKH.clear();
        onBackPressed();
    }

    @Override
    public void biLoi() {
        thongBao("Run sql lỗi");
    }
}
