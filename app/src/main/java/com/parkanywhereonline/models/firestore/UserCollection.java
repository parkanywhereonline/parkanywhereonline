package com.parkanywhereonline.models.firestore;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.parkanywhereonline.models.Spot;

import java.util.HashMap;
import java.util.Map;

public class UserCollection {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Returns the spot collection
    public CollectionReference getUserCollection() {
        return db.collection("users");
    }

    public void addSpotToUserByID(Spot spot) {
        // Retrieve the current user's UID
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID = currentFirebaseUser.getUid();

        // Add spot to database
        SpotsCollection spotsCollection = new SpotsCollection();
        Task spotsCollectionTask = spotsCollection.addSpot(spot);

        // Then add the reference to the user
        spotsCollectionTask.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // Create a spothashmapref to store the reference object to the spot stored in the db
                Map<String, Object> spotHashMapRef = new HashMap<>();

                // Put the reference in with spotRef as the field name
                spotHashMapRef.put("spotRef", documentReference);

                // set spotHashMapRef
                getUserCollection().document(UID).set(spotHashMapRef);
            }
        });

    }

//    public CollectionReference getSpotsByUserID() {
//
//    }
}

