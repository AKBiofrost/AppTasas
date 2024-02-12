package com.portafolio.appdivisas.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portafolio.appdivisas.model.Dinero
import com.example.exampleapikotlin.interfaz.APIService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.JsonObject
import com.portafolio.appdivisas.R
import com.portafolio.appdivisas.controladores.peticiones
import com.portafolio.appdivisas.interfaz.date
import com.portafolio.appdivisas.utiles.manipularJSON
import com.portafolio.test_api_kotlin.adapter.CustomAdapter
import com.portafolio.test_api_kotlin.adapter.ItemsViewModel
import com.portafolio.test_api_kotlin.adapter.presentacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    /*
    *
    *
    *
    * */
    private val TAG = "AppFinanciera"

    //private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var BASE_URL = "https://api.frankfurter.app"
    private var valor = "dinero"
    private var moneda = "USD"

    /*
    *
    *
    *
    * */

    val arrayList = ArrayList<presentacion>()//Creating an empty arraylist
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        //lineGrafica
        button.setOnClickListener {
            peticiones.getAllComments(this)

        }
    }

    override fun onStart() {
        super.onStart()



    }


    override  fun onResume() {
        super.onResume()

    }


    private fun getAllComments() {


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
                    /*
                     for (i in response.body()?.indices!!) {
                         Log.i(TAG, "-$i " + response.body()?.get(i)?.title)

                     }
                    */

                }

            }

            override fun onFailure(call: Call<Dinero>, t: Throwable) {

            }
        })
    }


    private   fun getAllDate(){

        //BASE_URL="https://jsonplaceholder.typicode.com"
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(date::class.java)
        //coroutineScope {
           // launch {
                api.getPostById("USD").enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful) {
                            val resCode = response.code()
                            Log.e(TAG, "code: $resCode")
                            Log.e(TAG, "response: ${response}")
                            Log.e(TAG, "response: ${response.body()?.get("rates")}")
                            val jsonM= manipularJSON()
                            val j= JsonObject()

                             j.add("rates",response.body()?.get("rates"))
                            jsonM.JSONSave("rates.json", j,
                                "com.portafolio.appdivisas",this@MainActivity )
                            Log.e(TAG, "-------------------------------------------------------")
                            /*
                     for (i in response.body()?.indices!!) {
                         Log.i(TAG, "-$i " + response.body()?.get(i)?.title)

                     }
                    */

                        }

                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    }


                })
            //}
       // }
    }
    private fun init() {

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_activity_main)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

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
        val adapter = CustomAdapter(data, this, recyclerview)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


    }

    fun voidOnClick(context: Context ){



    }


    /*
     *
     * */
}