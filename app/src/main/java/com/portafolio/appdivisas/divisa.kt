package com.portafolio.appdivisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class divisa : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_divisa)

        val bundle = intent.extras
        val dato= bundle?.getString("text")

       val moneda= findViewById<TextView>(R.id.divisa)
        moneda.setText(dato)

    }
}