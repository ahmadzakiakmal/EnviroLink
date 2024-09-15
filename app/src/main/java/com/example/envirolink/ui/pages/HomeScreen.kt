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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.envirolink.DayBox
import com.example.envirolink.PlaceholderCircle
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily

@Composable
fun HomeScreen(navController: NavController) {
    EnviroLinkTheme {
        // A surface container using the 'background' color from the theme
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .background(Color(0x10FF0000))
                            .fillMaxWidth()
                            .padding(top = 26.dp, start = 20.dp, end = 20.dp)
                    ) {
                        PlaceholderCircle()
                    }
                    Row(
                        Modifier
                            .background(Color(0x1000FF00))
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
                            .background(Color(0x100000FF))
                            .fillMaxWidth()
                            .padding(bottom = 44.dp)
                    ) {
                        DayBox(today = false)
                        Spacer(Modifier.width(30.dp))
                        DayBox(today = true)
                        Spacer(Modifier.width(30.dp))
                        DayBox(today = false)
                        Spacer(Modifier.width(30.dp))
                        DayBox(today = false)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            Modifier
                                .clip(
                                    CircleShape
                                )
                                .background(Color(0xFFD9D9D9))
                                .size(240.dp)
                        )
                    }
                }

                BottomNavBar(navController = navController)
            }
        }
    }
}