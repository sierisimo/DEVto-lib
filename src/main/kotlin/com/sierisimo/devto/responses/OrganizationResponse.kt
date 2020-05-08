package com.sierisimo.devto.responses

import com.google.gson.annotations.SerializedName

data class OrganizationResponse(
	val name: String,
	val username: String,
	val slug: String,
	@SerializedName("profile_image")
	val profileImage: String,
	@SerializedName("profile_image_90")
	val profileImage90: String?
)

