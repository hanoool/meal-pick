package com.project.nfc.service.enum

enum class WeatherErrorCode(
    val code :String,
    val description: String
) {
    NORMAL_SERVICE("00", "정상"),
    APPLICATION_ERROR("01", "어플리케이션 에러"),
    DB_ERROR("02", "데이터베이스 에러"),
    NODATA_ERROR("03", "데이터없음 에러"),
    HTTP_ERROR("04", "HTTP 에러"),
    SERVICETIME_OUT("05", "서비스 연결실패 에러"),
    INVALID_REQUEST_PARAMETER_ERROR("10", "잘못된 요청 파라메터 에러"),
    NO_MANDATORY_REQUEST_PARAMETERS_ERROR("11", "필수요청 파라메터가 없음"),
    NO_OPENAPI_SERVICE_ERROR("12", "해당 오픈API 서비스가 없거나 폐기됨"),
    SERVICE_ACCESS_DENIED_ERROR("20", "서비스 접근거부"),
    TEMPORARILY_DISABLE_THE_SERVICEKEY_ERROR("21", "일시적으로 사용할 수 없는 서비스 키"),
    LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR("22", "서비스 요청제한횟수 초과에러"),
    SERVICE_KEY_IS_NOT_REGISTERED_ERROR("30", "등록되지 않은 서비스키"),
    DEADLINE_HAS_EXPIRED_ERROR("31", "기한만료된 서비스키"),
    UNREGISTERED_IP_ERROR("32", "등록되지 않은 IP"),
    UNSIGNED_CALL_ERROR("33", "서명되지 않은 호출"),
    UNKNOWN_ERROR("99", "기타에러")
    ;

    companion object {
        fun getDescription(code: String): String {
            return entries.firstOrNull { it.code == code }?.description ?: throw IllegalArgumentException("Non exiting reason code")
        }
    }

}