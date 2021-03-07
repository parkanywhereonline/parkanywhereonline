package com.parkanywhereonline.models.firestore;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;

public class SpotsCollection {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Returns the spot collection
    public CollectionReference getSpotCollection() {
        return db.collection("spots");
    }
}
