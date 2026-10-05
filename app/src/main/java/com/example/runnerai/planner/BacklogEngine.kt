package com.example.runnerai.planner

data class BacklogItem(
    val id: String,
    val category: PlannedBlock,
    val originalLoad: Double,
    val daysOld: Int
)

object BacklogEngine {
    fun recover(items: List<BacklogItem>, availableCapacity: Double): List<BacklogItem> {
        if (availableCapacity <= 0.0) return items
        var remaining = availableCapacity
        return items.sortedBy { it.daysOld }.mapNotNull {
            if (it.daysOld > 7) return@mapNotNull null
            val recoverable = minOf(it.originalLoad, remaining * 0.35)
            remaining -= recoverable
            if (recoverable >= it.originalLoad * 0.95) null
            else it.copy(originalLoad = it.originalLoad - recoverable)
        }
    }
}
