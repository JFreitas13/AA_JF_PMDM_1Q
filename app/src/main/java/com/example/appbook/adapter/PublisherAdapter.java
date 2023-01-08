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

import com.example.appbook.ModifyPublisherActivity;
import com.example.appbook.PublisherDetailsActivity;
import com.example.appbook.R;
import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Publisher;

import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.PublisherHolder> {

    private Context context;
    private List<Publisher> publisherList;

    public PublisherAdapter(Context context, List<Publisher> dataList) {
        this.context = context;
        this.publisherList = dataList; //lista de libros
    }

    @Override
    public PublisherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.publisher_item, parent, false);
        return new PublisherHolder(view);
    }

    @Override
    public void onBindViewHolder(PublisherHolder holder, int position) {
        holder.publisherName.setText(publisherList.get(position).getName());
        holder.publisherPhoneNumber.setText(publisherList.get(position).getPhoneNumber());
        holder.publisherDescription.setText(publisherList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return publisherList.size();
    }

    public class PublisherHolder extends RecyclerView.ViewHolder {
        public TextView publisherName;
        public TextView publisherPhoneNumber;
        public TextView publisherDescription;
        public Button seePublisherButton;
        public Button modifyPublisherButton;
        public Button deletePublisherButton;
        public View parentView;

        public PublisherHolder(View view) {
            super(view);
            parentView = view;

            publisherName = view.findViewById((R.id.publisher_name));
            publisherPhoneNumber = view.findViewById(R.id.publisher_phone_number);
            publisherDescription = view.findViewById(R.id.publisher_description);
            seePublisherButton = view.findViewById(R.id.see_publisher_button);
            modifyPublisherButton = view.findViewById(R.id.modify_publisher_button);
            deletePublisherButton = view.findViewById(R.id.delete_publisher_button);

            seePublisherButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));

            modifyPublisherButton.setOnClickListener(v -> modifyPublisher(getAdapterPosition()));

            deletePublisherButton.setOnClickListener(v -> deletePublisher(getAdapterPosition()));
        }

        //ver detalles
        private void seeDetails(int position) {
            Publisher publisher = publisherList.get(position);

            Intent intent = new Intent(context, PublisherDetailsActivity.class);
            intent.putExtra("name", publisher.getName());
            context.startActivity(intent);
        }

        private void modifyPublisher(int position) {
            Publisher publisher = publisherList.get(position);

            Intent intent = new Intent(context, ModifyPublisherActivity.class);
            intent.putExtra("publisher_id", publisher.getId());
            context.startActivity(intent);
        }

        //eliminar libro
        private void deletePublisher(int position) {

            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_publisher_delete_message)
                    .setTitle(R.string.delete_publisher_title)
                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si

                //conectar BBDD
                final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries().build();
                Publisher publisher = publisherList.get(position);
                db.publisherDao().delete(publisher); //borramos de la BBDD

                publisherList.remove(position); //borrar de la lista que se muestra
                notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
                })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }
}
