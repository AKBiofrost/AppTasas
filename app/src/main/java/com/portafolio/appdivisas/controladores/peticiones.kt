package com.portafolio.appdivisas.controladores

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapikotlin.interfaz.APIService
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
    }

}

