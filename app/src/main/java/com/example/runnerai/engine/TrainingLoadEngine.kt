package com.example.runnerai.engine

data class LoadInput(
    val durationMin: Double,
    val intensity: Double,
    val rpe: Int? = null,
    val distanceKm: Double? = null,
    val pain: Int = 0
)

data class LoadResult(
    val tlu: Double,
    val strainFactor: Double
)

object TrainingLoadEngine {
    fun calculate(i: LoadInput): LoadResult {
        val effort = i.rpe?.coerceIn(1, 10)?.div(10.0) ?: i.intensity.coerceIn(0.0, 1.0)
        val painFactor = when {
            i.pain >= 7 -> 0.0
            i.pain >= 4 -> 0.65
            else -> 1.0
        }
        val tlu = (i.durationMin.coerceAtLeast(0.0) * effort * 10.0 * painFactor)
            .coerceIn(0.0, 500.0)
        return LoadResult(tlu, (effort * (1.0 + i.pain / 20.0)).coerceIn(0.0, 2.0))
    }
}
