package com.example.appbook;

import static com.example.appbook.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.appbook.db.AppDatabase;
import com.example.appbook.domain.Library;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView); //cargamos el mapa
        initializePointManager(); //inicializamos el pointmanager

        //conectamos a BBDD para coger los objetos que queremos pintar
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        List<Library> libraries = db.libraryDao().getAll();
        addLibraryToMap(libraries);
    }

    //lista de librerias y un for para poner un marker en cada una
    private void addLibraryToMap(List<Library> libraries) {
        for (Library library : libraries) {
            Point point = Point.fromLngLat(library.getLongitude(), library.getLatitude());
            addMarker(point, library.getName());
        }
        Library lastLibrary = libraries.get(libraries.size() - 1); //ultima ubicacion
        setCameraPosition(Point.fromLngLat(lastLibrary.getLongitude(), lastLibrary.getLatitude())); //fijamos la camara en la ultima ubicacion
    }

    //iniciar pointManager
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    //añadir marker
    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    //fijar camara del mapa en la ubicacion que queremos
    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
}