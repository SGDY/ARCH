package com.example.sgdy.http;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.sgdy.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MultipartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipart);
    }

    public void onClick(View view) {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String url = "http://i.nongmuren.com/NongMuRen_Interface/interface/myIndex/updateFace.action";
        Response.ErrorListener errorListener = error -> {
            Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
        };
        Response.Listener listener = response -> {
            Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
        };
        Map<String, String> map = new HashMap<>();
        map.put("memberId", "52");
        map.put("memberName", "15190111202");

        String filePath = Environment.getExternalStorageDirectory() + "/Nongmuren/icon/" + Base64.encodeToString("user_header_52.jpg".getBytes(), Base64.DEFAULT);
        File file = new File(filePath);

        MultipartRequest request = new MultipartRequest(url, errorListener, listener, file, map);
        mQueue.add(request);
    }
}
