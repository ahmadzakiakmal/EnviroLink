package com.example.envirolink.utils

enum class Pages(val activityName: String) {
    HOME("HomeActivity"),
    MAIN("MainActivity"),
    WEATHER("WeatherActivity"),
    WEATHERTIPS("WeatherTipsActivity");

    override fun toString(): String {
        return activityName
    }
}