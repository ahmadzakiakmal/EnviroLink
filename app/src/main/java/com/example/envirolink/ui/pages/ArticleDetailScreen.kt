package com.example.envirolink.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envirolink.ui.theme.EnviroLinkTheme

@Composable
fun ArticleDetailScreen(articleId: String, navigateBack: () -> Unit = {}) {
    EnviroLinkTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back Button and Profile Image Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }

            // Article Title
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
            {
                Text(
                    text = articleId,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Scrollable Article Body
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = """
                    WASHINGTON — The Air Quality Index (AQI) is "not just a number" and people who ignore it could be putting their health at serious risk, warns Environmental Protection Agency (EPA) Administrator Michael Regan.
                    
                    The AQI, which ranges from 0 to 500, has been making headlines recently as wildfires and industrial pollution push air quality to dangerous levels in cities across the globe, reminding some residents of the severe smog events of the 1950s and 60s that led to the creation of modern air quality standards.
                    
                    Asked about the rising concern over air quality at a press conference Tuesday, Regan said: "The AQI is not just a number. It's a vital tool that directly relates to public health. Ignoring it, especially when it's high, can have serious consequences."
                    
                    "I'm going to be very clear about this," he added. "When the AQI is high, limit your outdoor activities if you're sensitive to air pollution, and everyone should avoid prolonged outdoor exertion."
                """.trimIndent(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp

                )
            }
        }
    }
}

