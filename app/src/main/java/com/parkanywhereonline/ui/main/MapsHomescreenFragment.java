package com.parkanywhereonline.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.parkanywhereonline.AddSpotActivity;
import com.parkanywhereonline.R;

import java.util.HashMap;

public class MapsHomescreenFragment extends Fragment {
    private FusedLocationProviderClient client;
    private GoogleMap map;
    private final int REQUEST_CODE = 991;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to tclient = LocationServices.getFusedLocationProviderClient(getActivity());he app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            /**
             * Create your variable where you store all your data mapped to the marker ID,
             * make it accessible where you want.
             * The key of this hashmap is your marker ID, the value is another Map with extra data
             */
            HashMap<String, HashMap> extraMarkerInfo = new HashMap<String, HashMap>();

            // Map stuff
            map = googleMap;
            updateMapToLocationIfEnabled();
            final LatLng theTrapHouse = new LatLng(42.995935, -81.279263);
            map.moveCamera(CameraUpdateFactory.newLatLng(theTrapHouse));
            Marker marker = map.addMarker(
                    new MarkerOptions()
                            .position(theTrapHouse)
                            .draggable(true));

        }
    };


    @SuppressLint("MissingPermission")
    public void updateMapToLocationIfEnabled() {


        if (!areLocationPermissionsEnabled()) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
        }

        if (areLocationPermissionsEnabled()){
            setMapToLastLocation();
        }
    }
    public void moveCamera(LatLng latlng, float zoom){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));
    }
            // Get the location and update the UI
            // Update the map with the current location
            // Set the center of the map to be user's location




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homescreen_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE && (grantResults[0] == PackageManager.PERMISSION_GRANTED
            || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            setMapToLastLocation();
        }
    }

    public boolean areLocationPermissionsEnabled() {
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED);
    }

    @SuppressLint("MissingPermission")
    public void setMapToLastLocation() {
        client = LocationServices.getFusedLocationProviderClient(getContext());
        Task location = client.getLastLocation();

        map.setMyLocationEnabled(true);
        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location currentLocation = (Location) task.getResult();
                    moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()) ,10f);
                }
            }
        });
    }
}