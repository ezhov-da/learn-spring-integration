package ru.ezhov.learn.spring.integration.app.gateway

import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.Message
import ru.ezhov.learn.spring.integration.app.config.START_INTEGRATION_CHANNEL

@MessagingGateway
interface ImportGateway {
    @Gateway(requestChannel = START_INTEGRATION_CHANNEL)
    fun start(message: Message<*>)
}