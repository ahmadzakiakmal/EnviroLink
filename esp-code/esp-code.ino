#include "DHT.h"
#include <WiFi.h>
#include <PubSubClient.h>

#define DHTPIN 4
#define DHTTYPE DHT22
DHT dht(DHTPIN, DHTTYPE);

const char* ssid = "EnviroLink";
const char* password = "44444444";
const char* mqttServer = "20.187.145.145";
const int mqttPort = 1883;
const char* mqttUser = "EnviroLink-0001";
const char* mqttPassword = "EnviroLink-0001";
const char* temperatureTopic = "EnviroLink-0001/temperature";
const char* humidityTopic = "EnviroLink-0001/humidity";

WiFiClient wifiClient;
PubSubClient mqttClient(wifiClient);

void setup() {
  Serial.begin(9600);
  dht.begin();
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }

  Serial.println("Connected to WiFi");
  mqttClient.setServer(mqttServer, mqttPort);
  
  while (!mqttClient.connected()) {
    if (mqttClient.connect("DHTClient", mqttUser, mqttPassword)) {
      Serial.println("Connected to MQTT broker");
    } else {
      Serial.print("Failed to connect to MQTT broker, trying again in 5 seconds...");
      delay(5000);
    }
  }
}

void loop() {
  delay(2000);

  float h = dht.readHumidity();
  float t = dht.readTemperature();
  float f = dht.readTemperature(true);

  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  float hif = dht.computeHeatIndex(f, h);
  float hic = dht.computeHeatIndex(t, h, false);

  String tempStr = String(t);
  String humidityStr = String(h);
  String heatIndexStr = String(hic);

  mqttClient.publish(temperatureTopic, tempStr.c_str());
  mqttClient.publish(humidityTopic, humidityStr.c_str());

  Serial.print("Humidity: ");
  Serial.print(h);
  Serial.print("%  Temperature: ");
  Serial.print(t);
  Serial.print("°C ");
  Serial.print(f);
  Serial.print("°F  Heat index: ");
  Serial.print(hic);
  Serial.print("°C ");
  Serial.print(hif);
  Serial.println("°F");

  mqttClient.loop();
}
