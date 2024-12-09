package com.project.nfc.service.enum

enum class WeatherCode(
    val code: String,
    val itemName: String,
    val unit: String,
) {
    TEMPERATURE("T1H","기온", "℃"),
    HOURLY_PRECIPITATION_AMOUNT("RN1","1시간 강수량", "mm"),
    EAST_WEST_WIND_COMPONENT("UUU","동서바람성분", "m/s"),
    NORTH_SOUTH_WIND_COMPONENT("VVV","남북바람성분", "m/s"),
    HUMIDITY("REH","습도", "%"),
    PRECIPITATION_TYPE("PTY","강수형태", "코드값"),
    WIND_DIRECTION("VEC","풍향", "deg"),
    WIND_SPEED("WSD","풍속", "m/s");

    companion object {
        fun fromCode(code: String): WeatherCode {
            return entries.find { it.code == code } ?: throw IllegalArgumentException("Invalid code")
        }
    }
}

enum class PrecipitationType(val code: String, val description: String) {
    NONE("0", "없음"),
    RAIN("1", "비"),
    RAIN_AND_SNOW("2", "비/눈"),
    SNOW("3", "눈"),
    DRIZZLE("5", "빗방울"),
    DRIZZLE_AND_FLURRIES("6", "빗방울눈날림"),
    FLURRIES("7", "눈날림");

    companion object {
        fun fromCode(code: String): String {
            return entries.find { it.code == code }?.description ?: throw IllegalArgumentException("Code should be 0 to 7")
        }
    }
}