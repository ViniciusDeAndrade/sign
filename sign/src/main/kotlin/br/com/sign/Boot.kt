package br.com.sign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class Boot

fun main(args: Array<String>) {
	runApplication<Boot>(*args)
}
