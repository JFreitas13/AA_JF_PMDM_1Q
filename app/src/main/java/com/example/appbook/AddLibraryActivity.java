package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;
import com.example.appbook.domain.Library;

public class AddLibraryActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_library);
    }

    //boton AÑADIR
    public void addButton(View view) {
        EditText etName = findViewById(R.id.nameEditText);
        EditText etCity = findViewById(R.id.cityTextNumber);
        EditText etZipCode = findViewById(R.id.zipCodeTextNumber);
        EditText etPhoneNumber = findViewById(R.id.phoneNumberEditText);

        String name = etName.getText().toString();
        String city = etCity.getText().toString();
        String zipCode = etZipCode.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        //crear libro
        Library library = new Library(name, city, zipCode, phoneNumber);

        //creo BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.libraryDao().insert(library);

        //mensaje emergente
        Toast.makeText(this, R.string.library_add_message, Toast.LENGTH_SHORT).show();
        etName.setText("");
        etCity.setText("");
        etZipCode.setText("");
        etPhoneNumber.setText("");
        etName.requestFocus();


        //boton CANCELAR
    }
    public void cancelButton(View view) {
        onBackPressed();

    }

}
