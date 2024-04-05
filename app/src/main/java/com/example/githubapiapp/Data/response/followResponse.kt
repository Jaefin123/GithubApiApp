package com.example.githubapiapp.Data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class  Follow (

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null

)