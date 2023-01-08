package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;
import com.mapbox.maps.MapView;

public class AddBookActivity extends AppCompatActivity {

    Button btnCamera; //boton para abrir la camera
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        btnCamera = findViewById(R.id.camara_add_book_button);
        imageView = findViewById(R.id.book_add_image);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera();
            }
        });
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

    private void camera() {
        Intent takeFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeFotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeFotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
