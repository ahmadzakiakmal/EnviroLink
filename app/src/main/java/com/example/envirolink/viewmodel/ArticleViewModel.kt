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

    // State for a single unique article
    private val _uniqueArticle = MutableStateFlow<Article?>(null)
    val uniqueArticle: StateFlow<Article?> = _uniqueArticle

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            _loading.value = true
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
            } finally {
                _loading.value = false
            }
        }
    }

    fun fetchUniqueArticle(title: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getNewsDetail(
                    query = title,
                    searchIn = "title",
                    language = "en",
                    sortBy = "publishedAt",
                    pageSize = 1
                )

                if (response.articles.isNotEmpty()) {
                    _uniqueArticle.value = response.articles.first()
                    Log.d("artikel", response.articles.first().toString())
                } else {
                    _uniqueArticle.value = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uniqueArticle.value = null
            }
        }
    }
}
