package com.sierisimo.devto.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val name: String,
    val username: String,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("github_username")
    val githubUsername: String?,
    @SerializedName("website_url")
    val websiteUrl: String?,
    @SerializedName("profile_image")
    val profileImage: String?,
    @SerializedName("profile_image_90")
    val profileImage90: String?
)

