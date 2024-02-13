package com.portafolio.appdivisas.controladores

import com.portafolio.appdivisas.utiles.VentanaEmergente
import com.portafolio.appdivisas.utiles.manipularJSON
import com.portafolio.appdivisas.utiles.sharePreferend

class  config {
    companion object {
        var companionVariable = "I am shared"
        var JSON= manipularJSON()
        var Dialog=VentanaEmergente()
        var paquete = "com.portafolio.appdivisas"
        var BASE_URL = "https://api.frankfurter.app"
        var respuesta=false
    }

}