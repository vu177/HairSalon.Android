package com.example.quanlycuahang.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycuahang.Data;
import com.example.quanlycuahang.Key;
import com.example.quanlycuahang.R;
import com.example.quanlycuahang.adapter.DanhSachKHAdapter;
import com.example.quanlycuahang.api.ApiLayDL;
import com.example.quanlycuahang.api.ApiRunSQL;
import com.example.quanlycuahang.api.LayDL;
import com.example.quanlycuahang.dialog.LuaChonKHDialog;
import com.example.quanlycuahang.dialog.LuaChonThemDialog;
import com.example.quanlycuahang.dialog.ThemThoCatDialog;
import com.example.quanlycuahang.object.KhachHang;
import com.example.quanlycuahang.tool.Convert;

import java.util.ArrayList;

public class DSKHActivity extends AppCompatActivity implements ApiLayDL, ApiRunSQL {
    Convert convert = new Convert();
    DanhSachKHAdapter danhSachKHAdapter;
    ListView lsvDSKH;

    EditText edtTenKH;
    String tenKH="";

    EditText edtSDT;
    String sdt="";

    TextView txvGtAll, txvGtNam, txvGtNu;
    TextView arrTxvGt[];
    int chonGt = 0; //0 All 1 Nam 2 Nu

    int thucHienCauLenh = 0; //0 thì thực hiện thêm thợ, 1 thì xóa KH
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dskh);
        init();
        anhXa();
        setUp();
        setClick();
        String s = checkData();
        if(s.length()>0){
            new LayDL(s, this).execute();
        }
        new LayDL("",this).execute();
    }

    public void init(){

    }

    public void anhXa(){
        lsvDSKH = findViewById(R.id.lsvDSKH);
        edtTenKH = findViewById(R.id.edtTenKH);
        edtSDT = findViewById(R.id.edtSDT);

        txvGtAll = findViewById(R.id.txvGtAll);
        txvGtNam = findViewById(R.id.txvGtNam);
        txvGtNu = findViewById(R.id.txvGtNu);
    }

    public void setUp(){
        arrTxvGt = new TextView[]{txvGtAll, txvGtNam, txvGtNu};
        setUpGt();
    }

    public void setClick(){
        edtTenKH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTenKH.getText().toString();
                if(s.length()>=2){
                    s = s.toLowerCase();
                    //tiến hành kt và update list
                    tenKH =s;
                }else {
                    tenKH = "";
                    danhSachKHAdapter.updateKH(Data.getData().arrKH);
                }
                tienHanhKT();
            }
        });

        edtSDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtSDT.getText().toString();
                if(s.length()==3){
                    sdt = s;
                }else {
                    sdt="";
                }
                tienHanhKT();
            }
        });

        txvGtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGt = 0;
                setUpGt();
                tienHanhKT();
            }
        });
        txvGtNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGt = 1;
                setUpGt();
                tienHanhKT();
            }
        });
        txvGtNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonGt = 2;
                setUpGt();
                tienHanhKT();
            }
        });

        lsvDSKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                thucHienCauLenh = 1;
                new LuaChonKHDialog(DSKHActivity.this, danhSachKHAdapter.myArr.get(i)).show();
            }
        });
    }

    private void tienHanhKT(){ //kt tên khi nhập vào
        ArrayList<KhachHang> arrTen;
        if(tenKH.length()>=2){
            arrTen = new ArrayList<>();
            for(KhachHang k: Data.getData().arrKH){
                String tenKhach= k.ten.toLowerCase();
                if(tenKhach.indexOf(tenKH)>=0){
                    arrTen.add(k);
                }
            }
        }else {
            arrTen = Data.getData().arrKH;
        }

        //kt sdt
        ArrayList<KhachHang> arrSDT;
        if(sdt.length()==3){
            arrSDT = new ArrayList<>();
            for(KhachHang k: arrTen){
                String sdts= k.sdt;
                if(sdts.indexOf(sdt) == sdts.length()-3){
                    arrSDT.add(k);
                }
            }
        }else {
            arrSDT = arrTen;
        }

        //kt gioi tinh
        ArrayList<KhachHang> arrGt;
        if(chonGt != 0){
            arrGt = new ArrayList<>();
            boolean gt = false;
            if(chonGt == 1){
                gt = true;
            }
            for(KhachHang k: arrSDT){
                if(k.gioiTinh == gt){
                    arrGt.add(k);
                }
            }
        }else {
            arrGt = arrSDT;
        }

        danhSachKHAdapter.updateKH(arrGt);
    }

    public void setUpGt(){
        for(TextView txv: arrTxvGt){
            txv.setTextColor(this.getResources().getColor(R.color.den));
            txv.setBackgroundColor(this.getResources().getColor(R.color.trang));
        }
        arrTxvGt[chonGt].setTextColor(this.getResources().getColor(R.color.vang));
        arrTxvGt[chonGt].setBackgroundColor(this.getResources().getColor(R.color.den));
    }

    public String checkData(){
        String nameTable = "";
        if(Data.getData().arrKH.size()==0){
            return Key.Table_KH;
        }else if(Data.getData().arrThoCatToc.size() == 0){
            return Key.Table_ThoCatToc;
        }
        return "";
    }

    public void setUpShowData(){
        danhSachKHAdapter = new DanhSachKHAdapter(this,0, Data.getData().arrKH);
        lsvDSKH.setAdapter(danhSachKHAdapter);
    }
    @Override
    public void batDauLay() {
        Toast.makeText(this,"bat dau lay",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data, String tableName) {
        if(tableName.equals(Key.Table_KH)){
            Data.getData().arrKH.clear();
            Data.getData().arrKH.addAll(convert.jsonToKH(data));
        }else if(tableName.equals(Key.Table_ThoCatToc)){
            Data.getData().arrThoCatToc.clear();
            Data.getData().arrThoCatToc.addAll(convert.jsonToThoCatToc(data));
        }
        String s = checkData();
        if(s.length()>0){
            new LayDL(s, this).execute();
        }else {
            setUpShowData();
        }
    }

    public static int ID_THEMKH = 99;
    public static int ID_SUAKH = 100;
    public static int ID_CATTOC = 101;
    public static int ID_DATLICH = 102;

    @Override
    public void batDauChay() {

    }

    @Override
    public void ketThuc() {
        if(thucHienCauLenh == 0) {
            Toast.makeText(this, "Thêm Thợ Cắt Tóc Thành Công", Toast.LENGTH_SHORT).show();
            Data.getData().arrThoCatToc.clear();
            String s = checkData();
            if(s.length() >0){
                new LayDL(s, this).execute();
            }
        }else if(thucHienCauLenh == 1){
            Toast.makeText(this, "Xóa Khách Hàng Thành Công", Toast.LENGTH_SHORT).show();
            Data.getData().arrKH.clear();
            String s = checkData();
            if(s.length() >0){
                new LayDL(s, this).execute();
            }
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"loi ket noi",Toast.LENGTH_SHORT).show();
    }

    //liên quan nút thêm
    public void luachonthem(View view) {
        new LuaChonThemDialog(this).show();
    }

    public void chuyenDenThemKH(){
        Data.getData().idKHCanSua = -1;
        Intent i = new Intent(this, ThemKHActivity.class);
        startActivityForResult(i, ID_THEMKH);
    }

    public void themthocat(){
        thucHienCauLenh = 0;
        new ThemThoCatDialog(this).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ID_THEMKH){
            String s = checkData();
            if(s.length()>0){
                new LayDL(s, this).execute();
            }
        }else if(requestCode == ID_SUAKH){
            String s = checkData();
            if(s.length() >0){
                new LayDL(s, this).execute();
            }
        }
    }
}
