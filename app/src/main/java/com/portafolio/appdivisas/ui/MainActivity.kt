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
import com.portafolio.appdivisas.controladores.config
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
            if (config.calibrar.isOnline(this)) {
                peticiones.getAllComments(this)
            } else {
                config.Dialog.AlertDialog_error("Sin red", "No tiene conexion de internet", this)
            }


        }
    }

    override fun onStart() {
        super.onStart()


    }


    override fun onResume() {
        super.onResume()

    }


    /*
     *
     * */
}