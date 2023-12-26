package com.mohamedtaofig.bookstore.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mohamedtaofig.bookstore.R;

public class PDFActivity extends AppCompatActivity {
//    PDFView pdfView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfactivity);
//        pdfView = findViewById(R.id.pdfView);
        String link = getIntent().getStringExtra("link");

//        pdfView.fromUri(link).load();




    }
}