package com.example.envirolink.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.envirolink.ui.theme.InriaSansFamily

@Composable
fun ArticleItem(title: String, publisher: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
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
                Text(text = date, fontFamily = InriaSansFamily)
            }
        }
    }
}
