package com.flasshka.networktest.models.database

import android.content.Context
import androidx.room.Room

object Dependencies {
    val repository: Repository by lazy {
        Repository(appDatabase.getDao())
    }

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .build()
    }
}