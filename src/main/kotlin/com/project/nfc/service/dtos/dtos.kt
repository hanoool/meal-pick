package com.project.nfc.service.dtos

import com.project.nfc.controller.dtos.WeatherInfoFormat
import java.time.LocalDateTime

data class WeatherInfoCommand(
    val x: Double,
    val y: Double,
    val dateTime: LocalDateTime
)

data class WeatherInfoResult(
    val temperatureInfo: WeatherInfoFormat,
    val humidityInfo: WeatherInfoFormat,
    val windSpeedInfo: WeatherInfoFormat,
    val hourlyPrecipitationAmountInfo: WeatherInfoFormat
    //  날씨정보 (온도:14°C 강수확률: 1% 습도: 38% 풍속: 1m/s)
)

data class WeatherApiResponse(
    val response: Response
)

data class Response(
    val header: Header,
    val body: Body?
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val dataType: String,
    val items: Items,
    val pageNo: Int,
    val numOfRows: Int,
    val totalCount: Int
)

data class Items(
    val item: List<Item>
)

data class Item(
    val baseDate: String,
    val baseTime: String,
    val category: String,
    val nx: Int,
    val ny: Int,
    val obsrValue: String
)
