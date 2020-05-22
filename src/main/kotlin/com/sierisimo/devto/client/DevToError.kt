package com.sierisimo.devto.client

data class DevToError(
    val error: String,
    val status: Int,
    val source: String = "Unknown source"
) {
    companion object {
        val unknownError = DevToError("Unknown error happened", 400)
    }
}