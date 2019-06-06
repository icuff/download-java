package com.example.downloadjava;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.downloadBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                download();
            }
        });
    }

    private void download() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        WeakReference<Context> weakContext = new WeakReference<>(getApplicationContext());
        new DownloadTask(weakContext).execute("https://devdactic.com/html/5-simple-hacks-LBT.pdf");
    }
}
