package com.portafolio.appdivisas.controladores

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapikotlin.interfaz.APIService
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.portafolio.appdivisas.R
import com.portafolio.appdivisas.interfaz.date
import com.portafolio.appdivisas.model.Dinero
import com.portafolio.test_api_kotlin.adapter.CustomAdapter
import com.portafolio.test_api_kotlin.adapter.ItemsViewModel
import com.portafolio.test_api_kotlin.adapter.presentacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class peticiones {

    private val TAG = "peticiones"
    val arrayList = ArrayList<presentacion>()//Creating an empty arraylist
    public   fun getAllDate(context: Context, moneda: String){

        //BASE_URL="https://jsonplaceholder.typicode.com"
        val api = Retrofit.Builder()
            .baseUrl(config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(date::class.java)
        //coroutineScope {
        // launch {
        api.getPostById(moneda).enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    val resCode = response.code()
                    Log.e(TAG, "code: $resCode")
                    Log.e(TAG, "response: ${response}")
                    Log.e(TAG, "response: ${response.body()?.get("rates")}")
                    val j= JsonObject()

                    j.add("rates",response.body()?.get("rates"))
                    config.JSON.JSONSave("rates.json", j,
                        "com.portafolio.appdivisas",context )
                    Log.e(TAG, "-------------------------------------------------------")

                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

            }


        })

    }

    public fun getAllComments(context: Context)  {
         var valor = "dinero"
        val api = Retrofit.Builder()
            .baseUrl(config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        api.getComments().enqueue(object : Callback<Dinero> {
            override fun onResponse(
                call: Call<Dinero>,
                response: Response<Dinero>
            ) {
                if (response.isSuccessful) {
                    val resCode = response.code()
                    Log.e(TAG, "code: $resCode")
                    Log.e(TAG, "response: $response")
                    val i = 0
                    Log.e(TAG, "-------------------------------------------------------")
                    Log.i(TAG, "-Monto: " + response.body()?.amount)
                    Log.i(TAG, "-Fecha: " + response.body()?.date)
                    Log.i(TAG, "-Base: " + response.body()?.base)
                    Log.i(TAG, "-TASA USD: " + response.body()?.rates?.USD)


                    valor = response.body()?.rates?.USD.toString()

                    arrayList.add(presentacion(response.body()?.rates?.BGN.toString(), "BGN"))
                    arrayList.add(presentacion(response.body()?.rates?.USD.toString(), "USD"))
                    arrayList.add(presentacion(response.body()?.rates?.BRL.toString(), "BRL"))
                    arrayList.add(presentacion(response.body()?.rates?.JPY.toString(), "JPY"))
                    arrayList.add(presentacion(response.body()?.rates?.CAD.toString(), "CAD"))
                    arrayList.add(presentacion(response.body()?.rates?.MXN.toString(), "MXN"))
                    Log.e(TAG, "-------------------------------------------------------")

                    init(arrayList,context)
                }

            }

            override fun onFailure(call: Call<Dinero>, t: Throwable) {

            }
        })
    }

    public fun init(arrayList:ArrayList<presentacion>, context:Context) {

        // getting the recyclerview by its id
        val recyclerview = (context as Activity).findViewById<RecyclerView>(R.id.recycler_activity_main)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager( context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in arrayList) {
            Log.e(TAG, "init: " + i.moneda)
            data.add(
                ItemsViewModel(
                    R.drawable.baseline_monetization_on_24,
                    i.moneda + ":" + i.valor
                )
            )
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data, context, recyclerview)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


    }
    companion object {
        val TAG = "peticiones"
        val arrayList = ArrayList<presentacion>()//Creating an empty arraylist
        val json= JsonObject()
        val Listfechas= JsonArray()
        val fechas= JsonObject()
        val Listmonto= JsonArray()
        val monto= JsonObject()
        val ListMoney_date= JsonArray()
        val Money_date= JsonObject()
        val ListComplete= JsonObject()

        public   fun getAllDate(context: Context, moneda: String){

            //BASE_URL="https://jsonplaceholder.typicode.com"
            val api = Retrofit.Builder()
                .baseUrl(config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(date::class.java)
            //coroutineScope {
            // launch {
            api.getPostById(moneda).enqueue(object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    if (response.isSuccessful) {
                        val resCode = response.code()
                        Log.e(TAG, "code: $resCode")
                        Log.e(TAG, "response: ${response}")
                        Log.e(TAG, "response: ${response.body()?.get("rates")}")

                        json.add("rates",response.body()?.get("rates"))

                        Log.e(TAG, "-------------------------------------------------------")

                    }

                    Log.i(TAG, "Reading: $json")
                    val gson = Gson()

                    var ratesObject = json.getAsJsonObject("rates")

                    ratesObject.entrySet().forEach{
                            (fecha, monedaObject) ->

                        Listfechas.add(fecha)
                        Listmonto.add(monedaObject)
                        Log.i(TAG, "monedaObject: $fecha")
                        Log.e(TAG, "monedaObject: "+monedaObject)

                        monto.add("monto",Listmonto )
                    }
                    monto.add("monto",Listmonto )
                    fechas.add("fechas",Listfechas )
                    Log.i(TAG, "Reading monto: $monto")


                    /*---------------------------------------------------------------------------*/
                    Log.i(TAG, "monto: "+ monto)
                    /*---------------------------------------------------------------------------*/


                    config.JSON.JSONSave("fechas.json", fechas,
                        "com.portafolio.appdivisas",context )

                    config.JSON.JSONSave("monto.json", monto,
                        "com.portafolio.appdivisas",context )


                    val montoObject = config.JSON.ReadingJsonListMonto("monto.json","com.portafolio.appdivisas", context)
                    val fechasObject = config.JSON.ReadingJsonListFecha("fechas.json","com.portafolio.appdivisas", context)
                    Log.i(TAG, "montoObject.monto: "+ montoObject.monto)
                    //val montoObjectMonte = montoObject.size()
                    Log.i(TAG, "montoObject.monto: "+ montoObject.monto.get(0).keySet().toString())
                    val keyMoney=montoObject.monto.get(0).keySet().toString()

                    keyMoney.replace(']',' ')
                    keyMoney.replace('[',' ')

                    Log.e(TAG, "montoObject.monto: "+ keyMoney.substring(1))
                    val keyMoney1=keyMoney.substring(1)
                    Log.e(TAG, "montoObject.monto: "+ keyMoney.substring(0, keyMoney.length-1))
                    val keyMoney2=keyMoney1.substring(0, keyMoney.length-2)

                    Log.i(TAG, "montoObject.monto: "+ keyMoney2)
                    val size=montoObject.monto.withIndex()




              for((index, i) in size ){

                  ListComplete.addProperty("tasa",montoObject.monto.get(index).get(keyMoney2).toString() )
                  ListComplete.addProperty("fecha",fechasObject.fechas.get(index))
                  ListMoney_date.add(ListComplete)
                  Log.i(TAG, "montoObject.monto: "+ montoObject.monto.get(index).get(keyMoney2))
                  Log.i(TAG, "montoObject.fecha: "+ fechasObject.fechas.get(index))
                 // Log.i(TAG, "montoObject.monto: "+ i.get(keyMoney2))
                  Money_date.add("tasa",ListMoney_date )
              }
                    Log.i(TAG, "Reading Money_date: $Money_date")
                    config.JSON.JSONSave("tasas.json",Money_date, "com.portafolio.appdivisas",context )
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                }


            })

        }
        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }
        public fun getAllComments(context: Context)  {
            var valor = "dinero"
            val api = Retrofit.Builder()
                .baseUrl(config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

            api.getComments().enqueue(object : Callback<Dinero> {
                override fun onResponse(
                    call: Call<Dinero>,
                    response: Response<Dinero>
                ) {
                    if (response.isSuccessful) {
                        val resCode = response.code()
                        Log.e(TAG, "code: $resCode")
                        Log.e(TAG, "response: $response")
                        val i = 0
                        Log.e(TAG, "-------------------------------------------------------")
                        Log.i(TAG, "-Monto: " + response.body()?.amount)
                        Log.i(TAG, "-Fecha: " + response.body()?.date)
                        Log.i(TAG, "-Base: " + response.body()?.base)
                        Log.i(TAG, "-TASA USD: " + response.body()?.rates?.USD)


                        valor = response.body()?.rates?.USD.toString()

                        arrayList.add(presentacion(response.body()?.rates?.BGN.toString(), "BGN"))
                        arrayList.add(presentacion(response.body()?.rates?.USD.toString(), "USD"))
                        arrayList.add(presentacion(response.body()?.rates?.BRL.toString(), "BRL"))
                        arrayList.add(presentacion(response.body()?.rates?.JPY.toString(), "JPY"))
                        arrayList.add(presentacion(response.body()?.rates?.CAD.toString(), "CAD"))
                        arrayList.add(presentacion(response.body()?.rates?.MXN.toString(), "MXN"))
                        Log.e(TAG, "-------------------------------------------------------")

                        init(arrayList,context)
                    }

                }

                override fun onFailure(call: Call<Dinero>, t: Throwable) {

                }
            })
        }

        public fun init(arrayList:ArrayList<presentacion>, context:Context) {

            // getting the recyclerview by its id
            val recyclerview = (context as Activity).findViewById<RecyclerView>(R.id.recycler_activity_main)

            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager( context)

            // ArrayList of class ItemsViewModel
            val data = ArrayList<ItemsViewModel>()

            // This loop will create 20 Views containing
            // the image with the count of view
            for (i in arrayList) {
                Log.e(TAG, "init: " + i.moneda)
                data.add(
                    ItemsViewModel(
                        R.drawable.baseline_monetization_on_24,
                        i.moneda + ":" + i.valor
                    )
                )
            }

            // This will pass the ArrayList to our Adapter
            val adapter = CustomAdapter(data, context, recyclerview)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter


        }

        fun reading(context:Context){

            var rate = JsonObject()
            rate = config.JSON.ReadingJsonBasico("", context.packageName, context)

            val gson = Gson()

            val ratesObject = rate.getAsJsonObject("rates")
            val ratesArray = JsonArray()

            Log.i(TAG, "Reading: $ratesObject")
            Log.i(TAG, "Reading: $ratesObject")

            ratesObject.entrySet().forEach {
                (fecha, monedaObject) ->
               // val moneda = monedaObject.keySet().
                //val cantidad = monedaObject[moneda].asDouble





/*
                if (moneda != null) {
                    val rateObject = JsonObject()
                    rateObject.addProperty("fecha", fecha)
                    rateObject.addProperty("moneda", moneda)
                    rateObject.addProperty("cantidad", cantidad)
                    ratesArray.add(rateObject)
                }
*/


            }

        }
    }

}



