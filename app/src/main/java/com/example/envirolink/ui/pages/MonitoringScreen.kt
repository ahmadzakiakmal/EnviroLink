package com.example.envirolink.ui.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.envirolink.components.Indicator
import com.example.envirolink.utils.UiState
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringScreen(
    onBack: () -> Unit = {},
    deviceName: String,
//    deviceId: String
) {
    // Initial values for temperature and humidity
    var temperature by remember { mutableDoubleStateOf(Random.nextDouble(20.0, 30.0)) }
    var humidity by remember { mutableDoubleStateOf(Random.nextDouble(40.0, 60.0)) }

    // Animated arcValue for temperature
    val animatedTemperature = animateFloatAsState(
        targetValue = ((temperature - 20.0) / 10.0f).toFloat(),
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    // Animated arcValue for humidity
    val animatedHumidity = animateFloatAsState(
        targetValue = ((humidity - 40.0) / 20.0f).toFloat(),
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    // Update temperature and humidity every 5 seconds
    LaunchedEffect(Unit) {
        while (true) {
            temperature = Random.nextDouble(20.0, 30.0)
            humidity = Random.nextDouble(40.0, 60.0)
            delay(5000L) // Refresh every 5 seconds
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoring - $deviceName") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                    ) {
                    Text(
                        text = deviceName,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold

                        ),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 16.dp)


                    )
                }
            }

            // Temperature Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Temperature",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 24.dp),

                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Temperature Indicator
                        Indicator(
                            state = UiState(
                                name = "Temperature",
                                value = "${temperature.toInt()}°C", // Display temperature as integer
                                maxValue = "30",
                                arcValue = animatedTemperature.value // Animated arc value for temperature
                            ),
                            variant = "Temperature",
                            unit = "°C"
                        )
                    }
                }
            }

            item{
                Spacer(modifier = Modifier.height(32.dp))

            }

            // Humidity Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), // Adjust height to ensure uniform size
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Humidity",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 24.dp),
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Humidity Indicator
                        Indicator(
                            state = UiState(
                                name = "Humidity",
                                value = "${humidity.toInt()}%", // Display humidity as integer
                                maxValue = "60",
                                arcValue = animatedHumidity.value // Animated arc value for humidity
                            ),
                            variant = "Humidity",
                            unit = "%"
                        )
                    }
                }
            }
        }
    }
}


