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

public class AddBookActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    //boton AÑADIR
    public void addButton(View view) {
        EditText etName = findViewById(R.id.nameEditText);
        EditText etYearEdition = findViewById(R.id.yearTextNumber);
        EditText etPageNumber = findViewById(R.id.pagesTextNumber);
        EditText etDescription = findViewById(R.id.descriptionEditText);

        String name = etName.getText().toString();
        String yearEditionS = etYearEdition.getText().toString();
        int yearEdition = Integer.parseInt(yearEditionS);
        String pageNumberS = etPageNumber.getText().toString();
        int pageNumber = Integer.parseInt(pageNumberS);
        String description = etDescription.getText().toString();

        //crear libro
        Book book = new Book(name, yearEdition, pageNumber, description);

        //creo BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.bookDao().insert(book);

        //mensaje emergente
        Toast.makeText(this, R.string.book_add_message, Toast.LENGTH_SHORT).show();
        etName.setText("");
        etYearEdition.setText("");
        etPageNumber.setText("");
        etDescription.setText("");
        etName.requestFocus();


        //boton CANCELAR
    }
    public void cancelButton(View view) {
        onBackPressed();

    }

}
