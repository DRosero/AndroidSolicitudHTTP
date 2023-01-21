package com.aplicacion.solicitudhttp

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Parcel
import android.os.Parcelable
import android.os.StrictMode
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@SuppressLint("ParcelCreator")
class DescargaURL(var completadoListener: ICompletadoListener?) :AsyncTask<String, Void, String>(), Parcelable {




    override fun doInBackground(vararg p0: String): String? {
        return try {
            descargarDatos(p0[0])
        } catch (e: IOException) {
            return null
        }
    }

    override fun onPostExecute(result: String?){
         try {
             if (result != null) {
                 completadoListener?.descargaCompleta(result)
             }
        }
        catch (e:Exception) {

        }
    }

    @Throws(IOException::class)
    private fun descargarDatos(url:String):String{

        var inputStream: InputStream? =null
        try {
            val url= URL(url)
            val conexion=url.openConnection() as HttpURLConnection
            conexion.requestMethod = "POST"
            conexion.connectTimeout =5000
            conexion.connect()
            inputStream=conexion.inputStream
            return inputStream.bufferedReader().use{
                it.readText()
            }

        }

        finally {
            if(inputStream!=null){
                inputStream.close();
            }

        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }





}