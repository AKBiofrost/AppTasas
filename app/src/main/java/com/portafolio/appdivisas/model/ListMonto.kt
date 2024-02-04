package com.example.example

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


data class ListMonto (

  @SerializedName("monto" ) var monto : ArrayList<JsonObject> = arrayListOf()

)