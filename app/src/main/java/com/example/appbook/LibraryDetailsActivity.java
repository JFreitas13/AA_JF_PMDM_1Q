package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;

import java.util.concurrent.atomic.AtomicReference;

public class LibraryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_details);

        //coger el libreria por el id
        Intent intent = getIntent();
        long library_id = getIntent().getLongExtra("libraryId", 0);


        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Library library = db.libraryDao().getByLibraryId(library_id);
        fillData(library);
    }

        private void fillData(Library library) {
            TextView tvName = findViewById(R.id.library_name_details);
            TextView tvCity = findViewById(R.id.library_city_details);
            TextView tvZipCode = findViewById(R.id.library_zip_code_details);
            TextView tvPhoneNumber = findViewById(R.id.library_phone_number_details);

            tvName.setText(library.getName());
            tvCity.setText(library.getCity());
            tvZipCode.setText(library.getZipCode());
            tvPhoneNumber.setText(library.getPhoneNumber());
        }

    //boton CANCELAR
    public void returnButton(View view) {
        onBackPressed();
    }
}
