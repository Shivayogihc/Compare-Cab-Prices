package com.codinginflow.sharedviewmodelexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel_t extends ViewModel {
    private MutableLiveData<CharSequence> text = new MutableLiveData<>();


    public void setText_u(CharSequence input) {
        text.setValue(input);
    }



    public LiveData<CharSequence> getText_u() {
        return text;
    }
}