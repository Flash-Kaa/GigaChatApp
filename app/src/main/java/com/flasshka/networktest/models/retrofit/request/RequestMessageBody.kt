package com.flasshka.networktest.models.retrofit.request

import com.fasterxml.jackson.annotation.JsonValue
import java.util.Locale

data class RequestMessageBody(
    val messages: List<Message>,
    val model: String,
    val temperature: Float,
    val top_p: Float,
    val max_tokens: Int = 1024,
    val n: Int = 1,
    val repetition_penalty: Float = 1f,
    val stream: Boolean = false,
    val update_interval: Number = 0
) {
    data class Message(
        val content: String,
        val role: MessageRole
    )

    enum class MessageRole {
        SYSTEM,
        USER,
        ASSISTANT,
        SEARCH_RESULT;

        @JsonValue
        override fun toString(): String {
            return super.toString().lowercase(Locale.getDefault())
        }
    }
}