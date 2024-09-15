package com.example.envirolink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.ui.theme.InterFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnviroLinkTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(Modifier.weight(1f)) {
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
                        ) {
                            DayBox(today = false)
                            Spacer(Modifier.width(30.dp))
                            DayBox(today = true)
                            Spacer(Modifier.width(30.dp))
                            DayBox(today = false)
                            Spacer(Modifier.width(30.dp))
                            DayBox(today = false)
                        }
                    }

                    Row( // Navbar
                        Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(12.dp),
                                clip = false
                            )
                            .background(Color.White)
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = { /*TODO*/ },
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
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black,
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
                        Button(
                            onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
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
        }
    }
}

@Composable
fun PlaceholderCircle() {
    Box(
        Modifier
            .clip(CircleShape)
            .background(Color(0xFFD9D9D9))
            .width(50.dp)
            .height(50.dp)
    ) {
    }
}

@Composable
fun DayBox(today: Boolean) {
    Column(
        modifier = Modifier
            .background(if (!today) Color.White else Color(0xFFA8A8A8))
            .width(64.dp)
            .padding(top = 18.dp, bottom = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "DAY",
            fontFamily = InriaSansFamily,
            fontSize = 14.sp,
            color = if (!today) Color.Black else Color.White
        )
        Text(
            "14/09",
            fontSize = 12.sp,
            fontFamily = InriaSansFamily,
            color = if (!today) Color(0xFFA0A7BA) else Color.Black
        )
        Box(
            Modifier
                .padding(14.dp)
                .clip(CircleShape)
                .size(30.dp)
                .background(Color(0xFFD9D9D9))
        )
        Text(
            "22° C",
            fontSize = 19.sp,
            fontFamily = InterFamily,
            fontWeight = FontWeight.Bold,
            color = if (!today) Color.Black else Color.White
        )
        Spacer(Modifier.height(13.dp))
        Box(
            Modifier
                .clip(RoundedCornerShape(7.dp))
                .background(Color(0xFF8E8D88))
        ) {
            Text(
                "192",
                color = Color.White,
                fontFamily = InterFamily,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 7.dp)
            )
        }
    }
}
