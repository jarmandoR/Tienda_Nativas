package com.example.tienda_nativas.Activities;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.tienda_nativas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // Obtener el fragmento del mapa y notificar cuando esté listo
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Botón para volver
        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(view -> finish());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Coordenadas de las tres sedes de la tienda
        LatLng punto1 = new LatLng(4.7110, -74.0721); // direccion 1
        LatLng punto2 = new LatLng(4.6486, -74.2479); // direccion 2
        LatLng punto3 = new LatLng(4.6791, -74.0498); // direccion 3

        // Marcadores
        mMap.addMarker(new MarkerOptions().position(punto1).title("Centro de Bogotá"));
        mMap.addMarker(new MarkerOptions().position(punto2).title("Bosa"));
        mMap.addMarker(new MarkerOptions().position(punto3).title("Chapinero"));

        // Enfoca el mapa en el primer punto con zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1, 12));
    }
}