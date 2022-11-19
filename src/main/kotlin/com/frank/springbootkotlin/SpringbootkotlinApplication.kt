package com.frank.springbootkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringbootkotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringbootkotlinApplication>(*args)
}
