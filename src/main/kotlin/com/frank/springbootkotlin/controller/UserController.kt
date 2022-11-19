package com.frank.springbootkotlin.controller

import com.frank.springbootkotlin.model.User
import com.frank.springbootkotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/user-table")
    fun createUserTable(): ResponseEntity<String> {
        userService.createUserTable()
        return ResponseEntity.status(HttpStatus.CREATED).body("create success")
    }

    @PostMapping("/user")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user))
    }
}