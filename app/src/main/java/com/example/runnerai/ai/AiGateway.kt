package com.example.runnerai.ai

data class AiContext(
    val userId: String,
    val question: String,
    val readiness: Int,
    val load: Double,
    val risk: Int,
    val pain: Int,
    val planSummary: String
)

data class AiRecommendation(
    val answer: String,
    val suggestedAction: String
)

interface AiGateway {
    suspend fun ask(context: AiContext): AiRecommendation
}

/**
 * Production implementation must call the secure backend.
 * No API key belongs in the Android application.
 */
class BackendAiGateway : AiGateway {
    override suspend fun ask(context: AiContext): AiRecommendation =
        AiRecommendation(
            "تحلیل AI پس از اتصال Backend ارائه می‌شود. Safety Engine همچنان مرجع نهایی ایمنی است.",
            "NO_AUTONOMOUS_ACTION"
        )
}
