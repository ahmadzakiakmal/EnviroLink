package com.example.envirolink.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.envirolink.ui.pages.ArticleDetailScreen
import com.example.envirolink.viewmodel.ArticleViewModel

class ArticleDetailActivity : ComponentActivity() {
    private val articleViewModel: ArticleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val title = intent.getStringExtra("ARTICLE_TITLE")
            ArticleDetailScreen(title = title ?: "", navigateBack = {
               val intent = Intent(this, WeatherTipsActivity::class.java)
                startActivity(intent)
                finish()
            }, viewModel = articleViewModel)
        }
    }
}