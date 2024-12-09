package com.project.nfc.service

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@SpringBootTest
class ExternalApiServiceTest(@Autowired private val service: ExternalApiService) {

    @Test
    @DisplayName("최근 1일이 아닌 날짜 예외 테스트")
    fun weatherApiPastExceptionTest() {
        //given
        val x = 55.1086228
        val y = 127.4012191
        val localDateTime = LocalDateTime.of(
            LocalDate.of(2024,12,8),
            LocalTime.of(15,0)
        )
        //when, then
        org.junit.jupiter.api.assertThrows<IllegalArgumentException>{
            service.fetchRealTimeWeatherInfo(x, y, localDateTime)
        }
    }
}