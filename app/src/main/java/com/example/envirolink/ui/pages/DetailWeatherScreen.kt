package com.example.envirolink.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InriaSansFamily

@Preview(showBackground = true)
@Composable
fun DetailWeatherScreen(){
    EnviroLinkTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ){
                                Text(modifier = Modifier.height(100.dp).wrapContentHeight(Alignment.Top).align(Alignment.TopEnd), text = "27°", fontFamily = InriaSansFamily, fontSize = 10.em)
                            }
                            Text(
                                text = "Lorem",
                                fontFamily = InriaSansFamily
                            )
                            Text(
                                text = "Senin, 09/24",
                                fontFamily = InriaSansFamily
                            )
                        }
                    }
                }
            }
        }
    }
}