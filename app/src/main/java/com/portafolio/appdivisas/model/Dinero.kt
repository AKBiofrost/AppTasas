package com.portafolio.appdivisas.model

import com.google.gson.annotations.SerializedName


data class Dinero (

  @SerializedName("amount" ) var amount : Int?    = null,
  @SerializedName("base"   ) var base   : String? = null,
  @SerializedName("date"   ) var date   : String? = null,
  @SerializedName("rates"  ) var rates  : Rates?  = Rates()

)