package com.sierisimo.devto.data

import com.sierisimo.devto.responses.OrganizationResponse
import com.sierisimo.devto.responses.UserResponse

open class Article(
    val title: String,
    val bodyMarkdown: String,
    val tags: List<String>,
    val publish: Boolean = false,
    val series: String? = null,
    val mainImageUrl: String? = null,
    val canonicalUrl: String? = null,
    val description: String? = null
) {

    class Information(
        title: String,
        bodyMarkdown: String,
        tags: List<String>,
        publish: Boolean = false,
        series: String? = null,
        mainImageUrl: String? = null,
        canonicalUrl: String? = null,
        description: String? = null
    ) : Article(title, bodyMarkdown, tags, publish, series, mainImageUrl, canonicalUrl, description)

    class Published(
        val typeOf: String,
        val id: Int,
        val coverImage: String,
        val readablePublishDate: String,
        val socialImage: String,
        val tagList: String,
        val slug: String,
        val path: String,
        val url: String,
        val commentsCount: Int,
        val positiveReactionsCount: Int,
        val collectionId: Int,
        val createdAt: String,
        val editedAt: String?,
        val crosspostedAt: String?,
        val publishedAt: String,
        val lastCommentAt: String?,
        val publishedTimestamp: String,
        val bodyHtml: String,
        val user: UserResponse,
        val organization: OrganizationResponse,
        title: String,
        bodyMarkdown: String,
        tags: List<String>,
        publish: Boolean = false,
        series: String? = null,
        mainImageUrl: String? = null,
        canonicalUrl: String? = null,
        description: String? = null
    ) : Article(title, bodyMarkdown, tags, publish, series, mainImageUrl, canonicalUrl, description)
}