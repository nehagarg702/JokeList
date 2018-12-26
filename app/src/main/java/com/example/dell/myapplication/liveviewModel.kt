/*
LiveData model
 */
package com.example.dell.myapplication
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel



class liveviewModel() : ViewModel()
{
    var currentName: MutableLiveData<List<Jokes>> = MutableLiveData<List<Jokes>>()

    fun getFavs(): MutableLiveData<List<Jokes>> {
        if (currentName == null) {
            currentName = MutableLiveData<List<Jokes>>()
            currentName.setValue(JokesList().loadUserNames())
        }
        return currentName
    }


}
