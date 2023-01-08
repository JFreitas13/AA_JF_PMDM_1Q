package com.example.appbook.adapter;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appbook.BookDetailsActivity;
import com.example.appbook.ModifyBookActivity;
import com.example.appbook.R;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.bookList = dataList; //lista de libros
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
        holder.bookYearEdition.setText(bookList.get(position).getYearEditionString());
        holder.bookPageNumber.setText(bookList.get(position).getPagesNumberString());
        holder.bookDescription.setText(bookList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookName;
        public TextView bookYearEdition;
        public TextView bookPageNumber;
        public TextView bookDescription;
        public Button seeBookButton;
        public Button modifyBookButton;
        public Button deleteBookButton;
        public View parentView;

        public BookHolder(View view) {
            super(view);
            parentView = view;

            bookName = view.findViewById((R.id.book_name));
            bookYearEdition = view.findViewById(R.id.book_yearEdition);
            bookPageNumber = view.findViewById(R.id.book_pageNumber);
            bookDescription = view.findViewById(R.id.book_description);
            seeBookButton = view.findViewById(R.id.see_book_button);
            modifyBookButton = view.findViewById(R.id.modify_book_button);
            deleteBookButton = view.findViewById(R.id.delete_book_button);

            seeBookButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));

            modifyBookButton.setOnClickListener(v -> modifyBook(getAdapterPosition()));

            deleteBookButton.setOnClickListener(v -> deleteBook(getAdapterPosition()));
        }

        //ver detalles
        private void seeDetails(int position) {
            Book book = bookList.get(position);

            Intent intent = new Intent(context, BookDetailsActivity.class);
            intent.putExtra("name", book.getName());
            context.startActivity(intent);
        }

        private void modifyBook(int position) {
            Book book = bookList.get(position);

            Intent intent = new Intent(context, ModifyBookActivity.class);
            intent.putExtra("book_id", book.getId());
            context.startActivity(intent);
        }

        //eliminar libro
        private void deleteBook(int position) {

            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_book_message)
                    .setTitle(R.string.delete_book_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si

                //conectar BBDD
                final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries().build();
                Book book = bookList.get(position);
                db.bookDao().delete(book); //borramos de la BBDD

                bookList.remove(position); //borrar de la lista que se muestra
                notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
                })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }
}
