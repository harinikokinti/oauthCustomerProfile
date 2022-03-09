package com.dhilli.cowin.service

import com.dhilli.cowin.connector.CoWinConnector
import com.dhilli.cowin.model.Center
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip


@Service
class CoWinService {

    @Autowired
    lateinit var coWinConnector: CoWinConnector

    fun findOpenSlots(districtId: String, age: Int, dose: Int): Mono<List<Center>> {

        return coWinConnector.findOpenSlots(districtId)
            .map { it.centers }
            .map { centers ->
                centers.filter { !it.sessions.isNullOrEmpty() }
                    .map { center ->
                        center.sessions = center.sessions.filter { session ->
                            val availability =
                                if (dose == 1) session.availableCapacityDose1 else session.availableCapacityDose2
                            availability > 1 && session.minAgeLimit <= age
                        }.toList()
                        center
                    }
                    .filter { !it.sessions.isNullOrEmpty() }.toList()
            }
    }

    fun playSound(): Mono<Map<String, String>> {
        val input = AudioSystem.getAudioInputStream(File("/Users/dhilli/codebase/cowin/src/main/resources/sound.wav"))
        val clip: Clip = AudioSystem.getClip()
        clip.open(input)
        clip.microsecondPosition = 32 * 1000000
        clip.start()
        return Mono.just(mapOf("hello" to "sound"))
    }
}