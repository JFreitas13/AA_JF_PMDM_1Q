package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ModifyLibraryActivity extends AppCompatActivity {

    private long libraryId; //id que vamos a modificar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_library);

        Intent intent = new Intent(getIntent());
        libraryId = getIntent().getLongExtra("libraryId",0); //almacenados el id

        //BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Library library = db.libraryDao().getByLibraryId(libraryId);
        fillData(library);
    }

    public void modifyLibraryButton(View view) {
        EditText etName = findViewById(R.id.nameLibraryModifyEditText);
        EditText etCity = findViewById(R.id.cityLibraryModifyEditText);
        EditText etZipCode = findViewById(R.id.zipCodeLibraryModifyEditText);
        EditText etPhoneNumber = findViewById(R.id.phoneNumberLibraryModifyEditText);

        String name = etName.getText().toString();
        String city = etCity.getText().toString();
        String zipCode = etZipCode.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        Library library = new Library(libraryId, name, city, zipCode, phoneNumber);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.are_you_sure_modify_library_message)
                    .setTitle(R.string.modify_library_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //boton de si

                        db.libraryDao().update(library); //metodo modificar

                        Intent intent = new Intent(this, ListLibrariesActivity.class);
                        intent.putExtra("libraryId", library.getLibraryId());
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
        private void fillData(Library library) {
            EditText etName = findViewById(R.id.nameLibraryModifyEditText);
            EditText etCity = findViewById(R.id.cityLibraryModifyEditText);
            EditText etZipCode = findViewById(R.id.zipCodeLibraryModifyEditText);
            EditText etPhoneNumber = findViewById(R.id.phoneNumberLibraryModifyEditText);

            etName.setText(library.getName());
            etCity.setText(library.getCity());
            etZipCode.setText(library.getZipCode());
            etPhoneNumber.setText(library.getPhoneNumber());
        }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        onBackPressed();
    }
}
