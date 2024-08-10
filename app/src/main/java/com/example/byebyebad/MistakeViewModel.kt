package com.example.byebyebad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MistakeViewModel(private val database : MistakeDatabase) : ViewModel() {

    //the use of viewmodel is to keep the data when the configuration changes like screen rotation or when the app is in the background
    //the viewmodel will keep the data in the memory and will not be destroyed
    /**
     * the use of private val database : MistakeDatabase is to get the database from the MistakeDatabase class and use it in the viewmodel
     */
    private val _mistakes = MutableLiveData<List<Mistake>>()
    val mistake : LiveData<List<Mistake>> get() = _mistakes

    init{
        loadMistakes()
    }

    private fun loadMistakes(){
        viewModelScope.launch {
            _mistakes.value = database.mistakeDao().getAllMistakes()
        }
    }

    fun addMistake(mistake : Mistake) {
        viewModelScope.launch {
            database.mistakeDao().insert(mistake)
            loadMistakes()
        }
    }
}