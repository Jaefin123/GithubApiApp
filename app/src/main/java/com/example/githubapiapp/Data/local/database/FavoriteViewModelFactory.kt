package com.example.githubapiapp.Data.local.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiapp.Data.local.datastore.SettingPreference
import com.example.githubapiapp.UI.FavoriteViewModel

class FavoriteViewModelFactory(private val database: FavoriteRoomDatabase): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return FavoriteViewModel(database) as T
        }

        throw IllegalArgumentException("Unknown View Model Class: "+ modelClass.name)
    }

}