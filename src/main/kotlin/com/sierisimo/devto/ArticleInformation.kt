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

internal fun ArticleInformation.requireValidTitle() =
    require(title.isNotBlank()) { "Title cannot be blank or empty for a new article" }

internal fun ArticleInformation.requireValidBody() =
    require(bodyMarkdown.isNotBlank()) { "Creation of article requires a body in markdown text" }

fun articleOf(title: String, markdown: String, tags: List<String> = emptyList()): ArticleInformation {
    require(title.isNotBlank())
    require(markdown.isNotBlank())

    return ArticleInformation(title, markdown, tags.filter { it.isNotBlank() })
}