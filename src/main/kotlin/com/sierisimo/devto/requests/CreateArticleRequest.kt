package com.sierisimo.devto.requests

import com.google.gson.annotations.SerializedName

data class CreateArticleRequest(
	val article: CreateArticle
)

data class CreateArticle(
	val title: String,
	@SerializedName("body_markdown")
	val bodyMarkdown: String,
	val tags: List<String>,
	val published: Boolean = true,
	val series: String? = null,
	@SerializedName("main_image")
	val mainImage: String? = null,
	@SerializedName("canonical_url")
	val canonincalUrl: String? = null,
	val description: String? = null
)