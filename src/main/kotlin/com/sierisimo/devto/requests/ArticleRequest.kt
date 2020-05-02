package com.sierisimo.devto.requests

import com.google.gson.annotations.SerializedName

data class ArticleRequest(val article: ArticleRequestInfo)

data class ArticleRequestInfo(
	val title: String,
	@SerializedName("body_markdown")
	val bodyMarkdown: String,
	val tags: List<String>,
	val published: Boolean = true,
	val series: String? = null,
	@SerializedName("main_image")
	val mainImage: String? = null,
	@SerializedName("canonical_url")
	val canonicalUrl: String? = null,
	val description: String? = null
)