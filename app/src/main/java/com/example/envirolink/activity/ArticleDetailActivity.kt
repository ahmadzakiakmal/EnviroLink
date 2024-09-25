package com.example.envirolink.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.envirolink.ui.pages.ArticleDetailScreen

class ArticleDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val articleId = intent.getStringExtra("ARTICLE_ID")
            ArticleDetailScreen(articleId = articleId ?: "", navigateBack = {
               val intent = Intent(this, WeatherTipsActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }
}