package com.example.runnerai.safety

enum class WorkoutPermission {
    ALLOWED,
    REDUCED,
    BLOCKED
}

data class TrainingGateResult(
    val running: WorkoutPermission,
    val strength: WorkoutPermission,
    val rehab: WorkoutPermission,
    val decision: SafetyDecision
)

object TrainingGate {
    fun check(input: SafetyInput): TrainingGateResult {
        val d = SafetyEngine.evaluate(input)

        val reduced = d.action == SafetyAction.CAUTION
        return TrainingGateResult(
            running = permission(d.allowRunning, reduced),
            strength = permission(d.allowStrength, reduced),
            rehab = permission(d.allowRehab, false),
            decision = d
        )
    }

    private fun permission(allowed: Boolean, reduced: Boolean): WorkoutPermission =
        when {
            !allowed -> WorkoutPermission.BLOCKED
            reduced -> WorkoutPermission.REDUCED
            else -> WorkoutPermission.ALLOWED
        }
}
