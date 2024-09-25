package com.example.envirolink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envirolink.ui.pages.HomeScreen
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.ui.theme.InterFamily
import com.example.envirolink.components.BottomNavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
            BottomNavBar(context = this)
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewWeatherDetailScreen() {
//    val navController = rememberNavController()
//    val weatherCondition = mutableStateOf("Sunny")
//
//    NavHost(navController = navController, startDestination = "home") {
//        composable("home") { HomeScreen(navController) }
//        composable("weather") { WeatherDetailScreen(navController) }
//        composable("tips") { WeatherTipsScreen(navController, weatherCondition) }
//        composable("article/{articleId}") { backStackEntry ->
//            val articleId = backStackEntry.arguments?.getString("articleId")
//            ArticleDetailScreen(
//                articleId = articleId ?: "",
//                navigateBack = { navController.popBackStack() })
//        }
//    }
//}
