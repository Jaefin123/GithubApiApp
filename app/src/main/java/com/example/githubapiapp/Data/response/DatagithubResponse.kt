package com.example.githubapiapp.Data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class DatagithubResponse (
    @field:SerializedName("items")
    val items: ArrayList<User>
)
data class User(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(followingUrl)
        parcel.writeString(followersUrl)
        parcel.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}