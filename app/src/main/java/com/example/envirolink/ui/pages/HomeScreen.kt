package com.example.envirolink.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envirolink.DayBox
import com.example.envirolink.PlaceholderCircle
import com.example.envirolink.model.Current
import com.example.envirolink.model.Forecast
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.ui.theme.InterFamily

@Composable
fun HomeScreen(current: Current, forecast: Forecast) {
    EnviroLinkTheme {
        // A surface container using the 'background' color from the theme
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFFFFFFF)),
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .background(Color(0xFFFFFFFF))
                            .fillMaxWidth()
                            .padding(top = 26.dp, start = 20.dp, end = 20.dp)
                    ) {
                        PlaceholderCircle()
                    }
                    Row(
                        Modifier
                            .background(Color(0xFFFFFFFF))
                            .padding(top = 38.dp, bottom = 70.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "SLEMAN",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = InriaSansFamily
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,  // This refers to the Material Design "Favorite" icon
                            contentDescription = "Dropdown Icon", // Describe the icon for accessibility
                            tint = Color.Black                      // Optional: Change the color of the icon
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Color(0xFFFFFFFF))
                            .fillMaxWidth()
                            .padding(bottom = 44.dp)
                    ) {
                        DayBox(temperature = 26.4, day = "Kamis", aqi = "193", date = "11/28", today = false)
                        Spacer(Modifier.width(30.dp))
                        DayBox(temperature = current.temp_c, day = "Jumat", aqi = current.air_quality.o3.toInt().toString(), date = "11/29", today = true)
                        Spacer(Modifier.width(30.dp))
                        DayBox(temperature = forecast.forecastday[1].day.avgtemp_c, day = "Sabtu", aqi = "", date = "11/30", today = false)
                        Spacer(Modifier.width(30.dp))
                        DayBox(temperature = forecast.forecastday[2].day.avgtemp_c, day = "Minggu", aqi = "", date = "12/01", today = false)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFFFF))
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(0.dp)
                                .clip(
                                    CircleShape
                                )
                                .background(Color(0xFFD9D9D9))
                                .size(240.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    "AQI",
                                    fontSize = 18.sp,
                                    fontFamily = InterFamily,
                                    color = Color(0xFF333333)
                                )
                                Text(current.air_quality.o3.toInt().toString(), fontSize = 30.sp, fontFamily = InterFamily)
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewHomeScreen() {
//    HomeScreen(aqi = 193)
//}
