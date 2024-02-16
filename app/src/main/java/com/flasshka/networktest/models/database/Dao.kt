package com.flasshka.networktest.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert(entity = MessageEntity::class)
    fun addMessage(entity: MessageEntity)

    @Query("DELETE FROM MessageEntity WHERE chatId = :id")
    fun removeChat(id: Long)

    @Query("SELECT DISTINCT chatId FROM MessageEntity")
    fun getChatsId(): List<Long>

    @Query("SELECT * FROM MessageEntity WHERE chatId = :id")
    fun getMessagesInChat(id: Long): List<MessageEntity>
}