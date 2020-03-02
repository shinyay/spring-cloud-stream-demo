package io.pivotal.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Processor

@SpringBootApplication
@EnableBinding(Processor::class)
class SpringCloudStreamDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudStreamDemoApplication>(*args)
}
