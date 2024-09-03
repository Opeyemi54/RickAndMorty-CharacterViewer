package com.example.rickmorty.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetRick {
    @GET("character")
    suspend fun getAllCharacter():Rick

    @GET("character")
    suspend fun getCharacterById(@Query("id") id: Int):
            Characters


}