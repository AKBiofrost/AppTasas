package com.portafolio.appdivisas.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.portafolio.appdivisas.R
import com.portafolio.appdivisas.controladores.peticiones

class divisa : AppCompatActivity() {

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

       val moneda= findViewById<TextView>(R.id.divisa)
        moneda.setText(dato)

       // peticiones.reading(this)
       //
    }
}