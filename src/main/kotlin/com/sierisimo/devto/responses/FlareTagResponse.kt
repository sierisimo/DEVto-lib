package com.sierisimo.devto.responses

import com.google.gson.annotations.SerializedName

data class FlareTagResponse(
	val name: String,
	@SerializedName("bg_color_hex")
	val bgColorHex: String,
	@SerializedName("text_color_hex")
	val textColorHex: String
)