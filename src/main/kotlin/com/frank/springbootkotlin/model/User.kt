package com.frank.springbootkotlin.model

data class User(
        var id: String,
        var username: String,
        var age: Int
)

data class UserRequestVO(
        var username: String,
        var age: Int
)