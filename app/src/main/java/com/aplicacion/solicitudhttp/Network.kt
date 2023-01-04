package com.aplicacion.solicitudhttp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

open class Network {





        @RequiresApi(Build.VERSION_CODES.M)
        fun validadRed(activity: AppCompatActivity):Boolean{
            val validadorRed=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
            val informacionRed= validadorRed.getNetworkCapabilities(validadorRed.activeNetwork);
            if(informacionRed!=null){
                if (informacionRed.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||informacionRed.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)||informacionRed.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    return true;
                }
                /*else if (informacionRed.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    return true;
                }
                else if (informacionRed.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    return true;
                }*/
            }
             return false;
        }

}