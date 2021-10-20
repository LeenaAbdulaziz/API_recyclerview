package com.example.api_recyclerview


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET


interface APIInterface {
    @GET("/people/")
    fun Getname(): Call<ArrayList<ActivityDetails>>?
}

class ActivityDetails(var name: String?)



