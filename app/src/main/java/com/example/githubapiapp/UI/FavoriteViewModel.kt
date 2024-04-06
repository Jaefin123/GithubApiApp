package com.example.githubapiapp.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubapiapp.Data.local.database.Favorite
import com.example.githubapiapp.Data.local.database.FavoriteRoomDatabase

class FavoriteViewModel(private val data: FavoriteRoomDatabase): ViewModel() {

    fun getFavorite(): LiveData<List<Favorite>>{
        var fav = data.favDao().getAllFavorite()
        return fav
    }

}