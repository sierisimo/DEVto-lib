package com.sierisimo.devto.client.repositories

import com.sierisimo.devto.api.DevToAPI
import feign.Client
import feign.Feign
import feign.Logger
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import okhttp3.logging.HttpLoggingInterceptor

internal fun buildAPIClient(): DevToAPI {
	return Feign.builder()
		.client(buildHttpClient())
		.encoder(GsonEncoder())
		.decoder(GsonDecoder())
		.logger(Slf4jLogger(DevToAPI::class.java))
		.logLevel(Logger.Level.FULL)
		.target(DevToAPI::class.java, "https://dev.to/api")
}

private fun buildHttpClient(): Client {
	return OkHttpClient(
		okhttp3.OkHttpClient
			.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.build()
	)
}