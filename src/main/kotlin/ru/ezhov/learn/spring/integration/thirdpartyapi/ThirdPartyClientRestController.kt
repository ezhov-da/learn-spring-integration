package ru.ezhov.learn.spring.integration.thirdpartyapi

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/third-party-api")
class ThirdPartyClientRestController {
    @RequestMapping("/locations", method = [RequestMethod.GET])
    fun locations(): ThirdPartyLocations = ThirdPartyLocations(
        listOf(
            ThirdPartyLocation(id = "1", name = "Краснодар"),
            ThirdPartyLocation(id = "2", name = "Воркута"),
            ThirdPartyLocation(id = "3", name = "Сургут"),
        )
    )

    @RequestMapping("/shops", method = [RequestMethod.GET])
    fun shops(): ThirdPartyShops = ThirdPartyShops(
        listOf(
            ThirdPartyShop(id = "1", name = "Причал", locationId = "1", typeId = "1"),
            ThirdPartyShop(id = "2", name = "Эмоция", locationId = "2", typeId = "2"),
            ThirdPartyShop(id = "3", name = "Капля", locationId = "3", typeId = "3")
        )
    )

    @RequestMapping("/shop-types", method = [RequestMethod.GET])
    fun shopTypes(): ThirdPartyShopTypes = ThirdPartyShopTypes(
        listOf(
            ThirdPartyShopType(id = "1", name = "Большой"),
            ThirdPartyShopType(id = "2", name = "Маленький"),
            ThirdPartyShopType(id = "3", name = "Малюсенький")
        )
    )
}

data class ThirdPartyLocations(val list: List<ThirdPartyLocation>)
data class ThirdPartyLocation(val id: String, val name: String)

data class ThirdPartyShops(val list: List<ThirdPartyShop>)
data class ThirdPartyShop(val id: String, val name: String, val locationId: String, val typeId: String)

data class ThirdPartyShopTypes(val list: List<ThirdPartyShopType>)
data class ThirdPartyShopType(val id: String, val name: String)