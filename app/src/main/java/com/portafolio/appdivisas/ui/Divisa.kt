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

class divisa : AppCompatActivity() {

    var Sizes=20

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
            "Linea: , Punto: ",
            Toast.LENGTH_LONG
        ).show()
        //
        /*----------------------------------------------------------------------------------------*/
       val moneda= findViewById<TextView>(R.id.divisa)
        moneda.setText(dato)
        /*----------------------------------------------------------------------------------------*/
       val graficosDatos= config.JSON.ReadingJsonDatosGraficos("tasas.json","com.portafolio.appdivisas", this)
        /*----------------------------------------------------------------------------------------*/

        Sizes=graficosDatos.tasa.size
        var linea = Line()

       for((index,valor) in graficosDatos.tasa.withIndex()){
           linea = datosGrafica(linea, index.toString(),graficosDatos.tasa.get(index).tasa.toString())

       }

      //  linea.color = Color.parseColor("FFBB33")

        graficar(linea)
    }





    fun datosGrafica(linea: Line, ejeX: String, ejeY: String): Line {
        val punto = LinePoint()
        val EjeX = ejeX.toDouble()
        val EjeY = ejeY.toDouble()
        punto.setX(EjeX)
        punto.setY(EjeY)
        linea.addPoint(punto)
        val tvPuntos = findViewById<TextView>(R.id.tvPuntos)
        tvPuntos.text = "${tvPuntos.text}\nX: $ejeX, Y:$ejeY"

        return linea
    }

    fun graficar(linea: Line) {
        val grafico = findViewById<LineGraph>(R.id.lineGrafica)
        grafico.addLine(linea)
        val Sices=Sizes.toFloat()
        grafico.setRangeX(1f, Sices)
        grafico.setRangeY(0f, 30f)
        grafico.lineToFill = 0
    }

}