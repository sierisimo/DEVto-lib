package com.sierisimo.devto.client

import com.google.gson.Gson
import feign.Response
import feign.codec.ErrorDecoder

internal class DevToErrorDecoder : ErrorDecoder {
    private val gson: Gson by lazy { Gson() }

    override fun decode(methodKey: String?, response: Response?): Exception {
        //TODO: Specific errors for specif method calls based on methodKey

        val error = response?.body()?.asInputStream()?.bufferedReader()?.readText()?.let {
            gson.fromJson(it, DevToError::class.java)
        } ?: DevToError.unknownError

        throw DevToCallException(error)
    }
}

