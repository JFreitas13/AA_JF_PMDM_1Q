package com.example.appbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.book_list);
        button.setOnClickListener(view ->  {
            Intent intent = new Intent(MainActivity.this, ListBooksActivity.class);
            startActivity(intent);
        });

        button = findViewById(R.id.library_list);
        button.setOnClickListener(view ->  {
            Intent intent = new Intent(MainActivity.this, ListLibrariesActivity.class);
            startActivity(intent);
        });

        button = findViewById(R.id.publisher_list);
        button.setOnClickListener(view ->  {
            Intent intent = new Intent(MainActivity.this, ListPublisherActivity.class);
            startActivity(intent);
        });
    }

}