package com.sierisimo.devto.data

import com.sierisimo.devto.responses.OrganizationResponse
import com.sierisimo.devto.responses.UserResponse

/**
 * OUTPUT
 */
data class ArticlePublished(
    val typeOf: String,
    val id: Int,
    val title: String,
    val description: String,
    val coverImage: String,
    val readablePublishDate: String,
    val socialImage: String,
    val tagList: String,
    val tags: List<String>,
    val slug: String,
    val path: String,
    val url: String,
    val canonicalUrl: String,
    val commentsCount: Int,
    val positiveReactionsCount: Int,
    val collectionId: Int,
    val createdAt: String,
    val editedAt: String,
    val crosspostedAt: String,
    val publishedAt: String,
    val lastCommentAt: String,
    val publishedTimestamp: String,
    val bodyHtml: String,
    val bodyMarkdown: String,
    val user: User,
    val organization: Organization?
)