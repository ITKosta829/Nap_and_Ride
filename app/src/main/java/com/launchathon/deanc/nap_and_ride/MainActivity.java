package com.launchathon.deanc.nap_and_ride;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
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
    NotificationManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));


        ButterKnife.bind(this);




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
