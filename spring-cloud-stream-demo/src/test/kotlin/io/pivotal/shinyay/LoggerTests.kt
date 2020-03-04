package io.pivotal.shinyay

import io.pivotal.shinyay.entity.LogMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.cloud.stream.messaging.Processor
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@Tag("LoggerTest")
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [SpringCloudStreamDemoApplication::class])
@DirtiesContext
class LoggerTests(private val pipe: Processor, private val messageCollector: MessageCollector) {

    @Test
    @DisplayName("whenSendMessage_thenResponseShouldUpdateText")
    fun whenSendMessage_thenResponseShouldUpdateText() {
        pipe.input()
                .send(
                        MessageBuilder.withPayload(LogMessage("Hello!")).build()
                )
        val payload = messageCollector.forChannel(pipe.output())
                .poll()
                .payload
        assertThat(payload.toString()).isEqualTo("Hello!")
    }
}