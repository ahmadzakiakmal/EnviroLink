package com.example.envirolink.ui.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.envirolink.components.ArticleItem
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.viewmodel.ArticleViewModel

@Composable
fun WeatherTipsScreen(condition: String, viewModel: ArticleViewModel = viewModel()) {
    val articles = viewModel.articles.collectAsState()
    val isLoading = viewModel.loading.collectAsState()
    val context = LocalContext.current

    EnviroLinkTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (isLoading.value) {
                // Show a loader when loading
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 64.dp)
                        .padding(16.dp)
                ) {
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
                                    text = getWeatherTip(condition),
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
                            context,
                            articleId = index.toString(),
                            title = article.title,
                            publisher = article.source.name,
                            date = article.publishedAt
                        )
                    }
                }
            }
        }
    }
}

fun getWeatherTip(condition: String): String {
    return when (condition) {
        "Sunny" -> "It's a great day to go for a hike or a picnic! Don't forget your sunscreen."
        "Cloudy" -> "Perfect weather for a movie marathon or visiting a museum."
        "Rainy" -> "Don't forget your umbrella and wear waterproof shoes. Indoor activities might be preferable today."
        "Stormy" -> "Stay indoors and enjoy a good book or a series. Safety first!"
        "Windy" -> "How about flying a kite or enjoying a windy day at the beach?"
        "Foggy" -> "Visibility might be low, so it's better to stay inside and enjoy some cozy time with family."
        else -> "Enjoy your day, whatever the weather!"
    }
}

//@Preview
//@Composable
//fun PreviewWeatherTipsScreen() {
//    WeatherTipsScreen(navController = rememberNavController(), condition = "Sunny")
//}