package com.example.envirolink.ui.device

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

@Composable
fun DeviceScreen() {
    var deviceId by remember { mutableStateOf("") }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Device ID",
            style = TextStyle(fontSize = 18.sp, color = Color.Black),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BasicTextField(
            value = deviceId,
            onValueChange = { deviceId = it },
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (deviceId.isNotEmpty()) {
                    coroutineScope.launch(Dispatchers.IO) {
                        mqttManager.connectAndSubscribe(deviceId)
                    }
                    isConnected = true
                } else {
                    // Always commented per user preference
                    // Toast.makeText(LocalContext.current, "Please enter a device ID", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Connect")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isConnected) {
            Text(
                text = "Connected to EnviroLink-$deviceId",
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
                "EnviroLink-$deviceId/iqa",
                "EnviroLink-$deviceId/humidity",
                "EnviroLink-$deviceId/temperature"
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
