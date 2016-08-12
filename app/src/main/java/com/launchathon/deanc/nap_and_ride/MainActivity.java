package com.launchathon.deanc.nap_and_ride;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.lat) EditText lat;
    @Bind(R.id.lon) EditText lon;
    @Bind(R.id.wakeup_distance) EditText wake_up;

    Long userLat, userLon;

    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        manager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));


        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 1000, (float) 10, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

        });
        Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        userLat = Long.valueOf((String.valueOf(location.getLatitude())));
        userLon = Long.valueOf((String.valueOf(location.getLongitude())));




    }

    @OnClick(R.id.submitButton)
    public void setStop() {
        Long latitude = Long.valueOf(lat.getText().toString());
        Long longituge = Long.valueOf(lon.getText().toString());
        Float radius = Float.valueOf(wake_up.getText().toString());

        Geofence.Builder builder = new Geofence.Builder();
        builder.setCircularRegion(latitude, longituge, radius)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER);
        Geofence geofence = builder.build();
        getGeofencingRequest(geofence);



    }

    private GeofencingRequest getGeofencingRequest(Geofence geofence) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(geofence);
        return builder.build();
    }

    private void triggerNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("arrived");
        manager.notify(0, builder.build());
    }
}
