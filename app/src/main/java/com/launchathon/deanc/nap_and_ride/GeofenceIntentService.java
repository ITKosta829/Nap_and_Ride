package com.launchathon.deanc.nap_and_ride;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GeofenceIntentService extends IntentService {


    public GeofenceIntentService() {
        super("GeofenceIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event != null) {

            int transition = event.getGeofenceTransition();
            if (transition == Geofence.GEOFENCE_TRANSITION_ENTER){
                NotificationManager manager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentTitle("arrived")
                        .setVibrate(new long[]{0, 500, 500, 500, 500});
                manager.notify(0, builder.build());

            }

        }







    }




}
