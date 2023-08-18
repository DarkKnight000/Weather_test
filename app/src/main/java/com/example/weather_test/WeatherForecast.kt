package com.example.weather_test

// Данные
data class WeatherForecast(
    val date: String,
    val avgTempC: Double,
    val maxWindKph: Double,
    val avgHumidity: Double,
    val conditionText: String,
    val conditionIcon: String
)