package com.example.githubapiapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubapiapp.Data.local.datastore.SettingPreference
import kotlinx.coroutines.launch

class SettingViewModel(private val preference: SettingPreference): ViewModel() {

    fun getThemeSet(): LiveData<Boolean>{
        return preference.getThemeSetting().asLiveData()
    }

    fun saveThemeSet(isDarkModelActive: Boolean){
        viewModelScope.launch {
            preference.saveThemeSetting(isDarkModelActive)
        }
    }

}