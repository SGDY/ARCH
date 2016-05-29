package com.example.sgdy.photo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.sgdy.R;
import com.example.sgdy.photo.type1.SelectPhotoActivity1;

public class SelectPhotoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        initComponent();
    }

    private void initComponent() {
        findViewById(R.id.btn_1).setOnClickListener(v -> startActivity(new Intent(this, SelectPhotoActivity1.class)));
    }
}
