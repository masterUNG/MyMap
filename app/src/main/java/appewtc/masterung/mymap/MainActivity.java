package appewtc.masterung.mymap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TextView showLatTextView, showLngTextView;
    private LocationManager objLocationManager;
    private Criteria objCriteria;
    private boolean GPSABoolean, networkABoolean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Open Service Get Location
        openServiceGetLocation();

    }   // Main Method

    @Override
    protected void onStart() {
        super.onStart();

        GPSABoolean = objLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!GPSABoolean) {

            //ไม่มี GPS
            networkABoolean = objLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!networkABoolean) {

                //ไม่มี Net
                Toast.makeText(MainActivity.this, "Stant Alone", Toast.LENGTH_SHORT).show();

            }   // if2

        }   // if1


    }   // onStart

    @Override
    protected void onResume() {
        super.onResume();
        setupForRestart();
    }

    private void setupForRestart() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        objLocationManager.removeUpdates(objLocationListener);
        String strLat = "UnKnow";
        String strLng = "UnKnow";

        Location networkLocation = requestLocation(LocationManager.NETWORK_PROVIDER, "Internet not Connected");
        if (networkLocation != null) {

            strLat = String.format("%.7f", networkLocation.getLatitude());
            strLat = String.format("%.7f", networkLocation.getLongitude());

        }   // if

        Location GPSLocation = requestLocation(LocationManager.GPS_PROVIDER, "GPS false");
        if (GPSLocation != null) {

            strLat = String.format("%.7f", GPSLocation.getLatitude());
            strLng = String.format("%.7f", GPSLocation.getLongitude());

        }   // if

        showLatTextView.setText(strLat);
        showLngTextView.setText(strLng);

    }   // setupForRestart

    @Override
    protected void onStop() {
        super.onStop();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        objLocationManager.removeUpdates(objLocationListener);

    }

    public Location requestLocation(String strProvider, String strError) {

        Location objLocation = null;

        if (objLocationManager.isProviderEnabled(strProvider)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            objLocationManager.requestLocationUpdates(strProvider, 1000, 10, objLocationListener);
            objLocation = objLocationManager.getLastKnownLocation(strProvider);

        } else {
            Toast.makeText(MainActivity.this, strError, Toast.LENGTH_SHORT).show();
        }

        return objLocation;
    }

    private void bindWidget() {
        showLatTextView = (TextView) findViewById(R.id.txtShowLat);
        showLngTextView = (TextView) findViewById(R.id.txtShowLng);
    }


    //Create Class for Find Location
    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            showLatTextView.setText(String.format("%.7f", location.getLatitude()));
            showLngTextView.setText(String.format("%.7f", location.getLongitude()));

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };  // End of Class


    private void openServiceGetLocation() {

        objLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);

    }   // openServiceGetLocation

    public void clickMyMap(View view) {

        String strLat = showLatTextView.getText().toString();
        String strLng = showLngTextView.getText().toString();

        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);

        //Sent lat,lng to MapsActivity
        objIntent.putExtra("Lat", strLat);
        objIntent.putExtra("Lng", strLng);

        startActivity(objIntent);

    }   // clickMyMap

}   // Main Class
