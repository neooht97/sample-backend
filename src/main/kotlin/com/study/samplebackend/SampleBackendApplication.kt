package com.study.samplebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleBackendApplication

fun main(args: Array<String>) {
	runApplication<SampleBackendApplication>(*args)
}
