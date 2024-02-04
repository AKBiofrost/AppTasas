package com.example.example

import com.google.gson.annotations.SerializedName


data class ListFechas (

  @SerializedName("fechas" ) var fechas : ArrayList<String> = arrayListOf()

)