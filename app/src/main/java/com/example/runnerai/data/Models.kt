package com.example.runnerai.data

data class UserProfile(
    val id: String,
    val name: String,
    val age: Int? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val goal: String? = null,
    val wakeTime: String? = null
)

enum class WorkoutType { RUNNING, REHAB, STRENGTH, RECOVERY, REST }

data class WorkoutRecord(
    val id: String,
    val userId: String,
    val type: WorkoutType,
    val startedAt: Long,
    val durationSec: Int = 0,
    val distanceMeters: Double? = null,
    val rpe: Int? = null,
    val completed: Boolean = false,
    val tlu: Double = 0.0
)

data class PainRecord(
    val id: String,
    val userId: String,
    val createdAt: Long,
    val area: String,
    val intensity: Int,
    val duringActivity: Boolean = false,
    val nextDayResponse: Int? = null
)

data class NutritionRecord(
    val id: String,
    val userId: String,
    val createdAt: Long,
    val meal: String,
    val calories: Double? = null,
    val proteinG: Double? = null,
    val carbsG: Double? = null,
    val fatG: Double? = null,
    val waterMl: Int? = null
)

data class DailyState(
    val date: String,
    val readiness: Int? = null,
    val trainingLoad: Double = 0.0,
    val overloadRisk: Int? = null,
    val availableCapacity: Double? = null,
    val status: String? = null
)
