package com.flasshka.networktest.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val chatId: Long,
    val owner: String,
    val message: String
)
