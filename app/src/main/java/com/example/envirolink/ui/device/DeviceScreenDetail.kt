package com.example.envirolink.ui.device

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envirolink.components.Indicator
import com.example.envirolink.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import kotlin.random.Random
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.envirolink.model.Forecast
import com.example.envirolink.ui.theme.EnviroLinkTheme
import com.example.envirolink.ui.theme.InterFamily

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeviceScreenDetail(deviceName: String, deviceId: String, onBack: () -> Unit = {}) {
    var isConnected by remember { mutableStateOf(false) }
    var iqaMessage by remember { mutableStateOf("") }
    var humidityMessage by remember { mutableStateOf("") }
    var temperatureMessage by remember { mutableStateOf("") }
    val serverUri = "tcp://20.187.145.145:1883"

    val mqttManager = remember { MqttClientManager(serverUri) { topic, msg ->
        // Update message values based on topic
        when {
            topic.contains("iqa", ignoreCase = true) -> iqaMessage = msg
            topic.contains("humidity", ignoreCase = true) -> humidityMessage = msg
            topic.contains("temperature", ignoreCase = true) -> temperatureMessage = msg
        }
        Log.d("MQTT", "Message received on $topic: $msg")
    } }

    val coroutineScope = rememberCoroutineScope()

    if (deviceId.isNotEmpty()) {
        coroutineScope.launch(Dispatchers.IO) {
            mqttManager.connectAndSubscribe(deviceId)
        }
        isConnected = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isConnected) {
            Text(
                text = "Connected to $deviceName",
                style = TextStyle(fontSize = 16.sp, color = Color.Green)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display separate messages for iqa, humidity, and temperature
        if (iqaMessage.isNotEmpty()) {
            Text(
                text = "IQA: $iqaMessage",
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (humidityMessage.isNotEmpty()) {
            Text(
                text = "Humidity: $humidityMessage %",
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (temperatureMessage.isNotEmpty()) {
            Text(
                text = "Temperature: $temperatureMessage °C",
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }
    }
}

class MqttClientManager(
    private val serverUri: String,
    private val onMessageReceived: (String, String) -> Unit  // Accept both topic and message
) {
    private lateinit var mqttClient: MqttClient

    fun connectAndSubscribe(deviceId: String) {
        try {
            mqttClient = MqttClient(serverUri, MqttClient.generateClientId(), MemoryPersistence())
            val options = MqttConnectOptions().apply {
                isAutomaticReconnect = true
                isCleanSession = false
                userName = "root"
                password = "123mudar".toCharArray()
            }
            mqttClient.connect(options)

            Log.d("MQTT", "Connected to the broker")

            mqttClient.setCallback(object : MqttCallback {
                override fun connectionLost(cause: Throwable?) {
                    Log.d("MQTT", "Connection lost: ${cause?.message}")
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    val receivedMessage = message?.toString()

                    if (!receivedMessage.isNullOrEmpty() && topic != null) {
                        // Log the topic and message for iqa, humidity, and temperature
                        if (topic.contains("iqa", ignoreCase = true)) {
                            Log.d("MQTT", "Topic: $topic | Message: $receivedMessage")
                            onMessageReceived(topic, receivedMessage)
                        } else if (topic.contains("humidity", ignoreCase = true)) {
                            Log.d("MQTT", "Topic: $topic | Message: $receivedMessage")
                            onMessageReceived(topic, receivedMessage)
                        } else if (topic.contains("temperature", ignoreCase = true)) {
                            Log.d("MQTT", "Topic: $topic | Message: $receivedMessage")
                            onMessageReceived(topic, receivedMessage)
                        }
                    }
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {}
            })

            val topics = listOf(
                "$deviceId/iqa",
                "$deviceId/humidity",
                "$deviceId/temperature"
            )

            topics.forEach { topic ->
                mqttClient.subscribe(topic, 0)
                Log.d("MQTT", "Subscribed to topic: $topic")
            }
        } catch (e: MqttException) {
            Log.e("MQTT", "Error connecting or subscribing: ${e.message}")
        }
    }

    fun disconnect() {
        try {
            mqttClient.disconnect()
        } catch (e: MqttException) {
            Log.e("MQTT", "Error disconnecting: ${e.message}")
        }
    }
}
