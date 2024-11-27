package com.example.envirolink.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.envirolink.model.Article
import com.example.envirolink.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeatherNews(
                    query = "weather",
                    language= "en",
                    from = "2024-11-25",
                    sortBy = "publishedAt",
                    pageSize = 15,
                )
//                Log.d("getarticle", "${response.status}")
                _articles.value = response.articles
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
            }
        }
    }
}
