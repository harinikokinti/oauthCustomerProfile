package com.dhilli.cowin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CowinApplication

fun main(args: Array<String>) {
	runApplication<CowinApplication>(*args)
}
