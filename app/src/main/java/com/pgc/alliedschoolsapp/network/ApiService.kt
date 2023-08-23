package com.example.examplecleanarch.network

import com.google.gson.JsonElement
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @GET("movie/popular?api_key=bbc03285c380502bc584412420117d4f")
    fun getApiMovies(
    ): Call<JsonElement?>


    @Headers("Content-Type: application/json")
    @POST
    fun postCall(@Url url: String?, @Body body: String?): Call<Any?>?

    @Headers("Content-Type: application/json")
    @POST
    fun postCall(

        @Url url: String,
        @Body params: RequestBody
    ): Call<JsonElement?>

    @Headers("Content-Type: application/json")
    @POST
    fun postWithTokenCall(
        @Header("Authorization") token: String,
        @Url url: String,
        @Body params: RequestBody
    ): Call<JsonElement?>

    @GET
    fun getCall(
        @Url url: String,
        @Header("Authorization") token: String
    ): Call<JsonElement?>

    @GET
    fun getCallWithoutToken(
        @Url url: String,
    ): Call<JsonElement?>

}