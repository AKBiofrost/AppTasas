package com.portafolio.appdivisas.utiles;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;


import com.portafolio.appdivisas.R;

public class VentanaEmergente {

    public static final String TAG = "VentanaEmergente";
    private static final int REQUEST_CODE_BLUETOOTH_SCAN = 1;



    public AlertDialog.Builder alertDialogBuilder;
    AlertDialog dialog;
    private Context contextA;

    public void AlertDialog_error(String titulo, String mensaje, Context context) {
       alertDialogBuilder = new AlertDialog.Builder(context);
       alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)


                .setPositiveButton("Aceptar", (dialog, id) -> {
                            dialog.cancel();






                        }

                );
        CrearDialog();
    }
/*
    public void AlertDialog_OTP(String usuario, String Contraseña,
                                Context context, String titulo, String mensaje,
                                String modelo, Activity activity) {

        Log.d(TAG, "AlertDialog_OTP() called with: usuario = [" + usuario + "], Contraseña = [" + Contraseña + "], context = [" + context + "], alertDialogBuilder = [" + alertDialogBuilder + "], titulo = [" + titulo + "], mensaje = [" + mensaje + "], modelo = [" + modelo + "], activity = [" + activity + "]");
        datosPOS bodyC2P = new datosPOS();
        manipularJSON json = new manipularJSON();
        String serial = "";
        alertDialogBuilder = new AlertDialog.Builder(context);
        calibracion calibrar = new calibracion();
        bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
        Body_DatosDispositivos body_datosDispositivosCelular = new Body_DatosDispositivos();
        body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
        AppPreferences discriminante = new AppPreferences(context, URL_API.determinante);
        String validadorModelo = discriminante.getSmsBody("modelo_equipo");
        AppPreferences bluetoothM = new AppPreferences(context, "Bluetooth");
        int validadorPos = discriminante.getSmsBody_int("EsPOS");
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        autentificacion login = new autentificacion(true, 200);
        View customLayout = inflater.inflate(R.layout.dialog_otp, null);

        if (calibrar.EsPOS(context) == true) {

            if (calibrar.EsPOSBluetooth(context) == true) {

                Log.e(TAG, "----------: " + " morefun Bluetooth");
                body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();

            } else if (validadorModelo.equalsIgnoreCase("morefun")) {
                Log.e(TAG, "----------: " + " morefun");
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            } else if (validadorModelo.equalsIgnoreCase("sunyard")) {
                Log.e(TAG, "----------: " + " sunyard");
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            } else if (validadorModelo.equalsIgnoreCase("verifone")) {
                bodyC2P = json.ReadingDatosDispositivosPOS(URL_API.JSON_Datospos, context.getApplicationContext().getPackageName());
                Log.e(TAG, "BotonLogin:  bodyC2P.getDatosDispositivo().getSerial() " + bodyC2P.getDatosDispositivo().getSerial());
                serial = bodyC2P.getDatosDispositivo().getSerial();
            }

        } else {
            Log.e(TAG, "BotonLogin: " + " ES UN CELULAR");
            body_datosDispositivosCelular = json.ReadingDatosDispositivos(URL_API.JSON_datosDispositivos, context.getApplicationContext().getPackageName());

            serial = body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial();
            Log.e(TAG, "BotonLogin: ->" + body_datosDispositivosCelular.getDatosDispositivo().getPinPadSerial());

        }
        alertDialogBuilder.setTitle(titulo);
        alertDialogBuilder.setView(customLayout);
        alertDialogBuilder.setIcon(R.drawable.alert);
        datosPOS finalBodyC2P = bodyC2P;
        String serialT = serial;
        alertDialogBuilder.setMessage(mensaje).setCancelable(false)
                .setPositiveButton(
                        "Aceptar",
                        (dialog, which) -> {

                            EditText OTP = customLayout.findViewById(R.id.editText);
                            String otp_edittex = OTP.getText().toString().trim();
                            JsonObject Obj_otp = new JsonObject();
                            Obj_otp.addProperty("otp", otp_edittex);
                            Obj_otp.addProperty("serialDispositivo", serialT);
                            Log.e(TAG, "AlertDialog_OTP: " + serialT);
                            try {
                                Log.e(TAG, "AlertDialog_OTP: " + "otp_edittex.length()->" + otp_edittex.length());
                                if (otp_edittex.length() > 0 && otp_edittex.length() <= 6) {
                                    if (otp_edittex.length() == 6) {
                                        dialog.cancel();
                                        AlertDialog_EsperandoRespuestaServer(context, "Enviando petición", "Estamos procesando su usuario, espero un momento", false);
                                        login.OTP(Obj_otp, usuario, Contraseña, context, URL_API.SharedPreferend_JWT, modelo, activity);

                                    } else {
                                        Toast.makeText(context, "OTP debe contener 6 caracteres.", Toast.LENGTH_SHORT).show();
                                        // ((Activity) (context)).finish();
                                        AlertDialog_OTP(usuario, Contraseña, context,
                                                "Ingrese su código OTP", "", modelo, activity);
                                    }

                                } else {
                                    Toast.makeText(context, "OTP debe contener 6 caracteres.", Toast.LENGTH_SHORT).show();
                                    //((Activity) (context)).finish();
                                    AlertDialog_OTP(usuario, Contraseña, context,
                                            "Ingrese su código OTP", "", modelo, activity);
                                }


                            } catch (JSONException e) {
                                // //
                                Log.w(TAG, "-----------------------------------------------------------");
                                Log.e(TAG, "catch" + e.getMessage());
                                Log.w(TAG, "-----------------------------------------------------------");
                            }


                        });
        CrearDialog();


    }
*/

    private void CrearDialog() {
        dialog = alertDialogBuilder.create();
        dialog.show();
    }

    public void CerrarDialog() {

        dialog.dismiss();
    }


}
