package com.portafolio.appdivisas.utiles;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class manipularJSON {

    private static String TAG = "MANEJAR_JSON";

    public void SaveJsonBasico(String params, JsonObject mJsonResponse, String PackageName, Context context) throws IOException {

        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();

    }
/*
    public Object ReadingJsonBasico(String params, String PackageName, Context context) {

        String ubicacionJSON = "/data/data/" + PackageName + "/files/" + params;

        Gson gson = new Gson();
        Object objetoPersonal = null;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(ubicacionJSON));
            if( buffer!=null    ) {
                objetoPersonal = gson.fromJson(buffer, body_json.class);
            }
          //  System.out.println(objetoPersonal);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return objetoPersonal;

    }
*/
    public void Save_body_cell(String params, JsonObject mJsonResponse, String PackageName, Context context) throws JSONException, IOException {

        //Log.d(TAG, "Save_body_cell() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }

    public void Save_body(String params, JsonObject mJsonResponse, String PackageName, Context context) throws JSONException, IOException {
        //Log.d(TAG, "Save_body() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }


    public void JSONSave(String params, JsonObject mJsonResponse, String PackageName, Context context) throws JSONException, IOException {
        //Log.d(TAG, "JSONSave() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }




    public void mCreateAndSaveFile(String params, String mJsonResponse, String PackageName, Context context) throws JSONException, IOException {
        //Log.e(TAG, "---------------------------------------------------------------------------------------------------------------------");
        //Log.d(TAG, "mCreateAndSaveFile() called with: params = [" + params + "], mJsonResponse = [" + mJsonResponse + "], PackageName = [" + PackageName + "], context = [" + context + "]");
        //Log.e(TAG, "---------------------------------------------------------------------------------------------------------------------");
        // Convert JsonObject to String Format
        String userString = mJsonResponse.toString();
        // Define the File Path and its Name
        File file = new File(context.getFilesDir(), params);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();


    }

    public String mReadJsonData(String params, String PackageName, Context context) {

        String mResponse = null;

        try {
            File f = new File("/data/data/" + PackageName + "/" + params);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            mResponse = new String(buffer);


        } catch (IOException e) {

            try {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return mResponse;
    }



    public JSONObject ReadJWTDesencriptado(String params, String PackageName) {
        String ubicacionJSON = "/data/data/" + PackageName + "/files/" + params;
        JSONObject json = null;

        //  json= new File(ubicacionJSON);

        return json;
    }

    public void DeleteFileJSON(String params, String PackageName) {
        String ubicacionJSON = "/data/data/" + PackageName + "/files/" + params;
        File file = new File(ubicacionJSON);
        //Log.d(TAG, "DeleteFileJSON() called with: params = [" + params + "], PackageName = [" + PackageName + "]");
        if (file.delete())                      //returns Boolean value
        {
            Log.e(TAG, "DeleteFileJSON: " + file.getName() + " deleted");

        } else {
            Log.e(TAG, "DeleteFileJSON: " + "failed");

        }


    }

}
