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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue

@Composable
fun BottomNavBar(
   navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                onClick =  { navController.navigate("home") },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentRoute == "home") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentRoute == "home") Color(0xFFc0dbfb) else Color.White
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
                onClick =  { navController.navigate("weather") },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentRoute == "weather") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentRoute == "weather") Color(0xFFc0dbfb) else Color.White
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
                onClick = { navController.navigate("tips") },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (currentRoute == "tips") Color(0xFF539DF3) else Color.Black,
                    containerColor = if (currentRoute == "tips") Color(0xFFc0dbfb) else Color.White
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
