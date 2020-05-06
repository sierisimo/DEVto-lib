package com.sierisimo.devto

data class ArticleInformation(
    val title: String,
    val bodyMarkdown: String,
    val tags: List<String>,
    val publish: Boolean = false,
    val series: String? = null,
    val mainImageUrl: String? = null,
    val canonicalUrl: String? = null,
    val description: String? = null
)

internal fun ArticleInformation.requireValidTitle() = require(title.isNotBlank())
internal fun ArticleInformation.requireValidBody() = require(bodyMarkdown.isNotBlank())

fun articleOf(title: String, markdown: String, tags: List<String> = emptyList()): ArticleInformation {
    require(title.isNotBlank())
    require(markdown.isNotBlank())

    return ArticleInformation(title, markdown, tags.filter { it.isNotBlank() })
}