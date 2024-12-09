package com.project.nfc.controller.dtos

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Fetch real time weather info response")
data class WeatherInfoResponse(
    val temperatureInfo: String,
    val humidityInfo: String,
    val windSpeedInfo: String,
    val hourlyPrecipitationAmountInfo: String
)
