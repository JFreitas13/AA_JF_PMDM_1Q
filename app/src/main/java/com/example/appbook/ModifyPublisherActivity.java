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
        publisherId = getIntent().getLongExtra("publisherId",0); //almacenados el id

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
            builder.setMessage(R.string.are_you_sure_modify_publisher_message)
                    .setTitle(R.string.modify_publisher_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //boton de si

                        db.publisherDao().update(publisher); //metodo modificar

                        Intent intent = new Intent(this, ListPublisherActivity.class);
                        intent.putExtra("publisherId", publisher.getPublisherId());
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
        private void fillData(Publisher publisher) {
            EditText etName = findViewById(R.id.namePublisherModifyEditText);
            EditText etPhoneNumber = findViewById(R.id.phoneNumberPublisherModifyEditText);
            EditText etDescription = findViewById(R.id.descriptionPublisherModifyEditText);

            etName.setText(publisher.getName());
            etPhoneNumber.setText(publisher.getPhoneNumber());
            etDescription.setText(publisher.getDescription());
        }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        onBackPressed();
    }
}
