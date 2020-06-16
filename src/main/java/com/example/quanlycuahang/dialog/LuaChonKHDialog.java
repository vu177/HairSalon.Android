package com.example.quanlycuahang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.quanlycuahang.Data;
import com.example.quanlycuahang.R;
import com.example.quanlycuahang.activity.CatTocChoKhachActivity;
import com.example.quanlycuahang.activity.DSKHActivity;
import com.example.quanlycuahang.activity.DatLichHenActivity;
import com.example.quanlycuahang.activity.ThemKHActivity;
import com.example.quanlycuahang.adapter.DanhSachKHAdapter;
import com.example.quanlycuahang.api.RunSQL;
import com.example.quanlycuahang.object.KhachHang;

public class LuaChonKHDialog extends Dialog {
    KhachHang ks;
    DSKHActivity ds;
    public LuaChonKHDialog(final Context context, KhachHang khachHang) {
        super(context);
        this.ks = khachHang;
        this.ds = (DSKHActivity)context;
        setContentView(R.layout.dialog_luachonkh);

        //ánh xạ
        TextView txvTenKH = findViewById(R.id.txvTenKH);
        TextView txvSuaKH = findViewById(R.id.txvSuaKH);
        TextView txvXoaKH = findViewById(R.id.txvXoaKH);
        TextView txvCatTocChoKhach = findViewById(R.id.txvCatTocChoKhach);
        TextView txvDatLichHen = findViewById(R.id.txvDatLichHen);

        //set thông tin
        txvTenKH.setText(ks.ten);

        txvSuaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getData().idKHCanSua = ks.id;
                ds.startActivityForResult(new Intent(ds, ThemKHActivity.class), ds.ID_SUAKH);
                dismiss();
            }
        });

        txvXoaKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn chắc chắn muốn xóa?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM `khachhang` WHERE `khachhang`.`id` = " + ks.id;
                        new RunSQL(sql, ds).execute();
                        dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });
        txvCatTocChoKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getData().idKHCanSua = ks.id;
                ds.startActivityForResult(new Intent(ds, CatTocChoKhachActivity.class), ds.ID_CATTOC);
                dismiss();
            }
        });

        txvDatLichHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getData().idKHCanSua = ks.id;
                ds.startActivityForResult(new Intent(ds, DatLichHenActivity.class), ds.ID_DATLICH);
                dismiss();
            }
        });

    }
}
