package com.example.runnerai.engine

data class PersonalBodyModel(
    val baselineReadiness: Double = 70.0,
    val recoveryRate: Double = 1.0,
    val loadTolerance: Double = 1.0,
    val painSensitivity: Double = 1.0,
    val confidence: Double = 0.25
)

data class BodyObservation(
    val predictedReadiness: Double,
    val actualReadiness: Double,
    val previousLoad: Double,
    val nextDayPain: Int
)

object PersonalModelEngine {
    fun update(m: PersonalBodyModel, o: BodyObservation): PersonalBodyModel {
        val error = o.actualReadiness - o.predictedReadiness
        val toleranceShift = when {
            o.nextDayPain >= 6 -> -0.04
            o.nextDayPain >= 4 -> -0.02
            error > 8 -> 0.02
            else -> 0.005
        }
        return m.copy(
            baselineReadiness = (m.baselineReadiness + error * 0.08).coerceIn(20.0, 90.0),
            recoveryRate = (m.recoveryRate + error * 0.002).coerceIn(0.5, 1.5),
            loadTolerance = (m.loadTolerance + toleranceShift).coerceIn(0.5, 1.5),
            painSensitivity = (m.painSensitivity + if (o.nextDayPain >= 6) 0.03 else -0.005).coerceIn(0.5, 1.5),
            confidence = (m.confidence + 0.02).coerceIn(0.25, 0.95)
        )
    }
}
