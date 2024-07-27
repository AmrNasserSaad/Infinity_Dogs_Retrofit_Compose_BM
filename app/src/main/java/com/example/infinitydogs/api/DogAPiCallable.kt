package com.example.infinitydogs.api

import retrofit2.Call
import retrofit2.http.GET

interface DogAPiCallable {
    @GET("/api/breeds/image/random")
    fun getDogImage(): Call<Dog>
}