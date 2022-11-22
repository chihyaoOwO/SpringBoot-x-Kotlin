package com.frank.springbootkotlin.controller

import com.frank.springbootkotlin.model.UserRequestVO
import com.frank.springbootkotlin.service.UserService
import com.frank.springbootkotlin.utils.toCreateResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/user-table")
    suspend fun createUserTable(): ResponseEntity<String> =
        userService.createUserTable().toCreateResponse()

    @PostMapping("/user")
    suspend fun createUser(@RequestBody userVO: UserRequestVO): ResponseEntity<String> =
        userService.createUser(userVO).toCreateResponse()
}