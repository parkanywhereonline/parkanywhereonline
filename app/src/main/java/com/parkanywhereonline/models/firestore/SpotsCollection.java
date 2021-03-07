package com.parkanywhereonline.models.firestore;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parkanywhereonline.models.Spot;

import java.util.Collection;

public class SpotsCollection {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Returns the spot collection
    public CollectionReference getSpotCollection() {
        return db.collection("spots");
    }

    // Add a spot and return the task for asynchronousity
    public Task addSpot(Spot spot) {
        return getSpotCollection().add(spot.getSpotAsHashMap());
    }
}
