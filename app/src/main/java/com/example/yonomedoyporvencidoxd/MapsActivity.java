package com.example.yonomedoyporvencidoxd;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.yonomedoyporvencidoxd.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;  // Solicitud permiso ubi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Verificar permisos de ubicación y activa la ubicación del dispositivo si está permitido.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        // spots
        LatLng spot = new LatLng(-36.62302679602536, -72.0803525215769);
        LatLng spot1 = new LatLng(-36.60269874770805, -72.08728446578257);
        LatLng spot2 = new LatLng(-36.607591083455596, -72.10470376266414);
        LatLng spot3 = new LatLng(-36.623963195481295, -72.13131447910023);
        LatLng spot4 = new LatLng(-36.650897565338276, -72.41323409197415);

        mMap.addMarker(new MarkerOptions().position(spot).title("Frutos de los Bosques Sucursal #1"));
        mMap.addMarker(new MarkerOptions().position(spot1).title("Frutos de los Bosques Sucursal #2"));
        mMap.addMarker(new MarkerOptions().position(spot2).title("Frutos de los Bosques Sucursal #3"));
        mMap.addMarker(new MarkerOptions().position(spot3).title("Frutos de los Bosques Sucursal #4"));
        mMap.addMarker(new MarkerOptions().position(spot4).title("Frutos de los Bosques Sucursal #5"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(spot2));
        mMap.setMinZoomPreference(4.0F);
        mMap.setMaxZoomPreference(18.0f);
    }

    // Habilitar ubi map.
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);  // Llamar al método de la superclase.

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}