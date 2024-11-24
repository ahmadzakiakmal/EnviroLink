package com.example.envirolink.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.platform.LocalContext
import com.example.envirolink.activity.DeviceActivity
import com.example.envirolink.activity.HomeActivity
import com.example.envirolink.activity.WeatherActivity
import com.example.envirolink.activity.WeatherTipsActivity
import com.example.envirolink.utils.Pages

@Composable
fun BottomNavBar(
    context: Context
) {
    val currentActivity = LocalContext.current.javaClass.simpleName
    val isInHomePage = currentActivity == Pages.HOME.activityName || currentActivity == Pages.MAIN.activityName
    val isInWeatherPage = currentActivity == Pages.WEATHER.activityName
    val isInArticlePage = currentActivity == Pages.WEATHERTIPS.activityName
    val isInDeviceListPage = currentActivity == Pages.DEVICELIST.activityName

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
                    if (!isInHomePage) {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (isInHomePage) Color(0xFF539DF3) else Color.Black,
                    containerColor = if (isInHomePage) Color(0xFFc0dbfb) else Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home",
                    Modifier.size(24.dp)
                )

                if (isInHomePage) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Beranda",
                        color = Color.Black,
                    )
                }
            }

            // Weather Button
            Button(
                onClick = {
                    if (!isInWeatherPage) {
                        val intent = Intent(context, WeatherActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (isInWeatherPage) Color(0xFF539DF3) else Color.Black,
                    containerColor = if (isInWeatherPage) Color(0xFFc0dbfb) else Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.WbSunny,
                    contentDescription = "Weather",
                    Modifier.size(24.dp)
                )

                if (isInWeatherPage) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Cuaca",
                        color = Color.Black,
                    )
                }
            }

            // Articles Button
            Button(
                onClick = {
                    if (!isInArticlePage) {
                        val intent = Intent(context, WeatherTipsActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (isInArticlePage) Color(0xFF539DF3) else Color.Black,
                    containerColor = if (isInArticlePage) Color(0xFFc0dbfb) else Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Lightbulb,
                    contentDescription = "Articles",
                    Modifier.size(24.dp)
                )

                if (isInArticlePage) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Tips",
                        color = Color.Black,
                    )
                }
            }

            Button(
                onClick = {
                    if (!isInDeviceListPage) {
                        val intent = Intent(context, DeviceActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = if (isInDeviceListPage) Color(0xFF539DF3) else Color.Black,
                    containerColor = if (isInDeviceListPage) Color(0xFFc0dbfb) else Color.White
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Contactless,
                    contentDescription = "Articles",
                    Modifier.size(24.dp)
                )

                if (isInDeviceListPage) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Perangkat",
                        color = Color.Black,
                    )
                }
            }
        }
    }
}
