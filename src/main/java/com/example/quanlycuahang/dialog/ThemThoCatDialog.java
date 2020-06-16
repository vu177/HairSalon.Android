package com.example.quanlycuahang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quanlycuahang.R;
import com.example.quanlycuahang.activity.DSKHActivity;
import com.example.quanlycuahang.api.RunSQL;

public class ThemThoCatDialog extends Dialog {
    DSKHActivity ds;
    EditText edtTenThoCat;
    public ThemThoCatDialog(@NonNull Context context) {
        super(context);
        this.ds = (DSKHActivity)context;
        setContentView(R.layout.dialog_themthocat);
        TextView txvThemThoCatToc = findViewById(R.id.txvThemThoCatToc);
        edtTenThoCat = findViewById(R.id.edtTenThoCat);

        txvThemThoCatToc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = "" + edtTenThoCat.getText().toString();
                if(ten.length() == 0){
                    Toast.makeText(ds,"Chưa nhập Tên Thợ Cắt Tóc", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sql = "INSERT INTO `tho` (`id`, `ten`) VALUES (NULL, '" + ten + "')";
                new RunSQL(sql, ds).execute();
                dismiss();
            }
        });
    }
}
