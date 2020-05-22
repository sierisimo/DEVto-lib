package com.sierisimo.devto.client

import com.google.gson.Gson
import feign.Response
import feign.codec.ErrorDecoder

internal class DevToErrorDecoder : ErrorDecoder {
    private val gson: Gson by lazy { Gson() }

    override fun decode(methodKey: String?, response: Response?): Exception {
        //TODO: Specific errors for specif method calls based on methodKey and response.request
/*
        response?.let {
            println("--> ${it.reason()}")
            println("--> ${it.request()}")
        }
*/

        var error = response?.readBodyText()?.let {
            gson.fromJson(it, DevToError::class.java)
        } ?: DevToError.unknownError

        if (methodKey != null) {
            error = error.copy(source = methodKey)
        }

        throw DevToCallException(error)
    }

    private fun Response.readBodyText(): String? = body()?.asInputStream()?.bufferedReader()?.readText()
}

