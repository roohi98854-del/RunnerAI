package com.example.runnerai.planner

import com.example.runnerai.engine.DailyMetrics

enum class PlannedBlock { RUNNING, STRENGTH, REHAB, RECOVERY, NUTRITION }

data class DailyPlan(
    val blocks: List<PlannedBlock>,
    val intensityMultiplier: Double,
    val reason: String
)

object DailyPlanner {
    fun build(m: DailyMetrics): DailyPlan = when {
        m.readiness < 30 || m.overloadRisk >= 75 ->
            DailyPlan(listOf(PlannedBlock.RECOVERY, PlannedBlock.REHAB), 0.0, "ظرفیت امروز پایین است.")
        m.readiness < 50 || m.overloadRisk >= 50 ->
            DailyPlan(listOf(PlannedBlock.REHAB, PlannedBlock.STRENGTH), 0.6, "بار امروز کاهش یافته است.")
        else ->
            DailyPlan(listOf(PlannedBlock.RUNNING, PlannedBlock.REHAB, PlannedBlock.NUTRITION), 1.0, "برنامه عادی با پایش واکنش بدن.")
    }
}
