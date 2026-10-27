package com.example.b005navigationdrower.ui.accesibility;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AccessibilityViewModel {

    private final MutableLiveData<String> mText;

    public AccessibilityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accessible fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
