package appewtc.masterung.mymap;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public Location requestUpdateFromProvider(String strProvider, String strError) {

        Location objLocation = null;

        if (objLocationManager.isProviderEnabled(strProvider)) {

            objLocationManager.requestLocationUpdates(strProvider, 1000, 10, objLocationListener);

        } else {
            Toast.makeText(MainActivity.this, strError, Toast.LENGTH_SHORT).show();
        }

        return null;
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

        objLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);

    }   // openServiceGetLocation

    public void clickMyMap(View view) {

        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(objIntent);

    }

}   // Main Class
