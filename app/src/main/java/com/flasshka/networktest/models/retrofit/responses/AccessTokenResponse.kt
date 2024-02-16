package com.flasshka.networktest.models.retrofit.responses

data class AccessTokenResponse(
    val access_token: String,
    val expires_at: Long
)