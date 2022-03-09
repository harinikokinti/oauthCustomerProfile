package com.dhilli.cowin.scheduler

import com.dhilli.cowin.selinium.AutomateBooking
import com.dhilli.cowin.service.CoWinService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.concurrent.schedule

@Service
class CoWinScheduler {

    private val log = LoggerFactory.getLogger(CoWinScheduler::class.java)

    @Autowired
    lateinit var coWinService: CoWinService

    @Autowired
    lateinit var automateBooking: AutomateBooking

    var jacksonObjectMapper = jacksonObjectMapper()

    init {
        setupSchedulerTask()
    }

    fun setupSchedulerTask() {
        Timer().schedule(2000, 4000) {
            coWinService.findOpenSlots("294", 18, 1)
                    .doOnNext {
                        if (it.isNullOrEmpty()) {
                            //log.info("No vaccine found")
                        }
                    }
                    .map { centers ->
                        val slots = centers.associate { center ->
                            val pair = center.pincode to center.sessions.map {
                                "${it.date} : Dose1 - ${it.availableCapacityDose1} : Dose2 - ${it.availableCapacityDose2}"
                            }
                                    .toList()
                                    .joinToString()
                            pair
                        }

                        if (!slots.isNullOrEmpty()) {
                            coWinService.playSound()
                            log.info(jacksonObjectMapper.writeValueAsString(slots))
                            automateBooking.automateBooking(slots.keys.first())
                        }

                    }
                    .subscribe()
        }
    }

}