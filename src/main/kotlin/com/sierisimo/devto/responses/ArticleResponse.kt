package com.sierisimo.devto.responses

import com.google.gson.annotations.SerializedName

internal data class ArticleResponse(
	@SerializedName("type_of")
	val typeOf: String,
	val id: Int,
	val title: String,
	val description: String,
	@SerializedName("cover_image")
	val coverImage: String?,
	@SerializedName("readable_publish_date")
	val readablePublishDate: String,
	@SerializedName("social_image")
	val socialImage: String,
	val slug: String,
	val path: String,
	val url: String,
	@SerializedName("canonical_url")
	val canonicalUrl: String,
	@SerializedName("comments_count")
	val commentsCount: Int,
	@SerializedName("positive_reactions_count")
	val positiveReactionCount: Int,
	@SerializedName("collection_id")
	val collectionId: String?,
	@SerializedName("created_at")
	val createdAt: String,
	@SerializedName("edited_at")
	val editedAt: String?,
	@SerializedName("crossposted_at")
	val crosspostedAt: String?,
	@SerializedName("published_at")
	val publishedAt: String?,
	@SerializedName("last_comment_at")
	val lastCommentAt: String,
	@SerializedName("published_timestamp")
	val publishedTimestamp: String,
	@SerializedName("tag_list")
	val tagList: List<String>?,
	val tags: String?,
	val user: UserResponse,
	@SerializedName("flare_tag")
	val flareTag: FlareTagResponse,
	val organization: OrganizationResponse?
)

