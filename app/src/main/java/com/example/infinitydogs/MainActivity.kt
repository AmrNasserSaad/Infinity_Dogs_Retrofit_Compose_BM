package com.example.infinitydogs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.infinitydogs.api.Dog
import com.example.infinitydogs.api.DogAPiCallable
import com.example.infinitydogs.ui.theme.InfinityDogsTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfinityDogsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

                }
            }
        }
    }
}


fun getDogImage(onReceived: (Dog?) -> Unit) {
    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://dog.ceo")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val c = retrofit.create(DogAPiCallable::class.java)
    c.getDogImage().enqueue(object : Callback<Dog> {
        override fun onResponse(p0: Call<Dog>, response: Response<Dog>) {
            val dog = response.body()
            onReceived(dog)
        }

        override fun onFailure(p0: Call<Dog>, p1: Throwable) {
            onReceived(null)
        }
    })
}
