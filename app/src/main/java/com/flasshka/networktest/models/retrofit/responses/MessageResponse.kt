package com.flasshka.networktest.models.retrofit.responses

data class MessageResponse(
    val choices: List<Choice>,
    val created: Int,
    val model: String,
    val `object`: String,
    val usage: Usage
) {
    data class Choice(
        val finish_reason: String,
        val index: Int,
        val message: Message
    ) {
        data class Message(
            val content: String,
            val role: String
        )
    }

    data class Usage(
        val completion_tokens: Int,
        val prompt_tokens: Int,
        val system_tokens: Int,
        val total_tokens: Int
    )
}