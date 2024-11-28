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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.envirolink.model.Forecast
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InterFamily
import kotlin.random.Random

@Composable
fun WeatherDetailScreen(temperature: Double, cloud: Int, wind_kph: Double, humidity: Int, uv: Double, heatIndex: Double, visibilityKm: Double, aqi: Int, forecast: Forecast) {
    var darkMode by remember {
        mutableStateOf(false)
    }
    EnviroLinkTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(if (darkMode) 0xFF16181b else 0xFFFFFFFF))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {
                item {
                    Row(
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { darkMode = !darkMode }) {
                            Text(text = if (darkMode) "Dark" else "Light")
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CurrentWeather(temperature)
                    }
                }

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        WeatherDetails(cloud, wind_kph, humidity, uv, heatIndex, visibilityKm)
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Forecast(temperature, forecast, aqiToday = aqi)
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentWeather(temperature: Double) {
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
                    text = "$temperature°",
                    fontFamily = InterFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.em
                )
            }
            Text(
                text = "John Doe",
                fontFamily = InterFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Jumat, 11/29",
                fontFamily = InterFamily,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun WeatherDetails(cloud: Int, wind_kph: Double, humidity: Int, uv: Double, heatIndex: Double, visibilityKm: Double) {
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
            WeatherDetailItem(indicatorFor = "Wind Speed",icon = Icons.Default.Air, "$wind_kph", unit = "km/h")
            WeatherDetailItem(indicatorFor = "Humidity", icon = Icons.Default.InvertColors, "$humidity", unit = "%")
            WeatherDetailItem(indicatorFor = "Cloud Coverage", icon = Icons.Default.CloudQueue, "$cloud", unit = "%")
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherDetailItem(indicatorFor = "UV Index", icon = Icons.Default.WbSunny, "$uv", "")
            WeatherDetailItem(indicatorFor = "Heat Index", icon = Icons.Default.Thermostat, "$heatIndex°", "")
            WeatherDetailItem(indicatorFor = "Visibility", icon = Icons.Default.Visibility, "$visibilityKm", "km")
        }
    }
}

@Composable
fun WeatherDetailItem(indicatorFor: String, icon: ImageVector, value: String, unit: String) {
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
        Text(indicatorFor, color = Color(0xFFCDD2DE), fontSize = 14.sp)
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
fun Forecast(tempToday: Double, forecast: Forecast, aqiToday: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ForecastItem("Kamis","11/28", "26.4°C", "193")
        ForecastItem("Jumat","11/29", "$tempToday°C", "$aqiToday", isSelected = true)
        ForecastItem("Sabtu","11/30", "${forecast.forecastday[1].day.avgtemp_c}°C", "")
        ForecastItem("Minggu","12/01", "${forecast.forecastday[2].day.avgtemp_c}°C", "")
    }
}

@Composable
fun ForecastItem(day: String, date: String, temp: String, value: String, isSelected: Boolean = false) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                if (isSelected) Color.Gray else Color.LightGray
            )
            .padding(top = 18.dp, bottom = 18.dp, start = 9.dp, end = 9.dp)
            .width(63.dp)
    ) {
        Text(
            day,
            fontSize = 14.sp,
            fontFamily = InterFamily,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Text(text = date, fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            Modifier
                .clip(CircleShape)
                .background(Color(0xFFD9D9D9))
                .size(40.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = temp,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else Color.DarkGray,
            fontFamily = InterFamily,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.DarkGray
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewWeatherDetailScreen() {
//    WeatherDetailScreen(temperature = 20)
//}