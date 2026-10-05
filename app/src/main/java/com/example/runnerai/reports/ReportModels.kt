package com.example.runnerai.reports

data class ProgressReport(
    val period: String,
    val totalRuns: Int,
    val totalDistanceKm: Double,
    val totalTlu: Double,
    val averageReadiness: Int,
    val averagePain: Double,
    val completionRate: Double
)

object ReportEngine {
    fun completion(completed: Int, planned: Int): Double =
        if (planned <= 0) 0.0 else (completed.toDouble() / planned * 100.0).coerceIn(0.0, 100.0)
}
