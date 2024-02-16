package com.flasshka.networktest.models.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: Dao) {
    suspend fun insertMessage(message: MessageEntity) {
        withContext(Dispatchers.IO) {
            dao.addMessage(message)
        }
    }

    suspend fun removeChat(id: Long) {
        withContext(Dispatchers.IO) {
            dao.removeChat(id)
        }
    }

    suspend fun getChats(): List<Long> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getChatsId()
        }
    }

    suspend fun getMessagesInChat(id: Long): List<MessageEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext dao.getMessagesInChat(id)
        }
    }
}