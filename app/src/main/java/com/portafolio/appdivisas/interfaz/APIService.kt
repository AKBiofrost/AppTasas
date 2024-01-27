package com.example.exampleapikotlin.interfaz

import com.example.example.Dinero
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

   // @GET("todos")
   // @GET("photos")
    @GET("latest")
    fun getComments(): Call<Dinero>
}