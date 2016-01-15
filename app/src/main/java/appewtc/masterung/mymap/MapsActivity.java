package appewtc.masterung.mymap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String centerLatString, centerLngString;
    private LatLng centerLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Create LatLng
        createLatLng();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }   // Main Method

    private void createLatLng() {

        //For Center Map
        centerLatString = getIntent().getStringExtra("Lat");
        centerLngString = getIntent().getStringExtra("Lng");

        //Change Data Type
        Double latDouble = Double.parseDouble(centerLatString);
        Double lngDouble = Double.parseDouble(centerLngString);

        centerLatLng = new LatLng(latDouble, lngDouble);

    }   // createLatLng


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup Center Map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

    }   // onMapReady

}   // Main Class
