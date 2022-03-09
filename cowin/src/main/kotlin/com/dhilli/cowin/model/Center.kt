package com.dhilli.cowin.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonIgnoreProperties(ignoreUnknown = true)
data class Centers(
        val centers: List<Center> = mutableListOf()
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Center(
        val centerId: String,
        val name: String,
        val address: String,
        val pincode: Int,
        val feeType: String,
        var sessions: List<Session> = mutableListOf()
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class Session(
        val sessionId: String,
        val date: String,
        val availableCapacityDose1: Int,
        val availableCapacityDose2: Int,
        val minAgeLimit: Int,
        val vaccine: String
)