package com.sierisimo.devto.client

data class DevToError(
    val error: String,
    val status: Int
) {
    companion object {
        val unknownError = DevToError("Unknown error happened", 400)
    }
}