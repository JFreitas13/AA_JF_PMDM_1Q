package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ModifyBookActivity extends AppCompatActivity {

    private long bookId; //id que vamos a modificar
    private long libraryId; // id de la libreria relacionada
    private long publisherId; //id de la editorial relacionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_book);

        Intent intent = new Intent(getIntent());
        bookId = getIntent().getLongExtra("bookId",0); //almacenados el id

        //BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Book book = db.bookDao().getById(bookId);
        libraryId = book.getLibrary_id(); //id libreria relacionada
        publisherId = book.getPublisher_id(); //id editorial relacionada
        fillData(book);
    }

    public void modifyButton(View view) {
        EditText etName = findViewById(R.id.nameModifyEditText);
        EditText etYearEdition = findViewById(R.id.yearModifyTextNumber);
        EditText etPageNumber = findViewById(R.id.pagesModifyTextNumber);
        EditText etDescription = findViewById(R.id.descriptionModifyEditText);
        CheckBox cbRead = findViewById(R.id.checkBoxRead);

        long libraryIdModify = libraryId; //no se puede borrar
        long publisherIdModify = publisherId;
        String name = etName.getText().toString();
        String yearEditionS = etYearEdition.getText().toString();
        int yearEdition = Integer.parseInt(yearEditionS);
        String pageNumberS = etPageNumber.getText().toString();
        int pageNumber = Integer.parseInt(pageNumberS);
        String description = etDescription.getText().toString();
        boolean read = cbRead.isChecked();

        Book book = new Book(bookId, libraryIdModify, publisherId, name, yearEdition, pageNumber, description, read);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.are_you_sure_modify_book_message)
                    .setTitle(R.string.modify_book_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //boton de si
                        final AppDatabase dbM = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                                .allowMainThreadQueries().build();
                        dbM.bookDao().update(book); //metodo modificar

                        Intent intent = new Intent(this, ListBooksActivity.class);
                        intent.putExtra("book_id", book.getBookId());
                        this.startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, is) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, R.string.error_message, BaseTransientBottomBar.LENGTH_LONG);
        }
    }

        //datos nuevos
        private void fillData(Book book) {
            EditText etName = findViewById(R.id.nameModifyEditText);
            EditText etYearEdition = findViewById(R.id.yearModifyTextNumber);
            EditText etPageNumber = findViewById(R.id.pagesModifyTextNumber);
            EditText etDescription = findViewById(R.id.descriptionModifyEditText);

            etName.setText(book.getName());
            etYearEdition.setText(book.getYearEditionString());
            etPageNumber.setText(book.getPagesNumberString());
            etDescription.setText(book.getDescription());
        }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        onBackPressed();
    }
}
