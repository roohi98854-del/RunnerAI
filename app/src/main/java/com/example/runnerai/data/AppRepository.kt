package com.example.runnerai.data

/**
 * Repository contract. The first implementation can be local/offline;
 * a remote implementation can later sync with the secure backend.
 */
interface AppRepository {
    suspend fun saveProfile(profile: UserProfile)
    suspend fun addWorkout(workout: WorkoutRecord)
    suspend fun addPain(record: PainRecord)
    suspend fun addNutrition(record: NutritionRecord)
    suspend fun saveDailyState(state: DailyState)
}
