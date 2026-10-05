package com.example.runnerai.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile private var INSTANCE: RunnerDatabase? = null

    fun get(context: Context): RunnerDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                RunnerDatabase::class.java,
                "runner_ai.db"
            ).build().also { INSTANCE = it }
        }
}
