package com.portafolio.appdivisas.interfaz


import com.example.exampleapikotlin.interfaz.Comments
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface date {
    @GET("2020-01-01..")
    fun getPostById( @Query("to") postId: String): Call<JsonObject>
}