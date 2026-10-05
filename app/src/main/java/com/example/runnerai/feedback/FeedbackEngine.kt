package com.example.runnerai.feedback

data class FeedbackEvent(
    val phase: String,
    val readiness: Int? = null,
    val pain: Int? = null,
    val rpe: Int? = null,
    val completed: Boolean? = null
)

data class FeedbackAction(
    val reduceLoad: Boolean,
    val stop: Boolean,
    val message: String
)

object FeedbackEngine {
    fun evaluate(e: FeedbackEvent): FeedbackAction {
        if ((e.pain ?: 0) >= 7) return FeedbackAction(true, true, "درد شدید: تمرین متوقف شود.")
        if ((e.pain ?: 0) >= 4 || (e.rpe ?: 0) >= 9)
            return FeedbackAction(true, false, "بار تمرین کاهش یابد و واکنش بدن پایش شود.")
        return FeedbackAction(false, false, "بازخورد در محدوده قابل قبول است.")
    }
}
