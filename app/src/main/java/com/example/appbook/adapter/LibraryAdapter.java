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
import com.example.appbook.LibraryDetailsActivity;
import com.example.appbook.ModifyLibraryActivity;
import com.example.appbook.R;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryHolder> {

    private Context context;
    private List<Library> libraryList;

    public LibraryAdapter(Context context, List<Library> dataList) {
        this.context = context;
        this.libraryList = dataList; //lista de librerias
    }

    @Override
    public LibraryAdapter.LibraryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.library_item, parent, false);
        return new LibraryHolder(view);
    }

    @Override
    public void onBindViewHolder(LibraryHolder holder, int position) {
        holder.libraryName.setText(libraryList.get(position).getName());
        holder.libraryCity.setText(libraryList.get(position).getCity());
        holder.libraryZipCode.setText(libraryList.get(position).getZipCode());
        holder.libraryPhoneNumber.setText(libraryList.get(position).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return libraryList.size();
    }

    public class LibraryHolder extends RecyclerView.ViewHolder {
        public TextView libraryName;
        public TextView libraryCity;
        public TextView libraryZipCode;
        public TextView libraryPhoneNumber;
        public Button seeLibraryButton;
        public Button modifyLibraryButton;
        public Button deleteLibraryButton;
        public View parentView;

        public LibraryHolder(View view) {
            super(view);
            parentView = view;

            libraryName = view.findViewById((R.id.library_name));
            libraryCity = view.findViewById(R.id.library_city);
            libraryZipCode = view.findViewById(R.id.library_zip_code);
            libraryPhoneNumber = view.findViewById(R.id.library_phone_number);
            seeLibraryButton = view.findViewById(R.id.see_library_button);
            modifyLibraryButton = view.findViewById(R.id.modify_library_button);
            deleteLibraryButton = view.findViewById(R.id.delete_library_button);

            seeLibraryButton.setOnClickListener(v -> seeLibraryDetails(getAdapterPosition()));
            modifyLibraryButton.setOnClickListener(v -> modifyLibrary(getAdapterPosition()));
            deleteLibraryButton.setOnClickListener(v -> deleteLibrary(getAdapterPosition()));
        }

        //ver detalles
        private void seeLibraryDetails(int position) {
            Library library = libraryList.get(position);

            Intent intent = new Intent(context, LibraryDetailsActivity.class);
            intent.putExtra("libraryId", library.getLibraryId());
            context.startActivity(intent);
        }

        private void modifyLibrary(int position) {
           Library library = libraryList.get(position);

            Intent intent = new Intent(context, ModifyLibraryActivity.class);
            intent.putExtra("libraryId", library.getLibraryId());
            context.startActivity(intent);
        }

        //eliminar libro
        private void deleteLibrary(int position) {

            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_library_message)
                    .setTitle(R.string.delete_library_message)
                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si

                //conectar BBDD
                final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries().build();
                Library library = libraryList.get(position);
                db.libraryDao().delete(library); //borramos de la BBDD

                libraryList.remove(position); //borrar de la lista que se muestra
                notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
                })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
