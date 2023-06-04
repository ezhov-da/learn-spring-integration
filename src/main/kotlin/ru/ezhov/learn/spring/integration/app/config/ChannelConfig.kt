package ru.ezhov.learn.spring.integration.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.IntegrationComponentScan
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.config.EnableIntegration

/**
 * Канал, на который подписываются все, кому необходимо событие старта интеграции
 */
const val START_INTEGRATION_CHANNEL = "START_INTEGRATION"

/**
 * Канал, в котором публикуются данные, полученные из стороннего API по расположению
 */
const val THIRD_PARTY_LOCATION_COMPLETE_CHANNEL = "THIRD_PARTY_LOCATION_COMPLETE"

/**
 * Канал, в котором публикуются данные, полученные из стороннего API по магазинам
 */
const val THIRD_PARTY_SHOP_COMPLETE_CHANNEL = "THIRD_PARTY_SHOP_COMPLETE"

/**
 * Канал, в котором публикуются данные, полученные из стороннего API по типам магазинов
 */
const val THIRD_PARTY_SHOP_TYPES_COMPLETE_CHANNEL = "THIRD_PARTY_SHOP_TYPES_COMPLETE"

/**
 * Канал, в котором публикуются обработанные данные по магазинам
 */
const val SHOP_COMPLETE_CHANNEL = "SHOP_COMPLETE"

@Configuration
@EnableIntegration
@IntegrationComponentScan("ru.ezhov.learn.spring.integration")
class ChannelConfig {
    @Bean(START_INTEGRATION_CHANNEL)
    fun startIntegrationChannel() = PublishSubscribeChannel()

    @Bean(THIRD_PARTY_LOCATION_COMPLETE_CHANNEL)
    fun thirdPartyLocationCompleteChannel() = PublishSubscribeChannel()

    @Bean(THIRD_PARTY_SHOP_COMPLETE_CHANNEL)
    fun thirdPartyShopCompleteChannel() = PublishSubscribeChannel()

    @Bean(THIRD_PARTY_SHOP_TYPES_COMPLETE_CHANNEL)
    fun thirdPartyShopTypesCompleteCompleteChannel() = PublishSubscribeChannel()

    @Bean(SHOP_COMPLETE_CHANNEL)
    fun shopCompleteChannel() = PublishSubscribeChannel()
}