package com.codinginflow.sharedviewmodelexample;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;


public class TestingFragment extends Fragment {


    View v;
    EditText editText;


    public TestingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_testing, container, false);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editText = v.findViewById(R.id.edit_text_1);

        Places.initialize(getActivity().getApplicationContext(),"AIzaSyARNTDKRXA6AgEkzoALgliVSLDyQjdFA6Q");

       // PlacesClient placesClient = Places.createClient(getContext());

        editText.setFocusable(false);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS
                        ,Place.Field.LAT_LNG,Place.Field.NAME);

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList).build(getActivity().getApplicationContext());


                startActivityForResult(intent,100);
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //when success
            //Initialize place
            Place place = Autocomplete.getPlaceFromIntent(data);
            //set address on editext
            editText.setText(place.getAddress());

        }else if (resultCode == AutocompleteActivity.RESULT_ERROR){
            //when error
            //initialize sttus
            Status status = Autocomplete.getStatusFromIntent(data);
            //display toast
            Toast.makeText((Context) v.getApplicationWindowToken(),status.getStatusMessage()
                    ,Toast.LENGTH_SHORT).show();
        }
    }

}