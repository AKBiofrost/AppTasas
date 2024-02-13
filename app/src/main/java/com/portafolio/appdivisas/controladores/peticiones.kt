package com.portafolio.appdivisas.controladores

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echo.holographlibrary.Line
import com.echo.holographlibrary.LineGraph
import com.echo.holographlibrary.LinePoint
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

    public fun init(arrayList: ArrayList<presentacion>, context: Context) {

        // getting the recyclerview by its id
        val recyclerview =
            (context as Activity).findViewById<RecyclerView>(R.id.recycler_activity_main)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

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
        val json = JsonObject()
        val Listfechas = JsonArray()
        val fechas = JsonObject()
        val Listmonto = JsonArray()
        val monto = JsonObject()
        val ListMoney_date = JsonArray()
        val Money_date = JsonObject()
        var tasas = "100"


        public fun getAllDate(context: Context, moneda: String) {

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

                        json.add("rates", response.body()?.get("rates"))

                        Log.e(TAG, "-------------------------------------------------------")

                    }

                    Log.i(TAG, "Reading: $json")
                    val gson = Gson()

                    var ratesObject = json.getAsJsonObject("rates")

                    ratesObject.entrySet().forEach { (fecha, monedaObject) ->

                        Listfechas.add(fecha)
                        Listmonto.add(monedaObject)
                        Log.i(TAG, "monedaObject: $fecha")
                        Log.e(TAG, "monedaObject: " + monedaObject)

                        monto.add("monto", Listmonto)
                    }
                    monto.add("monto", Listmonto)
                    fechas.add("fechas", Listfechas)
                    Log.i(TAG, "Reading monto: $monto")


                    /*---------------------------------------------------------------------------*/
                    Log.i(TAG, "monto: " + monto)
                    /*---------------------------------------------------------------------------*/


                    config.JSON.JSONSave(
                        "fechas.json", fechas,
                        "com.portafolio.appdivisas", context
                    )

                    config.JSON.JSONSave(
                        "monto.json", monto,
                        "com.portafolio.appdivisas", context
                    )


                    val montoObject = config.JSON.ReadingJsonListMonto(
                        "monto.json",
                        "com.portafolio.appdivisas",
                        context
                    )
                    val fechasObject = config.JSON.ReadingJsonListFecha(
                        "fechas.json",
                        "com.portafolio.appdivisas",
                        context
                    )
                    Log.i(TAG, "montoObject.monto: " + montoObject.monto)
                    //val montoObjectMonte = montoObject.size()
                    Log.i(TAG, "montoObject.monto: " + montoObject.monto.get(0).keySet().toString())
                    val keyMoney = montoObject.monto.get(0).keySet().toString()

                    keyMoney.replace(']', ' ')
                    keyMoney.replace('[', ' ')

                    Log.e(TAG, "montoObject.monto: " + keyMoney.substring(1))
                    val keyMoney1 = keyMoney.substring(1)
                    Log.e(TAG, "montoObject.monto: " + keyMoney.substring(0, keyMoney.length - 1))
                    val keyMoney2 = keyMoney1.substring(0, keyMoney.length - 2)

                    Log.i(TAG, "montoObject.monto: " + keyMoney2)

                    for (index in 0 until montoObject.monto.size) {
                        val ListComplete = JsonObject()
                        ListComplete.addProperty(
                            "tasa",
                            montoObject.monto.get(index).get(keyMoney2).toString()
                        )
                        ListComplete.addProperty("fecha", fechasObject.fechas.get(index))
                        ListMoney_date.add(ListComplete)
                        Log.i(TAG, "montoObject.monto: " + ListMoney_date)
                        Money_date.add("tasa", ListMoney_date)

                    }
                    Log.i(TAG, "Reading Money_date: $Money_date")
                    config.JSON.JSONSave(
                        "tasas.json",
                        Money_date,
                        "com.portafolio.appdivisas",
                        context
                    )
                    /*----------------------------------------------------------------------------------------*/
                    val graficosDatos = config.JSON.ReadingJsonDatosGraficos(
                        "tasas.json",
                        "com.portafolio.appdivisas",
                        context
                    )

                    /*----------------------------------------------------------------------------------------*/
                    var linea = Line()

                    Log.i(TAG, "graficosDatos total: " + graficosDatos.tasa.size)
                    Log.i(TAG, "graficosDatos %: " + graficosDatos.tasa.size * 0.80)
                    val sizes = graficosDatos.tasa.size * 0.80.toInt()


                    val json = JsonObject()
                    val Listfechas = JsonArray()
                    val fechas = JsonObject()
                    val Listmonto = JsonArray()
                    val monto = JsonObject()
                    val ListMoney_date = JsonArray()
                    val Money_date = JsonObject()



                    for (i in sizes until graficosDatos.tasa.size) {
                        Log.i(TAG, "graficosDatos: " + graficosDatos.tasa.size * 0.80.toInt())
                        if (i > graficosDatos.tasa.size * 0.80) {
                            Log.i(TAG, "graficosDatos--: " + i)

                            val ListComplete = JsonObject()
                            ListComplete.addProperty(
                                "tasa",
                                montoObject.monto.get(i).get(keyMoney2).toString()
                            )
                            ListComplete.addProperty("fecha", fechasObject.fechas.get(i))
                            ListMoney_date.add(ListComplete)
                            Log.i(TAG, "montoObject.monto: " + ListMoney_date)
                            Money_date.add("tasa", ListMoney_date)

                        }
                    }

                    config.JSON.JSONSave(
                        "tazas.json",
                        Money_date,
                        "com.portafolio.appdivisas",
                        context
                    )

                    val graficosDatoz = config.JSON.ReadingJsonDatosGraficos(
                        "tazas.json",
                        "com.portafolio.appdivisas",
                        context
                    )

                    for (i in sizes until graficosDatoz.tasa.size) {
                        Log.i(TAG, "graficosDatos: " + graficosDatoz.tasa.size * 0.80.toInt())
                        // if (i > graficosDatos.tasa.size * 0.80) {
                        Log.i(TAG, "graficosDatos--: " + i)
                        linea = datosGrafica(
                            linea,
                            i.toString(),
                            graficosDatoz.tasa.get(i).fecha.toString(),
                            graficosDatoz.tasa.get(i).tasa.toString(),
                            context
                        )
                        // }
                        tasas=graficosDatoz.tasa.get(i).tasa.toString()
                    }

                    Log.i(TAG, "graficosDatos--: " + tasas)

                    graficar(linea, context, graficosDatoz.tasa.size * 0.80)

                    /*-----------------------------------------------------------------------------------------------------------*/


                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    config.Dialog.AlertDialog_error(
                        "Falla de petición",
                        "La petición no es exitosa",
                        context
                    )

                }


            })

        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }

        public fun getAllComments(context: Context) {
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
                        arrayList.add(presentacion(response.body()?.rates?.ZAR.toString(), "ZAR"))
                        arrayList.add(presentacion(response.body()?.rates?.CHF.toString(), "CHF"))
                        arrayList.add(presentacion(response.body()?.rates?.MYR.toString(), "MYR"))
                        arrayList.add(presentacion(response.body()?.rates?.NZD.toString(), "NZD"))
                        Log.e(TAG, "-------------------------------------------------------")

                        init(arrayList, context)
                    }

                }

                override fun onFailure(call: Call<Dinero>, t: Throwable) {
                    config.Dialog.AlertDialog_error(
                        "Falla de petición",
                        "La petición no es exitosa",
                        context
                    )
                }
            })
        }

        public fun init(arrayList: ArrayList<presentacion>, context: Context) {

            // getting the recyclerview by its id
            val recyclerview =
                (context as Activity).findViewById<RecyclerView>(R.id.recycler_activity_main)

            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(context)

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

        fun reading(context: Context) {

            var rate = JsonObject()
            rate = config.JSON.ReadingJsonBasico("", context.packageName, context)

            val gson = Gson()

            val ratesObject = rate.getAsJsonObject("rates")
            val ratesArray = JsonArray()

            Log.i(TAG, "Reading: $ratesObject")
            Log.i(TAG, "Reading: $ratesObject")

            ratesObject.entrySet().forEach { (fecha, monedaObject) ->
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

        fun datosGrafica(
            linea: Line,
            ejeX: String,
            fecha: String,
            ejeY: String,
            context: Context
        ): Line {
            val punto = LinePoint()
            val EjeX = ejeX.toDouble()
            val EjeY = ejeY.toDouble()
            punto.setX(EjeX)
            punto.setY(EjeY)
            linea.addPoint(punto)
            linea.setColor(Color.parseColor("#FFBB33"));
            val tvPuntos = (context as Activity).findViewById<TextView>(R.id.tvPuntos)
            tvPuntos.text = "${tvPuntos.text}\nFecha: $fecha - tasa:$ejeY"

            return linea
        }

        fun graficar(linea: Line, context: Context, Sizes: Double) {
            val grafico = (context as Activity).findViewById<LineGraph>(R.id.lineGrafica)
            grafico.addLine(linea)
            val Sices = Sizes.toFloat()
            val SiceY=tasas.toFloat()+5
            grafico.setRangeX(1f, Sices)
            grafico.setRangeY(0f, SiceY)
            grafico.lineToFill = 0
        }


    }

}



