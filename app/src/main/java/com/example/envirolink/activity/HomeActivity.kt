package com.example.envirolink.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.HomeScreen
import kotlin.random.Random

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val aqi = Random.nextInt(78, 198)
        setContent {
            HomeScreen(aqi)
            BottomNavBar(context = this)
        }
    }

    override fun onResume() {
        super.onResume()
        val aqi = Random.nextInt(78, 198)
        Log.d("Lifecycle", "Resume Activity, new AQI value: $aqi")
        setContent {
            HomeScreen(aqi)
            BottomNavBar(context = this)
        }
    }
}
