package com.example.runnerai.safety

enum class SafetyAction {
    NORMAL,
    CAUTION,
    RECOVERY,
    STOP_TRAINING,
    REFER_TO_DOCTOR
}

data class SafetyInput(
    val readiness: Int,
    val overloadRisk: Int,
    val painIntensity: Int,
    val painImproving: Boolean,
    val painDays: Int,
    val abnormalSymptoms: Boolean = false,
    val doctorRestriction: Boolean = false
)

data class SafetyDecision(
    val action: SafetyAction,
    val allowRunning: Boolean,
    val allowStrength: Boolean,
    val allowRehab: Boolean,
    val reason: String
)

object SafetyEngine {

    fun evaluate(input: SafetyInput): SafetyDecision {
        // Doctor restrictions and red flags always override AI/user goals.
        if (input.doctorRestriction) {
            return SafetyDecision(
                SafetyAction.REFER_TO_DOCTOR,
                allowRunning = false,
                allowStrength = false,
                allowRehab = true,
                reason = "محدودیت پزشکی فعال است؛ فقط تمرین‌های تأییدشده مجاز هستند."
            )
        }

        if (input.abnormalSymptoms) {
            return SafetyDecision(
                SafetyAction.STOP_TRAINING,
                false, false, false,
                "علائم غیرعادی گزارش شده؛ تمرین عادی متوقف شود و ارزیابی پزشکی انجام شود."
            )
        }

        // Persistent pain rule: urgent red flags are handled above.
        if (input.painIntensity >= 7) {
            return SafetyDecision(
                SafetyAction.STOP_TRAINING,
                false, false, false,
                "شدت درد بالا است؛ تمرین عادی متوقف شود."
            )
        }

        if (input.painDays >= 7 && !input.painImproving && input.painIntensity >= 4) {
            return SafetyDecision(
                SafetyAction.REFER_TO_DOCTOR,
                false, false, true,
                "درد پایدار و بدون بهبود نیاز به بررسی پزشک/فیزیوتراپیست دارد."
            )
        }

        if (input.readiness < 30 || input.overloadRisk >= 75) {
            return SafetyDecision(
                SafetyAction.RECOVERY,
                allowRunning = false,
                allowStrength = false,
                allowRehab = true,
                reason = "ظرفیت امروز پایین یا ریسک بیش از حد است؛ تمرکز روی ریکاوری و توانبخشی."
            )
        }

        if (input.readiness < 50 || input.overloadRisk >= 50 || input.painIntensity >= 4) {
            return SafetyDecision(
                SafetyAction.CAUTION,
                allowRunning = true,
                allowStrength = true,
                allowRehab = true,
                reason = "بار تمرین باید کاهش یابد و واکنش بدن پایش شود."
            )
        }

        return SafetyDecision(
            SafetyAction.NORMAL,
            true, true, true,
            "تمرین عادی از نظر Safety Engine مجاز است."
        )
    }
}
