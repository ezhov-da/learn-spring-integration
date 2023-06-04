package ru.ezhov.learn.spring.integration.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler
import ru.ezhov.learn.spring.integration.app.handler.SaveShopsToJsonFileHandler
import ru.ezhov.learn.spring.integration.app.handler.ThirdPartyApiShopTransformHandler

@Configuration
class FlowConfig {
    @Bean
    fun startImportThirdPartyLocation(
        thirdPartyLocationHandler: AbstractReplyProducingMessageHandler,
    ): IntegrationFlow =
        IntegrationFlows
            .from(START_INTEGRATION_CHANNEL)
            .handle(thirdPartyLocationHandler)
            .channel(THIRD_PARTY_LOCATION_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun startImportThirdPartyShop(
        thirdPartyShopHandler: AbstractReplyProducingMessageHandler,
    ): IntegrationFlow =
        IntegrationFlows
            .from(START_INTEGRATION_CHANNEL)
            .handle(thirdPartyShopHandler)
            .channel(THIRD_PARTY_SHOP_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun startImportThirdPartyShopType(
        thirdPartyShopTypeHandler: AbstractReplyProducingMessageHandler,
    ): IntegrationFlow =
        IntegrationFlows
            .from(START_INTEGRATION_CHANNEL)
            .handle(thirdPartyShopTypeHandler)
            .channel(THIRD_PARTY_SHOP_TYPES_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun processThirdPartyLocationComplete(
        thirdPartyApiShopTransformHandler: ThirdPartyApiShopTransformHandler
    ): IntegrationFlow =
        IntegrationFlows
            .from(THIRD_PARTY_LOCATION_COMPLETE_CHANNEL)
            .handle(thirdPartyApiShopTransformHandler)
            .channel(SHOP_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun processThirdPartyShopComplete(
        thirdPartyApiShopTransformHandler: ThirdPartyApiShopTransformHandler
    ): IntegrationFlow =
        IntegrationFlows
            .from(THIRD_PARTY_SHOP_COMPLETE_CHANNEL)
            .handle(thirdPartyApiShopTransformHandler)
            .channel(SHOP_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun processThirdPartyShopTypeComplete(
        thirdPartyApiShopTransformHandler: ThirdPartyApiShopTransformHandler
    ): IntegrationFlow =
        IntegrationFlows
            .from(THIRD_PARTY_SHOP_TYPES_COMPLETE_CHANNEL)
            .handle(thirdPartyApiShopTransformHandler)
            .channel(SHOP_COMPLETE_CHANNEL)
            .get()

    @Bean
    fun shopCompleteHandler(
        saveShopsToJsonFileHandler: SaveShopsToJsonFileHandler
    ): IntegrationFlow =
        IntegrationFlows
            .from(SHOP_COMPLETE_CHANNEL)
            .handle(saveShopsToJsonFileHandler)
            .get()
}