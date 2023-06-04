package ru.ezhov.learn.spring.integration.app.service

import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import ru.ezhov.learn.spring.integration.app.gateway.ImportGateway

@Service
class IntegrationServiceImpl(
    private val importGateway: ImportGateway
) : IntegrationService {
    override fun start() {
        Thread { importGateway.start(MessageBuilder.withPayload("start import").build()) }
            .apply { isDaemon = false }
            .start()
    }
}