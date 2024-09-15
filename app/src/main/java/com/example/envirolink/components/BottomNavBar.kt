package com.example.envirolink.components

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

@Composable
fun BottomNavBar(
    onHomeClick: () -> Unit,
    onWeatherClick: () -> Unit,
    onArticlesClick: () -> Unit
) {
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
                onClick = onHomeClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFF539DF3),
                    containerColor = Color(0xFFc0dbfb)
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
                onClick = onWeatherClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
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
                onClick = onArticlesClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
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
