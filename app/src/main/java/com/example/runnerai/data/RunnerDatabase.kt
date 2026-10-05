package com.example.runnerai.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user_profile")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val goal: String? = null,
    val wakeTime: String? = null
)

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val type: String,
    val startedAt: Long,
    val durationSec: Int,
    val distanceMeters: Double?,
    val rpe: Int?,
    val completed: Boolean,
    val tlu: Double
)

@Entity(tableName = "pain_records")
data class PainEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val createdAt: Long,
    val area: String,
    val intensity: Int,
    val duringActivity: Boolean,
    val nextDayResponse: Int?
)

@Entity(tableName = "nutrition_records")
data class NutritionEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val createdAt: Long,
    val meal: String,
    val calories: Double?,
    val proteinG: Double?,
    val carbsG: Double?,
    val fatG: Double?,
    val waterMl: Int?
)

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkout(workout: WorkoutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPain(pain: PainEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNutrition(nutrition: NutritionEntity)

    @Query("SELECT * FROM workouts WHERE userId = :userId ORDER BY startedAt DESC")
    fun workouts(userId: String): Flow<List<WorkoutEntity>>

    @Query("SELECT * FROM pain_records WHERE userId = :userId ORDER BY createdAt DESC")
    fun pains(userId: String): Flow<List<PainEntity>>

    @Query("SELECT * FROM nutrition_records WHERE userId = :userId ORDER BY createdAt DESC")
    fun nutrition(userId: String): Flow<List<NutritionEntity>>
}

@Database(
    entities = [UserEntity::class, WorkoutEntity::class, PainEntity::class, NutritionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RunnerDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}
