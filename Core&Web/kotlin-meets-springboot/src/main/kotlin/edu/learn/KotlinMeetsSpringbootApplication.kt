package edu.learn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMeetsSpringbootApplication

fun main(args: Array<String>) {
	runApplication<KotlinMeetsSpringbootApplication>(*args)
}
