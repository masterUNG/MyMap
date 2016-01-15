package appewtc.masterung.mymap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String centerLatString, centerLngString;
    private LatLng centerLatLng;
    private Double[] myLatDoubles = {13.66938156, 13.66531579, 13.66431498, 13.67071595};
    private Double[] myLngDoubles = {100.62350035, 100.6226635, 100.61676264, 100.61671972};
    private LatLng[] myLatLngs;

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

        //For Other Marker
        myLatLngs = new LatLng[myLatDoubles.length];
        for (int i=0;i<myLatDoubles.length;i++) {
            myLatLngs[i] = new LatLng(myLatDoubles[i], myLngDoubles[i]);
        }   // for

    }   // createLatLng


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup Center Map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

        //Create Marker of Center Map
//        mMap.addMarker(new MarkerOptions().position(centerLatLng)); //Default Marker
        mMap.addMarker(new MarkerOptions()
                .position(centerLatLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.friend))); // Image Marker From Resource

        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[0])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[1])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[2])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[3])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));


    }   // onMapReady

}   // Main Class
