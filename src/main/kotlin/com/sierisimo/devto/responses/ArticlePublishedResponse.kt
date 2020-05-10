package com.sierisimo.devto.responses

import com.google.gson.annotations.SerializedName

data class ArticlePublishedResponse(
    //TODO: Map typeOf to different values of enum
    @SerializedName("type_of")
    val typeOf: String,
    val id: Int,
    val title: String,
    val description: String?,
    @SerializedName("cover_image")
    val coverImage: String?,
    @SerializedName("readable_publish_date")
    val readablePublishDate: String?,
    @SerializedName("social_image")
    val socialImage: String,
    @SerializedName("tag_list")
    val tagList: String,
    val tags: List<String>,
    val slug: String,
    val path: String,
    val url: String,
    @SerializedName("canonical_url")
    val canonicalUrl: String?,
    @SerializedName("comments_count")
    val commentsCount: Int,
    @SerializedName("positive_reactions_count")
    val positiveReactionsCount: Int,
    @SerializedName("collection_id")
    val collectionId: Int?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("edited_at")
    val editedAt: String?,
    @SerializedName("crossposted_at")
    val crosspostedAt: String?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("last_comment_at")
    val lastCommentAt: String?,
    @SerializedName("published_timestamp")
    val publishedTimestamp: String,
    @SerializedName("body_html")
    val bodyHtml: String,
    @SerializedName("body_markdown")
    val bodyMarkdown: String,
    val user: UserResponse,
    val organization: OrganizationResponse?
)