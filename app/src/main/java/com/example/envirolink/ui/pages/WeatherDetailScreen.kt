package com.example.envirolink.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.ui.theme.InterFamily

@Composable
fun WeatherDetailScreen(navController: NavController) {
    EnviroLinkTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CurrentWeather()
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        WeatherDetails()
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Forecast()
                    }
                }
            }
        }

        BottomNavBar(navController = navController)
    }
}

@Composable
fun CurrentWeather() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .height(100.dp)
                        .wrapContentHeight(Alignment.Top)
                        .align(
                            Alignment.TopEnd
                        ),
                    text = "27°",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.em
                )
            }
            Text(
                text = "LOREM",
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Senin, 09/24",
                fontFamily = InterFamily,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun WeatherDetails() {
    Column(
        modifier = Modifier
            .background(Color(0xFFF4F4F4))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherDetailItem(icon = Icons.Default.Air, "29", unit = "km/h")
            WeatherDetailItem(icon = Icons.Default.InvertColors, "0.9", unit = "%")
            WeatherDetailItem(icon = Icons.Default.CloudQueue, "30", unit = "%")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherDetailItem(icon = Icons.Default.WbSunny, "3", "")
            WeatherDetailItem(icon = Icons.Default.Thermostat, "26.5°", "")
            WeatherDetailItem(icon = Icons.Default.Visibility, "50", "")
        }
    }
}

@Composable
fun WeatherDetailItem(icon: ImageVector, value: String, unit: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(60.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Weather Detail Icon",
            modifier = Modifier.size(24.dp),
            tint = Color(0xFF6C757E)
        )
        Text("Lorem", color = Color(0xFFCDD2DE))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color(0xFF333333),
                fontFamily = InterFamily,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = if (unit !== "") "($unit)" else "",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun Forecast() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ForecastItem("10/07", "22°C", "193")
        ForecastItem("10/08", "22°C", "193", isSelected = true)
        ForecastItem("10/09", "27°C", "150")
        ForecastItem("10/10", "28°C", "78")
    }
}

@Composable
fun ForecastItem(date: String, temp: String, value: String, isSelected: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                if (isSelected) Color.Gray else Color.LightGray,
                CircleShape
            )
            .padding(8.dp)
    ) {
        Text(text = date, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = temp, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 14.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherDetailScreen() {
    WeatherDetailScreen(navController = rememberNavController())
}