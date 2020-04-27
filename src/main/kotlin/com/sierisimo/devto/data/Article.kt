package com.sierisimo.devto.data

data class Article(
	val id: Int,
	val title: String
) {
	enum class State {
		All, Fresh, Rising
	}
}