package com.example.quanlycuahang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.quanlycuahang.R;
import com.example.quanlycuahang.activity.DSKHActivity;

public class LuaChonThemDialog extends Dialog {
    DSKHActivity ds;
    public LuaChonThemDialog(@NonNull Context context) {
        super(context);
        this.ds = (DSKHActivity)context;
        setContentView(R.layout.dialog_luachonthem);
        TextView txvThemKH = findViewById(R.id.txvThemKH);
        TextView txvThemThoCat = findViewById(R.id.txvThemThoCat);
        txvThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.chuyenDenThemKH();
                dismiss();
            }
        });
        txvThemThoCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.themthocat();
                dismiss();
            }
        });
    }
}
