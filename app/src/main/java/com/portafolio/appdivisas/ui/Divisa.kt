package com.portafolio.appdivisas.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.portafolio.appdivisas.R
import com.portafolio.appdivisas.controladores.peticiones

//import com.example.linealgraph_holograph_kotlin.databinding.ActivityMainBinding
import android.graphics.Color
import android.widget.Toast
import com.echo.holographlibrary.Line
import com.echo.holographlibrary.LineGraph
import com.echo.holographlibrary.LinePoint
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.portafolio.appdivisas.controladores.config
import com.portafolio.appdivisas.utiles.Log

class divisa : AppCompatActivity() {

    var Sizes=20
    val TAG="divisas"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divisa)

        val bundle = intent.extras
        val dato= bundle?.getString("text")
        val arr = dato?.split(':')?.toTypedArray()

      val valormoneda= arr?.get(1)

        if (valormoneda != null) {
            peticiones.getAllDate(this, valormoneda)
        };

        /*----------------------------------------------------------------------------------------*/
        Toast.makeText(
            this@divisa,
            "Cargando Historial de tasas ${valormoneda} ",
            Toast.LENGTH_LONG
        ).show()
        //
        /*----------------------------------------------------------------------------------------*/
       val moneda= findViewById<TextView>(R.id.divisa)
        moneda.setText(dato)
        /*----------------------------------------------------------------------------------------*/



    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Your logic here
        config.JSON.DeleteFileJSON(
            "tasas.json",
            "com.portafolio.appdivisas",
        )

        config.JSON.DeleteFileJSON(
            "tazas.json",
            "com.portafolio.appdivisas",
        )

    }

    }

