package com.example.appbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbook.R;
import com.example.appbook.domain.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.bookList = dataList;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        holder.bookName.setText(bookList.get(position).getName());
        holder.bookYearEditon.setText(bookList.get(position).getYearEdition());
        holder.bookPageNumber.setText(bookList.get(position).getPagesNumber());
        holder.bookDescription.setText(bookList.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookYearEditon;
        public TextView bookPageNumber;
        public TextView bookDescription;
        public View parentView;

        public BookHolder(View view) {
            super(view);
            parentView = view;

            bookName = view.findViewById((R.id.book_name));
            bookYearEditon = view.findViewById(R.id.book_yearEdition);
            bookPageNumber = view.findViewById(R.id.book_pageNumber);
            bookDescription = view.findViewById(R.id.book_description);
        }
    }
}
