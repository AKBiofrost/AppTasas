package com.example.example

import com.google.gson.annotations.SerializedName


data class Tasa (

  @SerializedName("tasa"  ) var tasa  : String? = null,
  @SerializedName("fecha" ) var fecha : String? = null

)