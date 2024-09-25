package com.example.envirolink.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import com.example.envirolink.components.ArticleItem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envirolink.components.BottomNavBar
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.viewmodel.ArticleViewModel

@Composable
fun WeatherTipsScreen(navController: NavController, viewModel: ArticleViewModel = viewModel()) {
    val articles = viewModel.articles.collectAsState()
    EnviroLinkTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
                    .padding(16.dp)
            ) {
                // Static content, e.g., "Today's Tips"
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)

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

                item(
                ) {
                    Text(text = "Daily Articles", fontFamily = InriaSansFamily)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Dynamic list of articles
                items(articles.value.size) { index ->
                    val article = articles.value[index]
                    ArticleItem(
                        articleId = index.toString(),
                        navController = navController,
                        title = article.title,
                        publisher = article.source.name,
                        date = article.publishedAt
                    )
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