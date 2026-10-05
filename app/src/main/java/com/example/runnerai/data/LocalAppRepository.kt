package com.example.runnerai.data

class LocalAppRepository(private val dao: AppDao) : AppRepository {
    override suspend fun saveProfile(profile: UserProfile) =
        dao.saveUser(UserEntity(profile.id, profile.name, profile.age, profile.heightCm, profile.weightKg, profile.goal, profile.wakeTime))

    override suspend fun addWorkout(workout: WorkoutRecord) =
        dao.addWorkout(WorkoutEntity(workout.id, workout.userId, workout.type.name, workout.startedAt,
            workout.durationSec, workout.distanceMeters, workout.rpe, workout.completed, workout.tlu))

    override suspend fun addPain(record: PainRecord) =
        dao.addPain(PainEntity(record.id, record.userId, record.createdAt, record.area,
            record.intensity, record.duringActivity, record.nextDayResponse))

    override suspend fun addNutrition(record: NutritionRecord) =
        dao.addNutrition(NutritionEntity(record.id, record.userId, record.createdAt, record.meal,
            record.calories, record.proteinG, record.carbsG, record.fatG, record.waterMl))

    override suspend fun saveDailyState(state: DailyState) {
        // Daily-state persistence is intentionally added with its own migration
        // when the Safety Engine schema is finalized.
    }
}
