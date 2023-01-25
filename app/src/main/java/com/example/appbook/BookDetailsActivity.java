package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //coger el libro por el id
        Intent intent = getIntent();
        long bookId = getIntent().getLongExtra("bookId", 0);


        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Book book = db.bookDao().getById(bookId);
        fillData(book);
    }

    private void fillData(Book book) {
        TextView tvName = findViewById(R.id.book_name_details);
        TextView tvYearEditionS = findViewById(R.id.book_yearEdition_details);
        TextView tvPagesNumber = findViewById(R.id.book_pageNumber_details);
        TextView tvDescription = findViewById(R.id.book_description_details);
//        TextView tvPublisher = findViewById(R.id.book_publisher);
//        TextView tvLibrary = findViewById(R.id.book_library);
//        CheckBox cbRead = findViewById(R.id.read_check_box);

        tvName.setText(book.getName());
        tvYearEditionS.setText(book.getYearEditionString());
        tvPagesNumber.setText(book.getPagesNumberString());
        tvDescription.setText(book.getDescription());
//        tvPublisher.setText((int) book.getPublisher_id());
//        tvLibrary.setText((int) book.getLibrary_id());
//        cbRead.setChecked(book.isRead());
    }

    //boton CANCELAR
    public void returnButton(View view) {
        onBackPressed();
    }
}
