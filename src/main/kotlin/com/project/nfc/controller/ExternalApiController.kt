package com.project.nfc.controller

import com.project.nfc.controller.dtos.WeatherInfoRequest
import com.project.nfc.controller.dtos.WeatherInfoResponse
import com.project.nfc.service.ExternalApiService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ExternalApiController(
    private val externalApiService: ExternalApiService
) {

    @GetMapping("weather-info")
    @Operation(summary = "fetch real time weather info through open api")
    fun fetchRealTimeWeatherInfo(request: WeatherInfoRequest): WeatherInfoResponse {
        val result = externalApiService.fetchRealTimeWeatherInfo(request.x, request.y, request.dateTime)
        return WeatherInfoResponse(
            weatherInfoFormat = result.temperatureInfo,
            humidityInfo = result.humidityInfo,
            windSpeedInfo = result.windSpeedInfo,
            hourlyPrecipitationAmountInfo = result.hourlyPrecipitationAmountInfo
        )
    }

}