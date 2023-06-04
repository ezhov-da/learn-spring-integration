package ru.ezhov.learn.spring.integration.app.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.integration.handler.MessageProcessor
import org.springframework.messaging.Message
import ru.ezhov.learn.spring.integration.app.dto.Shop
import kotlin.random.Random

/**
 * Сохранение магазинов в JSON
 */
class SaveShopsToJsonFileHandler(private val objectMapper: ObjectMapper) : MessageProcessor<Any> {

    override fun processMessage(message: Message<*>): Any? {
        println("${this::class.java} ${message.payload}")

        @Suppress("UNCHECKED_CAST")
        val shops = message.payload as? List<Shop>
        if (shops != null) {
            Thread.sleep(Random.nextLong(3000, 7000))
            println(objectMapper.writeValueAsString(shops))
        }
        return null
    }
}