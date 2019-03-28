package com.example.kazajii.local;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMS_CALL_ID = 1234;
    private LocationManager lm;
    private MapFragment  mapFragment;
    private GoogleMap googleMap;
    private ArrayList<Pharmacie> pharmacies;
    private FloatingActionButton btn_floatinbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacie("Pharmacie De LOGPOM","Ouvert de LUNDI a DIMANCHE CONTACT:002376940334556",9.770459,4.082797,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie NOTRE DAME DE VICTOIRE","Ouvert tous les jours 8h 22h CONTACT:2 33 06 42 44",9.764889,4.087271,"Nkonsamba","Patenon"));
        pharmacies.add(new Pharmacie("Pharmacie De MAKEPE","Ouvert de 8h a 16h CONTACT:233475555",9.755205,4.084965,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie De KOTTO","Ouvert de 8h a 16h CONTACT:697041056",9.749494 ,4.092272,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie SACRE COEUR","Ouvert de 8h a 16h CONTACT:non renseigner",9.759854 ,4.065940,"Yaounde","Mvan"));
        pharmacies.add(new Pharmacie("Pharmacie De PK 11","Ouvert de 8h a 16h CONTACT:6 99 12 24 49",9.776792 ,4.051216,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie De LA REPUBLIQUE PK 14","Ouvert de 8h a 16h CONTACT:6 96 23 57 75",9.741312 ,4.090018,"Douala","AKWA"));
        pharmacies.add(new Pharmacie("Pharmacie De PK 8","Ouvert de 07h30 a 21h CONTACT:2 33 37 64 96",9.753631 ,4.046282,"Nkonsamba","Patenon"));
        pharmacies.add(new Pharmacie("Pharmacie De L'AEROPORT","Ouvert de 8h a 21h CONTACT:2 33 42 28 76",9.741312 ,4.090018,"Douala","Bali"));
        pharmacies.add(new Pharmacie("Pharmacie OLYMPIQUE DE NDOKOTTI","Ouvert de 8h a 16h CONTACT:2 33 37 02 97",9.741312 ,4.090018,"Douala","Bonapriso"));
        pharmacies.add(new Pharmacie("Pharmacie Du RAIL","Ouvert de 07h30 a 20h CONTACT:2 33 42 66 18",9.711910 ,4.037775,"Douala","Bonaberie"));

        FragmentManager fragmentManager= getFragmentManager();
        mapFragment=  ( MapFragment ) fragmentManager.findFragmentById(R.id.map);
        btn_floatinbutton  =   findViewById(R.id.floatingActionButton);

        btn_floatinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        ListPharmacisActivity.class
                );
                startActivity(intent);
            }

        });
    }

    @Override
    // @SuppressWarnings("MissingPermission")
    protected void onResume() {
        super.onResume();

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        CheckPermission();

    }
    private void CheckPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID );
            return;
        }
        LoadMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID) {
            CheckPermission();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(lm != null){
            lm.removeUpdates(this);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void LoadMap()
    {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MainActivity.this.googleMap =googleMap;
                googleMap.moveCamera(CameraUpdateFactory.zoomBy(30) );
                googleMap.setMyLocationEnabled(true);
                for( Pharmacie p: pharmacies)
                    googleMap.addMarker( new MarkerOptions().position( new LatLng(p.Latitude, p.Longitude))   .title(p.Name). snippet(p.Description)
                            .icon((BitmapDescriptorFactory.fromResource(R.drawable.icon)))
                    );
            }
        });


    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

       // Toast.makeText(this,"location :"+ latitude  +"/"+  longitude, Toast.LENGTH_LONG).show();
       // if(googleMap != null){
             LatLng googleLocation = new LatLng( latitude,longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(googleLocation));
       // }

    }


}
