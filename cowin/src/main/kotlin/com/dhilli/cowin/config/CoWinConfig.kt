package com.dhilli.cowin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class CoWinConfig {

    @Bean
    fun coWinWebClient(webClientBuilder: WebClient.Builder) : WebClient {
        return webClientBuilder.baseUrl("https://cdn-api.co-vin.in/api/v2").build()
    }
}