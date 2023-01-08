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
import com.example.appbook.domain.Book;
import com.example.appbook.domain.Publisher;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ModifyPublisherActivity extends AppCompatActivity {

    private long publisherId; //id que vamos a modificar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_publisher);

        Intent intent = new Intent(getIntent());
        publisherId = getIntent().getLongExtra("publisher_id",0); //almacenados el id

        //BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Publisher publisher = db.publisherDao().getById(publisherId);
        fillData(publisher);
    }

    public void modifyPublisherButton(View view) {
        EditText etName = findViewById(R.id.namePublisherModifyEditText);
        EditText etPhoneNumber = findViewById(R.id.phoneNumberPublisherModifyEditText);
        EditText etDescription = findViewById(R.id.descriptionPublisherModifyEditText);

        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String description = etDescription.getText().toString();

        Publisher publisher = new Publisher(publisherId, name, phoneNumber, description);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro que quieres modificar esta Editorial?")
                    .setTitle("Modificar Editorial")
                    .setPositiveButton("Yes", (dialog, id) -> { //boton de si
//                                final AppDatabase dbM = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                                        .allowMainThreadQueries().build();

                        db.publisherDao().update(publisher); //metodo modificar

                        Intent intent = new Intent(this, ModifyPublisherActivity.class);
                        intent.putExtra("publisher_id", publisher.getId());
                        this.startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, is) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, "Ha ocurrido un error. Comprueba los datos e intentalo de nuevo", BaseTransientBottomBar.LENGTH_LONG);
        }
    }

        //boton cancelar y volver atras


        //datos nuevos
        private void fillData(Publisher publisher) {
            EditText etName = findViewById(R.id.namePublisherModifyEditText);
            EditText etPhoneNumber = findViewById(R.id.phoneNumberPublisherModifyEditText);
            EditText etDescription = findViewById(R.id.descriptionPublisherModifyEditText);

            etName.setText(publisher.getName());
            etPhoneNumber.setText(publisher.getPhoneNumber());
            etDescription.setText(publisher.getDescription());
        }

    public void cancelModifyButton(View view) {
        onBackPressed();
    }
}