package com.hhplus.fcfs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FcfsApplication

fun main(args: Array<String>) {
	runApplication<FcfsApplication>(*args)
}
