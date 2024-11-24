package com.example.envirolink.utils

enum class Pages(val activityName: String) {
    HOME("HomeActivity"),
    MAIN("MainActivity"),
    WEATHER("WeatherActivity"),
    WEATHERTIPS("WeatherTipsActivity"),
    DEVICELIST("DeviceActivity");

    override fun toString(): String {
        return activityName
    }
}