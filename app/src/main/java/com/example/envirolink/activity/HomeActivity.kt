package com.example.envirolink.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.pages.HomeScreen

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
            BottomNavBar(context = this)
        }
    }
}
