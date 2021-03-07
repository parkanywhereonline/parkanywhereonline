package com.parkanywhereonline.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.GeoPoint;
import com.parkanywhereonline.EditParkingSpotActivity;
import com.parkanywhereonline.MainActivity;
import com.parkanywhereonline.R;
import com.parkanywhereonline.models.Location;
import com.parkanywhereonline.models.Spot;
import com.parkanywhereonline.models.firestore.SpotsCollection;
import com.parkanywhereonline.ui.main.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class SpotFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpotFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SpotFragment newInstance(int columnCount) {
        SpotFragment fragment = new SpotFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        List spotList = new ArrayList<Spot>();
        spotList.add(new Spot("Trap house", "420 fox ave",true, null, "$0.00"));

        // Test spot read
        SpotsCollection spotsCollection = new SpotsCollection();
        spotsCollection.getSpotCollection().document("LeLDc4JzkxZkLm50bFpI").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
//                        Log.d("Name", document.getString("name") + ", " + document.getString("address"));
                        String nameOfSpot = document.getString("name");
                        GeoPoint geoPoint = document.getGeoPoint("location");
                    }
                }
            }
        });


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MySpotRecyclerViewAdapter(spotList));
        }
        return view;
    }

//    public void editSpotByID(View view) {
//        Intent intent = new Intent(getActivity(), EditParkingSpotActivity.class);
//        startActivity(intent);
//    }
}