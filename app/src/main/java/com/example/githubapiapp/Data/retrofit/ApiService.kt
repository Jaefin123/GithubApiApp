package com.example.githubapiapp.Data.retrofit

import com.example.githubapiapp.Data.response.DataDetailResponse
import com.example.githubapiapp.Data.response.DatagithubResponse
import com.example.githubapiapp.Data.response.Follow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(@Query("q") queryuser: String): Call<DatagithubResponse>
}

interface DetailService{
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DataDetailResponse>
}

interface followerService{
    @GET("users/{user}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("user") user: String
    ): Call<List<Follow>>

}

interface followingService {
    @GET("users/{user}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("user") user: String
    ): Call<List<Follow>>
}