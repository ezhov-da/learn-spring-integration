package ru.ezhov.learn.spring.integration.app.interfaces.rest.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import ru.ezhov.learn.spring.integration.app.service.IntegrationService

@RestController
@RequestMapping("/api")
class IntegrationRestController(
    private val integrationService: IntegrationService
) {
    @RequestMapping("/integration/import/_start", method = [RequestMethod.POST])
    fun startIntegration() {
        integrationService.start()
    }
}
