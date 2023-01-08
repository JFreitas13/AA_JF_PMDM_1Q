package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Publisher;

public class PublisherDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publisher_details);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        if(name == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Publisher publisher = db.publisherDao().getByName(name);

        //todo terminar mostrando layout
        Toast.makeText(this, "Libreria" + publisher.getName(), Toast.LENGTH_SHORT).show();
    }

    //boton CANCELAR
    public void returnButton(View view) {
        onBackPressed();

    }
}
