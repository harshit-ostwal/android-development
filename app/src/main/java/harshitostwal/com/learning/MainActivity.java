package harshitostwal.com.learning;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import harshitostwal.com.learning.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback {

    LocationManager manager;
    private GoogleMap mMap;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng india = new LatLng(13.1888014, 80.1853136);
        LatLng india1 = new LatLng(13.0343246, 80.2665041);
        mMap.setMinZoomPreference(10);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.addMarker(new MarkerOptions().position(india).title("Sri Vinayak Jewels"));
        mMap.addMarker(new MarkerOptions().position(india1).title("Citi Hardware & Power Tools"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(india));
    }

    public void location(View v) {

        boolean isGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isInternet = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        // Permission check
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    1);
            return;
        }

        // Network provider
        if (isInternet) {
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 10, this);
            Toast.makeText(this, "Fetching location from Internet", Toast.LENGTH_SHORT).show();
        }

        // GPS provider
        if (isGPS) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 10, this);
            Toast.makeText(this, "Fetching location from GPS", Toast.LENGTH_SHORT).show();
        }

        if (!isGPS && !isInternet) {
            Toast.makeText(this, "No provider enabled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        Toast.makeText(this, "Lat: " + lat + ", Lon: " + lon, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {}

    @Override
    public void onProviderDisabled(@NonNull String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
