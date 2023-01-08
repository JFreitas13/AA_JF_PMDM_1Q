package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Publisher;

public class AddPublisherActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);
    }

    //boton AÑADIR
    public void addPublisherButton(View view) {
        EditText etName = findViewById(R.id.name_publisher_edit_text);
        EditText etPhoneNumber = findViewById(R.id.phone_number_publisher_edit_text);
        EditText etDescription = findViewById(R.id.description_publisher_edit_text);

        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String description = etDescription.getText().toString();

        //crear libro
        Publisher publisher = new Publisher(name, phoneNumber, description);

        //creo BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.publisherDao().insert(publisher);

        //mensaje emergente
        Toast.makeText(this, R.string.publisher_add_message, Toast.LENGTH_SHORT).show();
        etName.setText("");
        etPhoneNumber.setText("");
        etDescription.setText("");
        etName.requestFocus();
    }

    //boton CANCELAR
    public void cancelPublisherButton(View view) {
        onBackPressed();

    }

}
