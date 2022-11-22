package com.frank.springbootkotlin.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseHandler {

    private val logger: Logger = LoggerFactory.getLogger(this::class.qualifiedName)

    fun toResponse(appException: AppException): ResponseEntity<String> = when (appException) {
        is AppException.DataBaseError -> {
            logger.error("DataBaseError : ", appException.e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("DataBase Error.")
        }

        is AppException.JsonSerializationFail -> {
            logger.error("JsonSerializationFail : ", appException.e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Json Serialization Fail.")
        }
    }

}