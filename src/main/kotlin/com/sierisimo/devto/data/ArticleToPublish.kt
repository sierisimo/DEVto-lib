package com.sierisimo.devto.data

data class ArticleToPublish(
	val title: String,
	val body: String,
	val tags: List<String>,
	val publish: Boolean,
	val series: String,
	val mainImage: String,
	val url: String,
	val description: String
)