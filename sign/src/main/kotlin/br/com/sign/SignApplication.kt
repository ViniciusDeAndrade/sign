package br.com.sign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SignApplication

fun main(args: Array<String>) {
	runApplication<SignApplication>(*args)
}
