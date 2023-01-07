package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appbook.adapter.LibraryAdapter;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;

import java.util.ArrayList;
import java.util.List;

public class ListLibrariesActivity extends AppCompatActivity {

    private List<Library> libraryList;
    private LibraryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_libraries);

        //instanciar la lista a vacio
        libraryList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.library_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LibraryAdapter(this, libraryList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //objeto que permite acceder a la BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        libraryList.clear(); //vaciar la BBDD
        libraryList.addAll(db.libraryDao().getAll()); //cojo todos los elementos que devuelva la BBDD
        adapter.notifyDataSetChanged();
    }

    public void mainReturnButton(View view) {
        onBackPressed();
    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_library, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Library) {
            Intent intent = new Intent(this, AddLibraryActivity.class); //para ir a otra activity
            startActivity(intent);
            return true;
        }
        return false;
    }
}