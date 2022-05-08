package com.example.mediatorlivedatatest.ui.main

import android.util.Log
import androidx.lifecycle.*

class MainViewModel : ViewModel() {

    val editText1 = MutableLiveData<String>()
    val editText2 = MutableLiveData<String>()
    val text1 = MediatorLiveData<String>().apply {
        fun updateText() {
            postValue("${editText1.value.orEmpty()} : ${editText2.value.orEmpty()}")
            Log.w("TEST", "text1 updated, it seems to be working correctly.")
        }
        addSource(editText1) {
            updateText()
        }
        addSource(editText2) {
            updateText()
        }
    }

    // Live data for "re-using layout with <include/>"
    val editText3 = MutableLiveData<String>()
    val editText4 = MutableLiveData<String>()
    val text2 = MediatorLiveData<String>().apply {
        fun updateText() {
            postValue("${editText3.value.orEmpty()} : ${editText4.value.orEmpty()}")
            Log.w("TEST", "text2 updated, it seems to be an infinite loop.")
        }
        addSource(editText3) {
            // Loop onChange callback.
            updateText()
        }
        addSource(editText4) {
            // Loop onChange callback.
            updateText()
        }
    }
}