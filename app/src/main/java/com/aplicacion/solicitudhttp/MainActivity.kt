package com.aplicacion.solicitudhttp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.InvocationTargetException
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), ICompletadoListener {

    var btnComprobarRed: Button? =null;
    var btnSolicitudHTTP: Button? =null;
    var btnSolicitudHTTPAsinc: Button?=null;
    val network=Network();


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSolicitudHTTP=findViewById<Button>(R.id.btnSolicitudHTTP);
        btnComprobarRed =findViewById<Button>(R.id.btnComprobarRed);
        btnSolicitudHTTPAsinc=findViewById<Button>(R.id.btnHTTPAsinc);
        val network=Network();
        clicBotonComprobarred();
        clicBotonSolicitudHTTP();
        clicBotonHTTPAsin();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun clicBotonComprobarred(){
        btnComprobarRed?.setOnClickListener {
            if (network.validadRed(this)) {
                Toast.makeText(this, "Si hay Red", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this,"No hay red", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun clicBotonSolicitudHTTP(){
        btnSolicitudHTTP?.setOnClickListener {
            if (network.validadRed(this)) {
                Log.d("botonSolicitudHTTP",descargarDatos("https://www.elcomercio.com/"))
            } else{
                Toast.makeText(this,"No hay red", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Throws(IOException::class)
    private fun descargarDatos(url:String):String{
        val policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
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

    @RequiresApi(Build.VERSION_CODES.M)
    fun clicBotonHTTPAsin(){
        btnSolicitudHTTPAsinc?.setOnClickListener {
            if(network.validadRed(this)){
                DescargaURL(this).execute("https://www.elcomercio.com/");
            } else{
                Toast.makeText(this,"No hay red",Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun descargaCompleta(resultado: String) {
        Log.d("Descarga completa", resultado);
    }
}