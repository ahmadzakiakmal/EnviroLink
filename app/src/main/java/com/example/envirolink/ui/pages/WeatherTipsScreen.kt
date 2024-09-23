package com.example.envirolink.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import com.example.envirolink.components.ArticleItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily

@Composable
fun WeatherTipsScreen(navController: NavController) {
    EnviroLinkTheme {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White)
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
                            .padding(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Today's Tips 💡", fontFamily = InriaSansFamily)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Don't forget your umbrella and wear waterproof shoes. Indoor activities might be preferable today.",
                                fontFamily = InriaSansFamily
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Daily Articles", fontFamily = InriaSansFamily)
                        Spacer(modifier = Modifier.height(8.dp))

                        // Add the list of articles
                        ArticleItem(articleId= "2024001", navController = navController, title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(articleId= "2024001", navController = navController, title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                    }
                }
            }

            BottomNavBar(navController)
        }
    }
}

@Preview
@Composable
fun PreviewWeatherTipsScreen() {
    WeatherTipsScreen(navController = rememberNavController())
}