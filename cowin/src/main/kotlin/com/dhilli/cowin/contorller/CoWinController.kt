package com.dhilli.cowin.contorller

import com.dhilli.cowin.model.Center
import com.dhilli.cowin.service.CoWinService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CoWinController {

    @Autowired
    lateinit var coWinService: CoWinService

    @GetMapping("/cowin/hello")
    fun hello(): Mono<Map<String, String>> {
        return Mono.just(mapOf("sample" to "hello"))
    }

    @GetMapping("/cowin/slots")
    fun findSlots(
        @RequestParam(required = false, defaultValue = "265") districtId: String,
        @RequestParam(required = false, defaultValue = "18") age: Int,
        @RequestParam(required = false, defaultValue = "1") dose: Int
    ): Mono<List<Center>> {
        return coWinService.findOpenSlots(districtId, age, dose)
    }

    @GetMapping("/cowin/sound")
    fun playSound(): Mono<Map<String, String>> {
        return coWinService.playSound()
    }
}