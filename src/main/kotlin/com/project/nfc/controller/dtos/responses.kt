package com.project.nfc.controller.dtos

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Fetch real time weather info response")
data class WeatherInfoResponse(
    val weatherInfoFormat: WeatherInfoFormat,
    val humidityInfo: WeatherInfoFormat,
    val windSpeedInfo: WeatherInfoFormat,
    val hourlyPrecipitationAmountInfo: WeatherInfoFormat
)

data class WeatherInfoFormat(
    val itemName: String,
    val unit: String,
    val value: Double
)