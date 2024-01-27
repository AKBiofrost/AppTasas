package com.example.example

import com.google.gson.annotations.SerializedName


data class Rates (

  @SerializedName("AUD" ) var AUD : Double? = null,
  @SerializedName("BGN" ) var BGN : Double? = null,
  @SerializedName("BRL" ) var BRL : Double? = null,
  @SerializedName("CAD" ) var CAD : Double? = null,
  @SerializedName("CHF" ) var CHF : Double? = null,
  @SerializedName("CNY" ) var CNY : Double? = null,
  @SerializedName("CZK" ) var CZK : Double? = null,
  @SerializedName("DKK" ) var DKK : Double? = null,
  @SerializedName("GBP" ) var GBP : Double? = null,
  @SerializedName("HKD" ) var HKD : Double? = null,
  @SerializedName("HUF" ) var HUF : Double? = null,
  @SerializedName("IDR" ) var IDR : Int?    = null,
  @SerializedName("ILS" ) var ILS : Double? = null,
  @SerializedName("INR" ) var INR : Double? = null,
  @SerializedName("ISK" ) var ISK : Double? = null,
  @SerializedName("JPY" ) var JPY : Double? = null,
  @SerializedName("KRW" ) var KRW : Double? = null,
  @SerializedName("MXN" ) var MXN : Double? = null,
  @SerializedName("MYR" ) var MYR : Double? = null,
  @SerializedName("NOK" ) var NOK : Double? = null,
  @SerializedName("NZD" ) var NZD : Double? = null,
  @SerializedName("PHP" ) var PHP : Double? = null,
  @SerializedName("PLN" ) var PLN : Double? = null,
  @SerializedName("RON" ) var RON : Double? = null,
  @SerializedName("SEK" ) var SEK : Double? = null,
  @SerializedName("SGD" ) var SGD : Double? = null,
  @SerializedName("THB" ) var THB : Double? = null,
  @SerializedName("TRY" ) var TRY : Double? = null,
  @SerializedName("USD" ) var USD : Double? = null,
  @SerializedName("ZAR" ) var ZAR : Double? = null

)