package com.aplicacion.solicitudhttp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    var btnComprobarRed: Button? =null;
    var btnSolicitudHTTP: Button? =null;
    val network=Network();


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSolicitudHTTP=findViewById<Button>(R.id.btnSolicitudHTTP);
        btnComprobarRed =findViewById<Button>(R.id.btnComprobarRed);
        val network=Network();
        clicBotonComprobarred();
        clicBotonSolicitudHTTP();
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

    fun clicBotonSolicitudHTTP(){
        btnSolicitudHTTP?.setOnClickListener {
            Toast.makeText(this,"Si funciono la funcion",Toast.LENGTH_SHORT).show();
        }
    }
}