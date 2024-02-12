package com.example.example

import com.google.gson.annotations.SerializedName


data class Datagrafico (

  @SerializedName("tasa" ) var tasa : ArrayList<Tasa> = arrayListOf()

)