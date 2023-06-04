package ru.ezhov.learn.spring.integration.app.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.integration.expression.ValueExpression
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler
import ru.ezhov.learn.spring.integration.app.handler.SaveShopsToJsonFileHandler
import ru.ezhov.learn.spring.integration.app.handler.ThirdPartyApiShopTransformHandler
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyLocations
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyShopTypes
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyShops
import java.net.URI

@Configuration
class HandlerConfig {

    private inline fun <reified T : Any> thirdPartyApiHandler(
        url: String,
        method: HttpMethod = HttpMethod.GET
    ): AbstractReplyProducingMessageHandler =
        HttpRequestExecutingMessageHandler(URI.create(url)).apply {
            setHttpMethod(method)
            setExpectedResponseTypeExpression(ValueExpression(object : ParameterizedTypeReference<T>() {}))
        }

    /**
     * Обработчик получения расположения из стороннего сервиса
     */
    @Bean
    fun thirdPartyLocationHandler(): AbstractReplyProducingMessageHandler =
        thirdPartyApiHandler<ThirdPartyLocations>(
            "http://localhost:8080/third-party-api/locations",
        )

    /**
     * Обработчик получения магазинов из стороннего сервиса
     */
    @Bean
    fun thirdPartyShopHandler(): AbstractReplyProducingMessageHandler =
        thirdPartyApiHandler<ThirdPartyShops>(
            "http://localhost:8080/third-party-api/shops",
        )

    /**
     * Обработчик получения типов магазинов из стороннего сервиса
     */
    @Bean
    fun thirdPartyShopTypeHandler(): AbstractReplyProducingMessageHandler =
        thirdPartyApiHandler<ThirdPartyShopTypes>(
            "http://localhost:8080/third-party-api/shop-types",
        )

    /**
     * Обработчик формирования списка магазинов из стороннего API в наши
     */
    @Bean
    fun thirdPartyApiShopTransformHandler(): ThirdPartyApiShopTransformHandler = ThirdPartyApiShopTransformHandler()

    /**
     * Обработчик итоговых магазинов для приложения
     */
    @Bean
    fun shopHandler(objectMapper: ObjectMapper): SaveShopsToJsonFileHandler = SaveShopsToJsonFileHandler(objectMapper)
}