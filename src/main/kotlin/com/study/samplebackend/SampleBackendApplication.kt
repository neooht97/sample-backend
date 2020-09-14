package com.study.samplebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

@SpringBootApplication(scanBasePackages = ["com.study"])
class SampleBackendApplication

fun main(args: Array<String>) {
	runApplication<SampleBackendApplication>(*args)
}
