package com.dhilli.cowin.connector

import com.dhilli.cowin.model.Centers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class CoWinConnector {

    private val URI = "/appointment/sessions/public/calendarByDistrict"

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY")

    @Autowired
    lateinit var coWinWebClient: WebClient

    fun findOpenSlots(districtId: String): Mono<Centers> {
        return coWinWebClient.get()
                .uri {
                    val params: MultiValueMap<String, String> = LinkedMultiValueMap()
                    params["district_id"] = listOf(districtId)
                    params["date"] = listOf(todayDate())
                    it.path(URI).queryParams(params).build()
                }
                .retrieve()
                .bodyToMono(Centers::class.java)
    }

    fun todayDate(): String {
        val today = LocalDate.now()
        return today.format(dateTimeFormatter)
    }

}