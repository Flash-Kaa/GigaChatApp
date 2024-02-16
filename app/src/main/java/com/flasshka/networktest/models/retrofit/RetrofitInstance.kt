package com.flasshka.networktest.models.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun get(
        client: OkHttpClient,
        type: CallType
    ) = Retrofit.Builder()
        .baseUrl(type.toString())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    enum class CallType {
        GET_MESSAGE,
        GET_TOKEN;

        override fun toString(): String {
            return when (this) {
                GET_MESSAGE -> "https://gigachat.devices.sberbank.ru"
                GET_TOKEN -> "https://ngw.devices.sberbank.ru:9443"
            }
        }
    }
}