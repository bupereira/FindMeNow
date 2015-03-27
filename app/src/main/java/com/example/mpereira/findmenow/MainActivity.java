package com.example.mpereira.findmenow;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView tvMyLocation;

    private LocationManager mLocManager;
    private LocationListener mLocListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMyLocation = (TextView) findViewById(R.id.myLocation);

        // Set Location Variables
        mLocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocListener = new MyLocationListener();
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocListener);

        // From example: http://www.firstdroid.com/2010/04/29/android-development-using-gps-to-get-current-location-2/


        final Button btRefresh = (Button) findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMyLocation.setText(getLocation());
            }
        });

        final Button btShare = (Button) findViewById(R.id.btShare);
        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Something something something Share With...
                // Probably start activity with only the intent and create a picker with action.send??
                Uri mLocation = Uri.parse("geo:" + tvMyLocation.getText());
                Intent mIntent = new Intent(Intent.ACTION_SEND, mLocation);
                startActivity(mIntent);
            }
        });

        final Button btEmergencySend = (Button) findViewById(R.id.btEmSend);
        btEmergencySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Something something something Send through SMS
            }
        });

        btRefresh.callOnClick();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getLocation () {
        // TODO: Something something something Dark Side... Something something something Complete!

        String mReturnValue = new String();
        Location location = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null)
            mReturnValue = location.getLatitude() + "N," + location.getLongitude() + "E";
        else
            mReturnValue = "Invalid location";
        return mReturnValue;
    }


    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged (Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            Log.i("MuriloLatitude:", String.valueOf(loc.getLatitude()));
            Log.i("MuriloLatitude:", String.valueOf(loc.getLongitude()));
            tvMyLocation.setText(String.valueOf(loc.getLatitude()) + " " + String.valueOf(loc.getLongitude()));
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }

}

