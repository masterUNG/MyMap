package appewtc.masterung.mymap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private String centerLatString, centerLngString;
    private LatLng centerLatLng;
    private Double[] myLatDoubles = {13.66938156, 13.66531579, 13.66431498, 13.67071595};
    private Double[] myLngDoubles = {100.62350035, 100.6226635, 100.61676264, 100.61671972};
    private LatLng[] myLatLngs;
    private Button normalButton, satelliteButton, terrainButton, hybridButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_maps);

        //Bind Widget
        bindWidget();

        //Create LatLng
        createLatLng();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }   // Main Method

    private void bindWidget() {
        normalButton = (Button) findViewById(R.id.button2);
        satelliteButton = (Button) findViewById(R.id.button3);
        terrainButton = (Button) findViewById(R.id.button4);
        hybridButton = (Button) findViewById(R.id.button5);
    }

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

        changeTypeMap();


    }   // onMapReady

    private void changeTypeMap() {

        normalButton.setOnClickListener(this);
        satelliteButton.setOnClickListener(this);
        terrainButton.setOnClickListener(this);
        hybridButton.setOnClickListener(this);

    }   // changeTypeMap

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button2:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.button3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.button4:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.button5:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }   // switch

    }   // onClick
}   // Main Class
