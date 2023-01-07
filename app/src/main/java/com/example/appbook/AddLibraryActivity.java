package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import android.database.sqlite.SQLiteConstraintException;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;

public class AddLibraryActivity extends AppCompatActivity {

    private MapView libraryMap; //para registrar ubicacion en el mapa
    private Point point; //latitud y longitud
    private PointAnnotationManager pointAnnotationManager; //anotar point

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_library);

        libraryMap = findViewById(R.id.libraryMapView); //pasamos el mapa

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(libraryMap);
        gesturesPlugin.addOnMapClickListener(point -> {
            removeAllMarkers(); //borrar todos los markers anteriores
            this.point = point; //guardamos la longitud y latitud
            addMarker(point);
            return true;
        });

        initializePointManager(); //para crearlo al arrancar
    }

    //boton AÑADIR
    public void addButton(View view) {
        EditText etName = findViewById(R.id.name_library_edit_text);
        EditText etCity = findViewById(R.id.city_edit_text);
        EditText etZipCode = findViewById(R.id.zip_code_edit_text);
        EditText etPhoneNumber = findViewById(R.id.phone_number_edit_text);

        String name = etName.getText().toString();
        String city = etCity.getText().toString();
        String zipCode = etZipCode.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        //hacemos if para el caso de que el usuario no elija una ubicación y no de error a grabar al entrada
        if (point == null) {
            //Snackbar.make(etName, "Elige una ubicación", BaseTransientBottomBar.LENGTH_LONG);
            Toast.makeText(this,"Elige una ubicación", Toast.LENGTH_SHORT).show();
            return;
        }

        //crear libro
        Library library = new Library(name, city, zipCode, phoneNumber, point.latitude(), point.longitude()); //primero siempre la latitud

        //creo BBDD
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        try {
            db.libraryDao().insert(library); //añadimos a la BBDD

            //mensaje emergente
            Snackbar.make(etName, R.string.library_add_message, BaseTransientBottomBar.LENGTH_LONG);
            etName.setText("");
            etCity.setText("");
            etZipCode.setText("");
            etPhoneNumber.setText("");
            etName.requestFocus();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, "Ha ocurrido un error. Comprueba los datos e intentalo de nuevo.", BaseTransientBottomBar.LENGTH_LONG);
        }
    }

    //inicializar el PointManager
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(libraryMap);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

        //boton CANCELAR
    public void cancelButton(View view) {
        onBackPressed();

    }

    //añadir red marker en el mapa
    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    //eliminar markers anteriores
    private void removeAllMarkers() {
        pointAnnotationManager.deleteAll();
    }

}
