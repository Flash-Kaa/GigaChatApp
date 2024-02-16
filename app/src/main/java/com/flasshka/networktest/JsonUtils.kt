package com.flasshka.networktest

import com.fasterxml.jackson.databind.ObjectMapper


object JsonUtils {
    private val OBJ_MAPPER: ObjectMapper = ObjectMapper()

    fun <T> objToJson(obj: T): String {
        return OBJ_MAPPER.writeValueAsString(obj)
    }

    fun <T> jsonToObj(json: String?, tClass: Class<T>?): T {
        return OBJ_MAPPER.readValue(json, tClass)
    }
}