package com.parkanywhereonline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.textfield.TextInputEditText;
import com.parkanywhereonline.models.Location;
import com.parkanywhereonline.models.Spot;
import com.parkanywhereonline.models.firestore.UserCollection;

public class AddSpotActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText addressInput;
    private EditText priceInput;

    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add new spot"); //ayy
        setContentView(R.layout.add_spot_activity);

        // Set textinput vars
        nameInput = findViewById(R.id.spotName);
        addressInput = findViewById(R.id.spotAddress);
        priceInput = findViewById(R.id.spotPrice);
    }

    public void confirmSpot(View view) {
        Log.d("marker", marker.getPosition().latitude + ", " + marker.getPosition().longitude);
        UserCollection userCollection = new UserCollection();

        // Get variables
        String getName = nameInput.getText().toString();
        String getAddress = addressInput.getText().toString();
        String getPrice = priceInput.getText().toString();
        Location location = new Location(marker.getPosition().latitude, marker.getPosition().longitude);

        // Generate spot object
        Spot spot = new Spot(getName, getAddress, true, location, getPrice);
        // Add spot to user by ID
        userCollection.addSpotToUserByID(spot);
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}