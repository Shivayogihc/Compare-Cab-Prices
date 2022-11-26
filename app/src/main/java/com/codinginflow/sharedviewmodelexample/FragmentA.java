package com.codinginflow.sharedviewmodelexample;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class FragmentA extends Fragment  {
    private SharedViewModel viewModel;
    private  SharedViewModel_t viewModel_t;
    private EditText editText;
    private EditText editText_1;

    View v;

    AutocompleteSupportFragment autocompleteFragment;

    AutocompleteSupportFragment autocompleteFragment_1;

    public GoogleMap mMap;
    public FusedLocationProviderClient mFusedLocationProviderClient;
    public PlacesClient placesClient;

    private View mapView;



    private final float DEFAULT_ZOOM = 18;
    private Location mLastKnownLocation;

    private LocationCallback locationCallback;

    int hjk;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_a, container, false);

        if(!Places.isInitialized()) {
            Places.initialize(getActivity().getApplicationContext(), "AIzaSyDcAiU2sN1zCvNnJ3C5JcfKJa0eAhiTokE");
           // getDeviceLocation();
        }

        placesClient = Places.createClient(getContext());


        /*
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.mapView);

         */
       // mapFragment.getMapAsync(this);
       // mapFragment.getMapAsync(this);


       // mapView = mapFragment.getView();


        editText = v.findViewById(R.id.edit_text);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                placesClient = Places.createClient(getActivity().getApplicationContext());
                final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

                 */


                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                        ,Place.Field.LAT_LNG,Place.Field.NAME);


                //CREATE INTENT

                getFragmentManager().beginTransaction();
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList).build(getActivity());
                hjk = 10;
                startActivityForResult(intent,100);







            }
        });


        editText_1 = v.findViewById(R.id.edit_text_1);
        editText_1.setFocusable(false);
        editText_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*

                placesClient = Places.createClient(getActivity().getApplicationContext());
                final AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

                 */


                List<Place.Field> fieldList_t = Arrays.asList(Place.Field.ADDRESS
                        ,Place.Field.LAT_LNG,Place.Field.NAME);


                //CREATE INTENT

                getFragmentManager().beginTransaction();
                Intent intent_t = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList_t).build(getActivity());
                hjk = 20;
                startActivityForResult(intent_t,100);






            }
        });



        Button button = v.findViewById(R.id.button_ok);


//AIzaSyB3UbYL11BSVwOvbmhOtPh9NeZNIdf2Lt8


        //Places.initialize((Context) v.getApplicationWindowToken(),"AIzaSyB3UbYL11BSVwOvbmhOtPh9NeZNIdf2Lt8");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setText(editText.getText());
               // viewModel_t.setText_u(editText_1.getText());

                getFragmentManager().beginTransaction().replace(R.id.container_a,new HomeFragment()).commit();

            }
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success
            //Initialize place


            //set address on editext
            if (hjk == 10){
                Place place = Autocomplete.getPlaceFromIntent(data);
                editText.setText(place.getAddress());
            }else {
                Place place_1 = Autocomplete.getPlaceFromIntent(data);
                editText_1.setText(place_1.getAddress());
            }



        }else if (resultCode == AutocompleteActivity.RESULT_ERROR){
            //when error
            //initialize sttus
            Status status = Autocomplete.getStatusFromIntent(data);
            //display toast
            Toast.makeText(getActivity().getApplicationContext(),status.getStatusMessage()
                    ,Toast.LENGTH_SHORT).show();
        }else  if (requestCode == 51) {
            if (resultCode == RESULT_OK) {
               //getDeviceLocation();
            }
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                editText.setText(charSequence);

               // editText_1.setText();
            }
        });
        /*

        viewModel_t = ViewModelProviders.of(getActivity()).get(SharedViewModel_t.class);
        viewModel_t.getText_u().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence_t) {

                editText_1.setText(charSequence_t);
                // editText_1.setText();
            }
        });

         */



    }


}