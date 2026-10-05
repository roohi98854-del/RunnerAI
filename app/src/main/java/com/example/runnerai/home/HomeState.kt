package com.example.runnerai.home

data class HomeState(
    val status: String,
    val readiness: Int,
    val load: Double,
    val risk: Int,
    val capacity: Double,
    val mainWorkout: String,
    val message: String
)
