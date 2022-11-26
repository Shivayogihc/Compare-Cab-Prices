package com.codinginflow.sharedviewmodelexample;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    AutocompleteSupportFragment autocompleteFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Initialize the sdk
        if(!Places.isInitialized()){
            Places.initialize(getApplicationContext(),"AIzaSyDcAiU2sN1zCvNnJ3C5JcfKJa0eAhiTokE");
        }


        //Create a new places client instance
        PlacesClient placesClient = Places.createClient(this);

       autocompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.autocomplete_fragment);


        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);

        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754,151.229596)));
        autocompleteFragment.setCountries("IN");

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG, "An error occurred:"+status);
            }

            @Override
            public void onPlaceSelected(@NonNull Place place) {

                Log.i(TAG,"Place:"+place.getName()+", "+place.getId());
            }
        });

    }



}