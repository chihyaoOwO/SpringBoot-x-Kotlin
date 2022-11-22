package com.frank.springbootkotlin.exception

sealed class AppException {
    data class DataBaseError(val e: Throwable) : AppException()
    data class JsonSerializationFail(val e: Throwable) : AppException()
}