package com.example.quanlycuahang.api;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class LayDL extends AsyncTask<Void,Void,Void> {
    String data;
    String tableName;
    ApiLayDL run;

    public LayDL(String tableName, ApiLayDL run) {
        this.tableName = tableName;
        this.run = run;
        this.run.batDauLay();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.56.1/quanlycuahang/getdata.php?name="+tableName).build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(data == null){
            run.biLoi();
        }else {
            run.ketThuc(data, tableName);
        }
    }
}
