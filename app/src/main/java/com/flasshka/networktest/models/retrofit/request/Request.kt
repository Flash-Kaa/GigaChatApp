package com.flasshka.networktest.models.retrofit.request

import android.util.Log
import com.flasshka.networktest.JsonUtils
import com.flasshka.networktest.models.retrofit.RetrofitInstance
import com.flasshka.networktest.viewmodels.VM
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Request(private val vm: VM) {
    private val client: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .initSSL()
    }

    private val jsonContentType = MediaType.parse("application/json")
    private val urlencodedContentType = MediaType.parse("application/x-www-form-urlencoded")

    fun getToken() {
        val retrofit = RetrofitInstance.get(client.build(), RetrofitInstance.CallType.GET_TOKEN)
            .create(HttpRequests::class.java)

        val call = retrofit.getAccessToken(
            "Basic ${vm.authData}",
            vm.rquid,
            urlencodedContentType.toString(),
            vm.apiVersion
        )

        val response = call.execute()

        val newMessage = if (response.isSuccessful) {
            response.body()?.access_token.toString()
        } else {
            Log.i("myLogScope", response.errorBody()?.string().toString())
            "!!!exception!!!"
        }

        vm.token = newMessage
    }

    fun getAnswer() {
        val retrofit = RetrofitInstance.get(client.build(), RetrofitInstance.CallType.GET_MESSAGE)
            .create(HttpRequests::class.java)

        val bodyObj = RequestMessageBody(
            messages = listOf(
                RequestMessageBody.Message(
                    "Hi! Translate this phrase, pls",
                    RequestMessageBody.MessageRole.USER
                ),
            ),
            model = "GigaChat",
            temperature = 1f,
            top_p = 1f
        )

        val call = retrofit.getMessage(
            content = jsonContentType.toString(),
            accept = jsonContentType.toString(),
            token = "Bearer ${vm.token}",
            body = RequestBody.create(
                jsonContentType,
                JsonUtils.objToJson(bodyObj)
            )
        )

        val response = call.execute()

        val newMessage = if (response.isSuccessful) {
            response.body()?.choices?.last()?.message?.content ?: "null"
        } else {
            Log.i("myLogScope", response.errorBody()?.string().toString())
            "!!!exception!!!"
        }

        vm.token = newMessage
    }

    private fun OkHttpClient.Builder.initSSL(): OkHttpClient.Builder {
        val sslContext = SSLContext.getInstance("SSL")
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        sslContext.init(null, trustAllCerts, SecureRandom())

        return this.sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
    }
}