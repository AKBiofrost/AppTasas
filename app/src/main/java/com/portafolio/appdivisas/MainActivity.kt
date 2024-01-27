package com.portafolio.appdivisas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Dinero
import com.example.exampleapikotlin.interfaz.APIService
import com.portafolio.appdivisas.adapter.ListElement
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
    private val BASE_URL = "https://api.frankfurter.app"
    private var valor = "dinero"
    private var moneda = "USD"
    public val recyclerView: RecyclerView = TODO()

    /*
    *
    *
    *
    * */
    val arrayList = ArrayList<presentacion>()//Creating an empty arraylist
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.findViewById<RecyclerView>(R.id.recycler_activity_main)
        setContentView(R.layout.activity_main)
        getAllComments()

    }

    override fun onStart() {
        super.onStart()



    }
   public fun  getRecyclerView()
    : RecyclerView {
        return recyclerView
    }

    override fun onResume() {
        super.onResume()

        init()
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
        val adapter = CustomAdapter(data, this)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter


    }

    fun voidOnClick(context: Context ){



    }


    /*
     *
     * */
}