package ru.ezhov.learn.spring.integration.app.handler

import org.springframework.integration.handler.MessageProcessor
import org.springframework.messaging.Message
import ru.ezhov.learn.spring.integration.app.dto.Shop
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyLocations
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyShopTypes
import ru.ezhov.learn.spring.integration.thirdpartyapi.ThirdPartyShops
import kotlin.random.Random

/**
 * Класс собирает магазины из нескольких каналов
 */
class ThirdPartyApiShopTransformHandler : MessageProcessor<List<Shop>> {
    private var locations: ThirdPartyLocations? = null
    private var shops: ThirdPartyShops? = null
    private var shopTypes: ThirdPartyShopTypes? = null

    override fun processMessage(message: Message<*>): List<Shop>? {
        Thread.sleep(Random.nextLong(3000, 7000))

        when (val payload = message.payload) {
            is ThirdPartyLocations -> locations = payload
            is ThirdPartyShops -> shops = payload
            is ThirdPartyShopTypes -> shopTypes = payload
        }

        return if (locations != null && shops != null && shopTypes != null) {
            val shopsFrom = shops!!.list
            val locationsFrom = locations!!.list.associateBy { it.id }
            val shopTypesFrom = shopTypes!!.list.associateBy { it.id }

            shopsFrom.map {
                Shop(
                    id = it.id,
                    name = it.name,
                    locationName = locationsFrom[it.locationId]!!.name,
                    typeName = shopTypesFrom[it.typeId]!!.name,
                )
            }
        } else {
            null
        }
    }
}