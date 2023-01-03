package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appbook.adapter.BookAdapter;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class ListBooksActivity extends AppCompatActivity {

    private List<Book> bookList;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        bookList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.book_list);
        recyclerView.setHasFixedSize(true);;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        bookList.clear();
        bookList.addAll(db.bookDao().getAll());
        adapter.notifyDataSetChanged();
    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_book, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Book) {
            Intent intent = new Intent(this, AddBookActivity.class); //para ir a otra activity
            startActivity(intent);
            return true;
        }
        return false;
    }
}