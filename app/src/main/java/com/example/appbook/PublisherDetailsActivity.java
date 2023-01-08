package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;
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
        fillData(publisher);

    }

    private void fillData(Publisher publisher) {
        TextView tvName = findViewById(R.id.publisher_name_details);
        TextView tvPhoneNumber = findViewById(R.id.publisher_phone_number_details);
        TextView tvDescription = findViewById(R.id.publisher_description_details);

        tvName.setText(publisher.getName());
        tvPhoneNumber.setText(publisher.getPhoneNumber());
        tvDescription.setText(publisher.getDescription());
    }

    //boton CANCELAR
    public void returnButton(View view) {
        onBackPressed();

    }
}
