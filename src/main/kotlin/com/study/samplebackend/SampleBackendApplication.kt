package com.study.samplebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.study"])
class SampleBackendApplication

fun main(args: Array<String>) {
runApplication<SampleBackendApplication>(*args)
}
