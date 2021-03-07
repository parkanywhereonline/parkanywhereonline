package com.parkanywhereonline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.Marker;
import com.google.android.material.textfield.TextInputEditText;

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
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}