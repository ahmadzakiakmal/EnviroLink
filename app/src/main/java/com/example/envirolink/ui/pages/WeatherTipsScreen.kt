package com.example.envirolink.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.envirolink.components.BottomNavBar
=======
import com.example.envirolink.components.ArticleItem
>>>>>>> 6e51a6a09de3bab233eaf80dd0552288df19a0d4
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily
import com.example.envirolink.viewmodel.ArticleViewModel

@Composable
<<<<<<< HEAD
fun WeatherTipsScreen(navController: NavController, viewModel: ArticleViewModel = viewModel()) {
    val articles = viewModel.articles.collectAsState()
=======
fun WeatherTipsScreen(condition: String) {
    val context = LocalContext.current

>>>>>>> 6e51a6a09de3bab233eaf80dd0552288df19a0d4
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

<<<<<<< HEAD
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
=======
                        // Add the list of articles
                        ArticleItem(context = context, articleId= "2024001", title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "Understanding Air Quality Index", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "How Weather Affects Your Mood", publisher = "Publisher", date = "Date")
                        ArticleItem(context = context, articleId= "2024001", title = "Tips for Staying Healthy in Polluted Areas", publisher = "Publisher", date = "Date")
                    }
                }
            }
>>>>>>> 6e51a6a09de3bab233eaf80dd0552288df19a0d4
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
