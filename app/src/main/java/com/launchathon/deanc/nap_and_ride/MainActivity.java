package com.launchathon.deanc.nap_and_ride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.lat) EditText lat;
    @Bind(R.id.lon) EditText lon;
    @Bind(R.id.wakeup_distance) EditText wake_up;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);


    }

    @OnClick(R.id.submitButton)
    public void setStop() {
        String a = lat.getText().toString();
        String b = lon.getText().toString();

    }
}
