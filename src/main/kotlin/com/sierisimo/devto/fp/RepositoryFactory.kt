package com.sierisimo.devto.fp

import com.sierisimo.devto.client.repositories.ArticleRepository
import com.sierisimo.devto.client.repositories.buildAPIClient

internal fun buildRepository(): ArticleRepository {
    return ArticleRepository(
        ::buildAPIClient,
        ::mapArticleFromResponse,
        ::fromInfoToPublish,
        ::mapCreateRequestFromToPublish
    )
}