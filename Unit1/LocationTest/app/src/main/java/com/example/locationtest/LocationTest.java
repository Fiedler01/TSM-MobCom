package com.example.locationtest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class LocationTest extends Activity implements LocationListener {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String[] ACCURACY = {"invalid", "n/a", "fine",
            "coarse"};
    private static final String[] POWER_REQ = {"invalid", "n/a", "low",
            "medium", "high"};
    private static final String[] STATUS = {"out of service",
            "temporarily unavailable", "available"};

    private LocationManager mgr;
    private TextView tv;
    private String best; // name of the best suited location provider

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // get a reference to the location manager
        mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        // get a reference to the text view to display results
        tv = findViewById(R.id.tv);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            requestPermission();
        } else {
            // Permission is granted
            executeLocationCode();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // To save battery only request updates when activity that displays
        // runs in foreground
        if (best != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mgr.requestLocationUpdates(best, 15000, 1, this);
            // parameter: provider name,
            // time delay for updates in ms,
            // spatial distance for updates in m,
            // reference to location listener
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop updates to save power while app paused
        mgr.removeUpdates(this);
    }

    public void onLocationChanged(Location location) {
        dumpLocation(location);
    }

    public void onProviderDisabled(String provider) {
        log("\nProvider disabled: " + provider);
    }

    public void onProviderEnabled(String provider) {
        log("\nProvider enabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        log("\nProvider status changed: " + provider + ", status="
                + STATUS[status] + ", extras=" + extras);
    }

    /**
     * Write a string to the textView
     */
    private void log(String string) {
        tv.append(string + "\n");
    }

    /**
     * dump information from all location providers
     */
    private void dumpProviders() {
        List<String> providers = mgr.getAllProviders();
        for (String provider : providers) {
            dumpProvider(provider);
        }
    }

    /**
     * dump information from a single location provider
     */
    private void dumpProvider(String provider) {
        LocationProvider info = mgr.getProvider(provider);
        String builder = "LocationProvider[" + "name=" +
                info.getName() + ",enabled=" +
                mgr.isProviderEnabled(provider) +
                ",getAccuracy=" +
                ACCURACY[info.getAccuracy() + 1] +
                ",getPowerRequirement=" +
                POWER_REQ[info.getPowerRequirement() + 1] +
                ",hasMonetaryCost=" + info.hasMonetaryCost() +
                ",requiresCell=" + info.requiresCell() +
                ",requiresNetwork=" + info.requiresNetwork() +
                ",requiresSatellite=" + info.requiresSatellite() +
                ",supportsAltitude=" + info.supportsAltitude() +
                ",supportsBearing=" + info.supportsBearing() +
                ",supportsSpeed=" + info.supportsSpeed() +
                "]";
        log(builder);
    }

    /**
     * Describe the given location, which might be null
     */
    private void dumpLocation(Location location) {
        if (location == null)
            log("\nLocation[unknown]");
        else
            log("\n" + location);
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LocationTest.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                executeLocationCode();
            } else {
                log("Location permissions denied");
            }
        }
    }

    private void executeLocationCode() {
        log("Location providers:");
        // display a list of all location providers
        dumpProviders();

        // Criteria to restrict Android's choice of the location provider
        Criteria criteria = new Criteria();
        // restrictions on cost, power, and requirements on accuracy
        // would go here
        best = mgr.getBestProvider(criteria, true);
        log("\nBest provider is: " + best);

        for(String provider : mgr.getAllProviders()) {
            log("\nName of Provider: " + provider);
            logLocationForProvider(provider);
        }
    }

    private void logLocationForProvider(String provider) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = mgr.getLastKnownLocation(provider);
        dumpLocation(location);
    }
}
