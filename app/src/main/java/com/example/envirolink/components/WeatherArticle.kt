package com.example.envirolink.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.envirolink.activity.ArticleDetailActivity
import com.example.envirolink.ui.theme.InriaSansFamily
import java.util.*

@Composable
fun ArticleItem(
    context: Context,
    title: String,
    publisher: String,
    date: String,
    articleId: String
) {
    // Split the date string at 'T' to get only the date portion
    val formattedDate = date.split("T")[0]

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                val intent = Intent(context, ArticleDetailActivity::class.java).apply {
                    putExtra("ARTICLE_ID", articleId)
                }
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, fontFamily = InriaSansFamily)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = publisher, fontFamily = InriaSansFamily)
                Text(text = formattedDate, fontFamily = InriaSansFamily)  // Display formatted date
            }
        }
    }
}


