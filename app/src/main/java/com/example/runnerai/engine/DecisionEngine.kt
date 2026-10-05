package com.example.runnerai.engine

data class DailyMetrics(
    val readiness: Int,
    val trainingLoad: Double,
    val overloadRisk: Int,
    val availableCapacity: Double
)

object DecisionEngine {
    fun calculate(
        recentLoad: Double,
        acuteLoad: Double,
        baseline: Double,
        pain: Int,
        model: PersonalBodyModel
    ): DailyMetrics {
        val loadRatio = if (baseline <= 0.0) 1.0 else acuteLoad / baseline
        val recoveryPenalty = ((50 - model.baselineReadiness) * 0.35).coerceAtLeast(0.0)
        val painPenalty = pain * 4.0 * model.painSensitivity
        val readiness = (model.baselineReadiness - recoveryPenalty - painPenalty - (loadRatio - 1.0) * 25.0)
            .coerceIn(0.0, 100.0).toInt()
        val risk = ((loadRatio - 0.8) * 75.0 + pain * 5.0 + (70 - readiness) * 0.35)
            .coerceIn(0.0, 100.0).toInt()
        val capacity = (100.0 - acuteLoad / model.loadTolerance - pain * 6.0)
            .coerceIn(0.0, 100.0)
        return DailyMetrics(readiness, recentLoad, risk, capacity)
    }
}
