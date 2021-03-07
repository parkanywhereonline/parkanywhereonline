package com.parkanywhereonline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.parkanywhereonline.models.Spot;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSpotActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
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

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void confirmSpot(View view) {
        double lat = marker.getPosition().latitude;
        double lng = marker.getPosition().longitude;
        Log.d("marker", lat + ", " + lng);

        // Get variables
        String getName = nameInput.getText().toString();
        String getAddress = addressInput.getText().toString();
        String getPrice = priceInput.getText().toString();
        GeoPoint location = new GeoPoint(lat, lng);

        // Generate spot object
        Spot spot = new Spot(getName, getAddress, true, location, getPrice);
        // Add to database
        Task<DocumentReference> addTask = db.collection("spots").add(spot);

        addTask.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                String uid = auth.getCurrentUser().getUid();
                String path = documentReference.getPath();
                db.collection("users").document(uid)
                        .update("spots", FieldValue.arrayUnion(path))
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Document does not exist yet
                                db.collection("users").document(uid)
                                        .set(new HashMap<String, Object>() {{
                                            put("spots", new ArrayList<String>() {{
                                                add(path);
                                            }});
                                        }});
                            }
                        });
            }
        });

        finish();
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}