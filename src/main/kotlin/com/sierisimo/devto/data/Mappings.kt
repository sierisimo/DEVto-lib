package com.sierisimo.devto.data

import com.sierisimo.devto.requests.ArticleRequest
import com.sierisimo.devto.requests.ArticleRequestInfo
import com.sierisimo.devto.responses.ArticlePublishedResponse
import com.sierisimo.devto.responses.OrganizationResponse
import com.sierisimo.devto.responses.UserResponse

internal fun mapInfoToRequest(articleInformation: ArticleInformation) = with(articleInformation) {
    ArticleRequest(
        ArticleRequestInfo(
            title,
            bodyMarkdown,
            tags,
            publish,
            series,
            mainImageUrl,
            canonicalUrl,
            description
        )
    )
}

internal fun mapResponseToPublished(publishedResponse: ArticlePublishedResponse) = with(publishedResponse) {
    ArticlePublished(
        typeOf,
        id,
        title,
        description ?: "",
        coverImage ?: "",
        readablePublishDate ?: "",
        socialImage,
        tagList,
        tags,
        slug,
        path,
        url,
        canonicalUrl ?: "",
        commentsCount,
        positiveReactionsCount,
        collectionId ?: -1,
        createdAt,
        editedAt ?: "",
        crosspostedAt ?: "",
        publishedAt ?: "",
        lastCommentAt ?: "",
        publishedTimestamp,
        bodyHtml,
        bodyMarkdown,
        mapUserResponse(user),
        mapOrganizationResponse(organization)
    )
}

internal fun mapUserResponse(response: UserResponse) = with(response) {
    User(name, username, twitterUsername, githubUsername, websiteUrl, profileImage, profileImage90)
}

internal fun mapOrganizationResponse(response: OrganizationResponse?) = response?.let {
    Organization(it.name, it.username, it.slug, it.profileImage, it.profileImage90)
}