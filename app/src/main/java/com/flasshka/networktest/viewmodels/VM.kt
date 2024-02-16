package com.flasshka.networktest.viewmodels

import androidx.lifecycle.ViewModel
import com.flasshka.networktest.models.database.MessageEntity
import com.flasshka.networktest.models.database.Repository
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

class VM(private val repository: Repository) : ViewModel() {
    var token: String = ""

    // will be added by the user in the application
    var authData: String = ""
    var rquid: String = ""
    var apiVersion: String = ""

    fun getMessagesInChat(chatId: Long) {
        newSingleThreadContext("myCoroutine").use { myCoroutine ->
            runBlocking(myCoroutine) {
                val res = repository.getMessagesInChat(chatId)
            }
        }
    }

    fun addMessage(message: MessageEntity) {
        newSingleThreadContext("myCoroutine").use { myCoroutine ->
            runBlocking(myCoroutine) {
                repository.insertMessage(message)
            }
        }
    }
}