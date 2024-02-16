package com.flasshka.networktest.models.retrofit.request

import com.flasshka.networktest.models.retrofit.responses.AccessTokenResponse
import com.flasshka.networktest.models.retrofit.responses.MessageResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface HttpRequests {
    @FormUrlEncoded
    @POST("api/v2/oauth")
    fun getAccessToken(
        @Header("Authorization") auth: String,
        @Header("RqUID") RqUID: String,
        @Header("Content-Type") content: String,
        @Field("scope") scope: String
    ): Call<AccessTokenResponse>

    @POST("api/v1/chat/completions")
    fun getMessage(
        @Header("Content-Type") content: String,
        @Header("Accept") accept: String,
        @Header("Authorization") token: String,
        @Body body: RequestBody,
    ): Call<MessageResponse>
}