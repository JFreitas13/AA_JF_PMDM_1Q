package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;

public class AddBookActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    //boton añadir libro
    public void addBook(View view) {
        EditText etName = findViewById(R.id.NameEditText);
        EditText etYearEdition = findViewById(R.id.YearTextNumber);
        EditText etPageNumber = findViewById(R.id.PagesTextNumber);
        EditText etDescription = findViewById(R.id.DescriptionEditText);

        String name = etName.getText().toString();
        String yearEdition = etYearEdition.getText().toString();
        int yearEditionI = Integer.parseInt(yearEdition);
        String pageNumber = etPageNumber.getText().toString();
        int pageNumberI = Integer.parseInt(pageNumber);
        String description = etDescription.getText().toString();

        Book book = new Book(name, yearEditionI, pageNumberI, description);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.bookDao().insert(book);

        Toast.makeText(this,"Libro añadido correctamente", Toast.LENGTH_SHORT).show();
        etName.setText("");
        etYearEdition.setText("");
        etPageNumber.setText("");
        etDescription.setText("");
        etName.requestFocus();

    }

    public void cancel(View view) {
        onBackPressed();

    }

}
