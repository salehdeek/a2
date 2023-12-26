package com.mohamedtaofig.bookstore.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mohamedtaofig.bookstore.R;

public class BookActivity extends AppCompatActivity {

    ImageView bookImage;
    TextView bookTitle,bookDescription,bookDate;
    Button readBtn,downloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String image = getIntent().getStringExtra("image");
        String link = getIntent().getStringExtra("link");
        String date = getIntent().getStringExtra("date");

        bookTitle = findViewById(R.id.b_title);
        bookDescription = findViewById(R.id.b_description);
        bookImage = findViewById(R.id.bookImage);
        readBtn = findViewById(R.id.readBtn);
        downloadBtn = findViewById(R.id.downloadBtn);
        bookDate = findViewById(R.id.b_date);

        bookDate.setText(date);

        bookTitle.setText(title);
        bookDescription.setText(description);
        Glide.with(this)
                .load(image)
                .into(bookImage);


        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PDFActivity.class);
                intent.putExtra("link",link);
                startActivity(intent);

            }
        });

    }
}