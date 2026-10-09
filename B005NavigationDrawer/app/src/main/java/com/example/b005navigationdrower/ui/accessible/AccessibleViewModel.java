package com.example.b005navigationdrower.ui.accessible;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccessibleViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AccessibleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accessible fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
