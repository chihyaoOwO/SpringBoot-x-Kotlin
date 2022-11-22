package com.frank.springbootkotlin.utils

import arrow.core.Either
import arrow.core.flatMap
import com.fasterxml.jackson.databind.ObjectMapper
import com.frank.springbootkotlin.exception.AppException
import com.frank.springbootkotlin.exception.ResponseHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

val objectMapper: ObjectMapper = ObjectMapper()

inline fun <reified T> toRestResponseBase(either: Either<AppException, T>, status: HttpStatus): ResponseEntity<String> =
    either.flatMap { obj ->
        Either.catch {
            objectMapper.writeValueAsString(obj)
        }.mapLeft {
            AppException.JsonSerializationFail(it)
        }
    }.fold(
        ifRight = {
            ResponseEntity.status(status).body(it)
        },
        ifLeft = {
            ResponseHandler.toResponse(it)
        }
    )

inline fun <reified T> Either<AppException, T>.toCreateResponse(): ResponseEntity<String> =
    toRestResponseBase(this, HttpStatus.CREATED)

inline fun <reified T> Either<AppException, T>.toRestResponse(): ResponseEntity<String> =
    toRestResponseBase(this, HttpStatus.OK)