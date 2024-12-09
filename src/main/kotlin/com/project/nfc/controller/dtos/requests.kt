package com.project.nfc.controller.dtos

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Fetch real time weather info request")
data class WeatherInfoRequest(
    @Schema(description = "x coordinate", required = true)
    val x: Double,
    @Schema(description = "y coordinate", required = true)
    val y: Double,
    @Schema(description = "date and time requested", required = true)
    val dateTime: LocalDateTime,
)
