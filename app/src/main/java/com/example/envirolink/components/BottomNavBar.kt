package com.example.envirolink.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.envirolink.activity.HomeActivity
import com.example.envirolink.activity.WeatherActivity
import com.example.envirolink.activity.WeatherTipsActivity

@Composable
fun BottomNavBar(
    context: Context
) {
    val currentActivity = LocalContext.current.javaClass.simpleName

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Home Button
            Button(
                onClick = {
                    if (currentActivity != "HomeActivity") {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentActivity == "HomeActivity") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentActivity == "HomeActivity") Color(0xFFc0dbfb) else Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home",
                    Modifier.size(22.dp)
                )
            }

            // Weather Button
            Button(
                onClick = {
                    if (currentActivity != "WeatherActivity") {
                        val intent = Intent(context, WeatherActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentActivity == "WeatherActivity") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentActivity == "WeatherActivity") Color(0xFFc0dbfb) else Color.White
                ),
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.WbSunny,
                    contentDescription = "Weather",
                    Modifier.size(22.dp)
                )
            }

            // Articles Button
            Button(
                onClick = {
                    if (currentActivity != "WeatherTipsActivity") {
                        val intent = Intent(context, WeatherTipsActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentActivity == "WeatherTipsActivity") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentActivity == "WeatherTipsActivity") Color(0xFFc0dbfb) else Color.White
                ),
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = "Articles",
                    Modifier.size(22.dp)
                )
            }
        }
    }
}
