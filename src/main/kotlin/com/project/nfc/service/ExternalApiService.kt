package com.project.nfc.service

import com.project.nfc.controller.dtos.WeatherInfoFormat
import com.project.nfc.service.*
import com.project.nfc.service.dtos.Item
import com.project.nfc.service.dtos.WeatherApiResponse
import com.project.nfc.service.dtos.WeatherInfoResult
import com.project.nfc.service.enum.WeatherCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.bodyToMono
import org.springframework.web.util.DefaultUriBuilderFactory
import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode
import org.springframework.web.util.UriBuilder
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.floor


@Service
class ExternalApiService(
    @Value("\${weather.api.servicekey}") private val serviceKey: String,
    @Value("\${weather.api.url}") private val serviceUrl: String
) {
    private val json: String = "JSON"
    fun fetchRealTimeWeatherInfo(x: Double, y: Double, dateTime: LocalDateTime): WeatherInfoResult {
        val dateTimeStr: String = dateTime.toString()
            .replace("-","")
            .replace(":","")
        val dateStr = dateTimeStr.slice(0..7)
        val timeStr = dateTimeStr.slice(9..12)
        val webClient = buildWebClient()

        try {
            // 오류로 body 없을 때 예외 처리 필요
            val result = webClient.get()
                .uri { uriBuilder: UriBuilder ->
                    uriBuilder
                        .queryParam("serviceKey", serviceKey)
                        .queryParam("numOfRows", 10)
                        .queryParam("pageNo", 1)
                        .queryParam("base_date", dateStr)
                        .queryParam("base_time", timeStr)
                        .queryParam("nx", floor(x).toInt())
                        .queryParam("ny", floor(y).toInt())
                        .queryParam("dataType", json)
                        .build()
                }
                .retrieve()
                .bodyToMono<WeatherApiResponse>()
                .block() ?: throw IllegalArgumentException("no data")

            return makeWeatherInfoResult(result)
        } catch (e: WebClientResponseException) {
            val errorMessage = e.message
            throw IllegalArgumentException(errorMessage)
        }
    }

    private fun buildWebClient(): WebClient {
        val factory = DefaultUriBuilderFactory(serviceUrl)
        factory.encodingMode = EncodingMode.VALUES_ONLY

        return WebClient.builder()
            .baseUrl(serviceUrl)
            .uriBuilderFactory(factory)
            .defaultHeaders { headers ->
                headers.set("Content-Type", "application/json")
                headers.set("Accept", "application/json") }
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create()
                        .responseTimeout(Duration.ofSeconds(5))
                )
            )
            .build()
    }

    private fun makeWeatherInfoResult(result: WeatherApiResponse): WeatherInfoResult {
        val weatherInfoList: MutableList<WeatherInfoFormat> = mutableListOf()

        if (result.response.body == null) throw IllegalArgumentException(result.response.header.resultMsg)

        result.response.body.items.item.map { item ->
            when (item.category) {
                WeatherCode.TEMPERATURE.code -> weatherInfoList.add(makeFormattedString(WeatherCode.TEMPERATURE, item))
                WeatherCode.HUMIDITY.code -> weatherInfoList.add(makeFormattedString(WeatherCode.HUMIDITY, item))
                WeatherCode.WIND_SPEED.code -> weatherInfoList.add(makeFormattedString(WeatherCode.WIND_SPEED, item))
                WeatherCode.HOURLY_PRECIPITATION_AMOUNT.code ->
                    weatherInfoList.add(makeFormattedString(WeatherCode.HOURLY_PRECIPITATION_AMOUNT, item))
                else -> null
            }
        }

        return toWeatherInfoResult(weatherInfoList)
    }

    private fun toWeatherInfoResult(list: List<WeatherInfoFormat>): WeatherInfoResult {
        return WeatherInfoResult(
            list[0],
            list[1],
            list[2],
            list[3],
        )
    }

    private fun makeFormattedString(code: WeatherCode, item: Item): WeatherInfoFormat {
        return WeatherInfoFormat(code.itemName, code.unit, item.obsrValue.toDouble())
    }
}