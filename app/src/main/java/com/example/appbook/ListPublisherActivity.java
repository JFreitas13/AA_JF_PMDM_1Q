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

import com.example.appbook.adapter.PublisherAdapter;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Publisher;

import java.util.ArrayList;
import java.util.List;

public class ListPublisherActivity extends AppCompatActivity {

    private List<Publisher> publisherList;
    private PublisherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_publisher);

        //instanciar la lista a vacio
        publisherList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.publisher_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PublisherAdapter(this, publisherList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //objeto que permite acceder a la BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        publisherList.clear(); //vaciar la BBDD
        publisherList.addAll(db.publisherDao().getAll()); //cojo todos los elementos que devuelva la BBDD
        adapter.notifyDataSetChanged();
    }

    public void mainReturnButton(View view) {
        onBackPressed();
    }

    //crear el menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_publisher, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Publisher) {
            Intent intent = new Intent(this, AddPublisherActivity.class); //para ir a otra activity
            startActivity(intent);
            return true;
        }
        return false;
    }
}